/**  
 * @FileName: OrganizationTreeServiceImpl.java 
 * @Package com.bow.service.organization.impl 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.organization.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bow.component.exception.BusinessException;
import com.bow.dao.organization.OrganizationDao;
import com.bow.model.organization.OrgaNode;
import com.bow.model.organization.Organization;
import com.bow.service.organization.OrganizationTreeService;

/**
 * @ClassName: OrganizationTreeServiceImpl
 * @Description: OrganizationTreeService的实现类
 * @author ViVi
 * @date 2015年6月26日 下午9:29:54
 */
@Service("organizationTreeService")
public class OrganizationTreeServiceImpl implements OrganizationTreeService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationTreeServiceImpl.class);

    @Autowired
    private OrganizationDao dao;

    @Override
    public List<OrgaNode> expand(OrgaNode node) {
        // 可以自由展开
        return expand(node, 4);
    }

    @Override
    public List<OrgaNode> expandToCompany(OrgaNode node) {
        // 只能展开公司
        return expand(node, 1);
    }

    @Override
    public List<OrgaNode> expandToDepartment(OrgaNode node) {
        // 能展开公司和部门
        return expand(node, 2);

    }

    @Override
    public List<OrgaNode> expandToDuty(OrgaNode node) {
        // 能看到公司部门和职务
        return expand(node, 3);

    }

    private List<OrgaNode> expand(OrgaNode node, int toLevel) {
        // 参数校验
        if (node == null) {
            return null;
        }
        logger.debug("expand node's id {}", node.getId());
        List<Organization> organizations = dao.getDescendant(node.getId(), toLevel);

        if (organizations == null || organizations.size() == 0) {
            return null;
        }
        List<OrgaNode> orgaNodes = new ArrayList<OrgaNode>();
        for (Organization organization : organizations) {
            orgaNodes.add(organization);
        }
        return orgaNodes;
    }

    /**
     * 
     * @Description: 获取组织结构树
     * @param id
     *            从哪个节点开始
     * @param toLevel
     *            查询到 哪级结束 如只查询到部门则 toLevel为3
     * @return
     */
    @Override
    public List<OrgaNode> getOrganizationTree(long id, int toLevel) {
        List<Organization> organizations = dao.getDescendant(id, toLevel);
        return getOrgaNodes(organizations);
    }

    private List<OrgaNode> getOrgaNodes(List<Organization> organizations) {
        if (organizations == null || organizations.size() == 0) {
            return null;
        }
        List<OrgaNode> orgaNodes = new ArrayList<OrgaNode>();
        for (Organization organization : organizations) {
            orgaNodes.add(organization);
        }
        return orgaNodes;
    }

    /**
     * 获取子节点
     */
    @Override
    public List<OrgaNode> getChildren(long id) {
        return getOrgaNodes(dao.getChildren(id));
    }

    /**
     * 根据ID获取节点
     */
    @Override
    public OrgaNode getOrgaNode(long id) {
        return dao.getOrganization(id);
    }

    /**
     * 根据ID删除节点 有子节点不让删除
     * 
     * @throws BusinessException
     */
    @Override
    public int deleteOrgaNode(long id) throws BusinessException {
        List<OrgaNode> children = this.getChildren(id);
        if (children == null || children.size() == 0) {
            return dao.deleteOrganization(id);
        } else {
            throw new BusinessException("节点:" + id + " 有子节点,请先删除子节点");
        }
    }

    /**
     * 添加节点
     */
    @Override
    public int addOrgaNode(OrgaNode node) throws BusinessException {

        // node节点为公司节点,则可以随便创建
        if (node.getType() == 1) {
            return dao.addOrganization((Organization) node);
        }

        // 其他类型都要判断父节点
        OrgaNode parent = this.getOrgaNode(node.getPid());
        if (parent == null) {
            throw new BusinessException("父节点不存在");
        }

        int type = node.getType();
        // -1则说明没有传入type 则根据parent推断其类型
        if (type == -1) {
            type = parent.getType() + 1;
        }

        if (type > 4) {
            // 员工层级最低,因此不可能大于4
            throw new BusinessException("不能在员工下创建子节点");
        }

        return dao.addOrganization((Organization) node);
    }

    @Override
    public int updateOrganization(Organization organization) {
        return dao.updateOrganization(organization);
    }


}
