/**  
 * @FileName: OrganizationTreeController.java 
 * @Package com.bow.controller.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller.organization;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bow.component.exception.BusinessException;
import com.bow.component.exception.WithCodeException;
import com.bow.component.interceptor.PageHelper;
import com.bow.model.common.EasyUiTreeNode;
import com.bow.model.common.Page;
import com.bow.model.common.Result;
import com.bow.model.organization.OrgaNode;
import com.bow.model.organization.Organization;
import com.bow.service.organization.OrganizationTreeService;
import com.bow.utils.common.JsonUtils;
import com.bow.utils.common.StrUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @ClassName: OrganizationTreeController
 * @Description: 组织机构树
 * @author ViVi
 * @date 2015年9月13日 下午7:54:35
 */

@Controller
public class OrganizationTreeController {

    @Autowired
    private OrganizationTreeService organizationTreeService;

    /**
     * 
     * @Description: 获取整棵树
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/organization/getOrganizationTree.do")
    @ResponseBody
    public String getOrganizationTree(HttpServletRequest request) throws JsonProcessingException {
        String idStr = request.getParameter("id");
        String toLevelStr = request.getParameter("toLevel");
        // 默认情况下全部查出
        long id = StrUtils.toLong(idStr, 1);
        int toLevel = StrUtils.toInt(toLevelStr, 4);

        List<OrgaNode> nodes = organizationTreeService.getOrganizationTree(id, toLevel);
        List<EasyUiTreeNode> list = new ArrayList<EasyUiTreeNode>();
        for (OrgaNode node : nodes) {
            EasyUiTreeNode e = new EasyUiTreeNode();
            e.setId(String.valueOf(node.getId()));
            e.setPid(String.valueOf(node.getPid()));
            e.setText(node.getName());
            // 设置树的图标
            switch (node.getType()) {
                case 1:
                    e.setIconCls("House");
                    break;
                case 2:
                    e.setIconCls("Group");
                    break;
                case 3:
                    e.setIconCls("Computer");
                    break;
                case 4:
                    e.setIconCls("User");
                    break;

            }
            list.add(e);
        }
        return JsonUtils.toEasyUiTree(list);
    }

    /**
     * 获取节点对应的子节点
     */
    @RequestMapping("/organization/getChildren.do")
    @ResponseBody
    public String getChildren(HttpServletRequest request) {
        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("rows");
        int pageNum = StrUtils.toInt(pageStr, 1);
        int sizeNum = StrUtils.toInt(sizeStr, 10);
        String id = request.getParameter("id");

        // 分页查询
        Page<OrgaNode> page = new Page<OrgaNode>(pageNum, sizeNum);
        PageHelper.startPage(page);
        // 默认展示最上层的子节点
        List<OrgaNode> nodes = organizationTreeService.getChildren(StrUtils.toLong(id, 0));
        page.setRows(nodes);
        return page.getJsonString();
    }

    /**
     * 
     * @Description: 按照规则根据type添加节点
     * @return
     */
    @RequestMapping("/organization/addOrgaNode.do")
    public String addOrgaNode(HttpServletRequest request) {
        String pidStr = request.getParameter("pid");
        String typeStr = request.getParameter("type");
        String name = request.getParameter("name");
        String statusStr = request.getParameter("status");
        String description = request.getParameter("description");

        int status = StrUtils.toInt(statusStr, 0);
        int type = StrUtils.toInt(typeStr, -1);
        long pid = StrUtils.toLong(pidStr, -1);

        OrgaNode node = new OrgaNode();
        node.setPid(pid);
        node.setType(type);
        node.setName(name);
        node.setStatus(status);
        node.setDescription(description);

        try {
            organizationTreeService.addOrgaNode(node);
        } catch (BusinessException e) {
            Result.getError(e.getMessage());
        }
        return Result.getSuccess();
    }


    /**
     * 
     * @Description: 删除组织结构树节点,支持批量删除
     * @return
     */
    @RequestMapping("/organization/deleteOrgaNode.do")
    @ResponseBody
    public String deleteOrgaNode(HttpServletRequest request) {
        String ids = request.getParameter("ids");

        try {
            StringTokenizer tokenizer = new StringTokenizer(ids, ",");
            while (tokenizer.hasMoreTokens()) {
                long id = StrUtils.toLong(tokenizer.nextToken(), -1);
                organizationTreeService.deleteOrgaNode(id);
            }

        } catch (WithCodeException e) {
            return Result.getError(e.getMessage());
        }
        return Result.getSuccess();
    }


    /**
     * 
     * @Description: 跳转到组织机构管理页
     * @return
     */
    @RequestMapping("/organization/toOrganizationTree.do")
    public String toOrganizationTree() {
        return "organization/organizationTree";
    }

    /**
     * 
     * @Description: 对组织机构树详细进行修改
     * @param request
     * @return
     */
    @RequestMapping("/organization/update.do")
    @ResponseBody
    public String updateOrganization(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        String pidStr = request.getParameter("pid");
        String codeStr = request.getParameter("code");
        String nameStr = request.getParameter("name");
        String typeStr = request.getParameter("type");
        String statusStr = request.getParameter("status");
        String descriptionStr = request.getParameter("description");
        String dutyFlagStr = request.getParameter("dutyFlag");
        String managerCodeStr = request.getParameter("managerCode");

        try {
            Organization organization = new Organization();
            organization.setId(StrUtils.toLong(idStr, -1));
            organization.setPid(StrUtils.toLong(pidStr, -1));
            organization.setCode(codeStr);
            organization.setName(nameStr);
            organization.setType(StrUtils.toInt(typeStr, -1));
            organization.setStatus(StrUtils.toInt(statusStr, -1));
            organization.setDescription(descriptionStr);
            organization.setDutyFlag(StrUtils.toInt(dutyFlagStr, -1));
            organization.setManagerCode(managerCodeStr);
            organizationTreeService.updateOrganization(organization);
        } catch (Exception e) {
            return Result.getError();
        }
        return Result.getSuccess();
    }

}
