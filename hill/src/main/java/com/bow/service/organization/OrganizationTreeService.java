/**  
 * @FileName: OrganizationTreeService.java 
 * @Package com.bow.service.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.organization;

import java.util.List;

import com.bow.component.exception.BusinessException;
import com.bow.model.organization.OrgaNode;
import com.bow.model.organization.Organization;
import com.bow.service.common.TreeService;

/**
 * @ClassName: OrganizationTreeService
 * @Description: 组织机构树的一些操作 包含公司树 公司-部门树 公司-部门-职务树 部门-职务-员工树等
 * @author ViVi
 * @date 2015年6月26日 下午8:00:12
 */

public interface OrganizationTreeService extends TreeService<OrgaNode> {

    /**
     * 
     * @Description: 只展现公司的树
     * @param node
     * @return
     */
    public List<OrgaNode> expandToCompany(OrgaNode node);

    /**
     * @Description: 公司-部门树
     * @param id
     */
    public List<OrgaNode> expandToDepartment(OrgaNode node);

    /**
     * 
     * @Description: 公司-部门-职务树
     * @param id
     */
    public List<OrgaNode> expandToDuty(OrgaNode node);

    /**
     * 
     * @Description: 获取组织结构树
     * @param id
     *            从哪个节点开始
     * @param toLevel
     *            查询到 哪级结束 如只查询到部门则 toLevel为3
     * @return
     */
    public List<OrgaNode> getOrganizationTree(long id, int toLevel);

    /**
     * 
     * @Description: 获取子节点
     * @param id
     * @return
     */
    public List<OrgaNode> getChildren(long id);

    public OrgaNode getOrgaNode(long id);

    /**
     * 
     * @Description: 只有当没有子节点时才可以删除
     * @param id
     * @return
     * @throws BusinessException
     *             该节点有子节点就会抛错
     */
    public int deleteOrgaNode(long id) throws BusinessException;

    /**
     * 
     * @Description: 添加节点
     * @param node
     * @return
     * @throws BusinessException
     *             如，对于员工层级不能再添加子节点
     */
    public int addOrgaNode(OrgaNode node) throws BusinessException;

    /**
     * 
     * @Description: 此接口操作organization表所有字段
     * @param organization
     * @return
     */
    public int updateOrganization(Organization organization);
}
