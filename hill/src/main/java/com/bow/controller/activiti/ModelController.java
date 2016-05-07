/**  
 * @FileName: ModelController.java 
 * @Package com.bow.controller.activiti 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller.activiti;



import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bow.model.common.Page;
import com.bow.utils.common.StrUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @ClassName: ModelController
 * @Description: 模型控制层(自定义的一些功能)
 * @author ViVi
 * @date 2015年8月9日 下午9:29:19
 */

@Controller
public class ModelController {

    private static final Logger logger = LoggerFactory.getLogger(ModelController.class);

    @Autowired
    RepositoryService repositoryService;

    /**
     * 模型列表
     */
    @RequestMapping("/model/toModelList.do")
    public String toModelList() {
        return "activiti/modelList";
    }

    /**
     * 
     * @Description: 分页查询model并转换为json
     * @param request
     * @return
     */
    @RequestMapping("/model/modelList.do")
    @ResponseBody
    public String modelList(HttpServletRequest request) {
        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("rows");
        int pageNum = StrUtils.toInt(pageStr, 1);
        int sizeNum = StrUtils.toInt(sizeStr, 10);
        List<Model> list = repositoryService.createModelQuery().list();
        // 分页显示
        Page<Model> page = new Page<Model>(pageNum, sizeNum, list);
        return page.getJsonString();
    }

    /**
     * @Description: 展示模型图
     * 
     *               代码参照 EditorProcessDefinitionInfoComponent.initImage()
     *               line79
     * 
     */
    @RequestMapping("/model/showModelImage.do")
    public void showModelImage(HttpServletRequest request, HttpServletResponse response) {
        String modelId = request.getParameter("modelId");
        byte[] editorSourceExtra = repositoryService.getModelEditorSourceExtra(modelId);
        try {
            response.getOutputStream().write(editorSourceExtra);

        } catch (Exception e) {
            logger.error("获取模型图片出错", e);
        }
    }


    /**
     * 创建模型 在此初始化模型的基本信息（名称，描述）并创建，然后跳转到模型编辑页面
     * 
     * 这里先填写模型的基本信息，模型的详细信息在图形化界面进行保存
     * 
     * 这些代码主要参照 activiti-explore-5.17.0.jar NewModelPopupWindow line134
     */
    @RequestMapping("/model/create.do")
    public void create(HttpServletRequest request, HttpServletResponse response) {

        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            // String key = request.getParameter("key");

            ObjectMapper objectMapper = new ObjectMapper();

            // 编辑器信息
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);

            // 模型信息
            Model modelData = repositoryService.newModel();
            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            description = StringUtils.defaultString(description);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(name);
            // modelData.setKey(StringUtils.defaultString(key));

            // 保存模型
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

            // response.sendRedirect(request.getContextPath() +
            // "/service/editor?id=" + modelData.getId());
            // 跳转到模型编辑--图形化界面
            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
        } catch (Exception e) {
            logger.error("创建模型失败", e);
        }
    }
    

    /**
     * 根据Model部署流程
     */
    @ResponseBody
    @RequestMapping(value = "/model/deploy.do", produces = "application/json")
    public ObjectNode deploy(String modelId) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        try {
            Model modelData = repositoryService.getModel(modelId);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService
                    .getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
                    .addString(processName, new String(bpmnBytes)).deploy();
            node.put("msg", "部署成功，部署ID=" + deployment.getId());
            node.put("success", true);
        } catch (Exception e) {
            logger.error("根据模型部署流程失败：modelId={}", modelId, e);
            node.put("msg", "模型部署流程失败");
        }
        return node;
    }

    /**
     * 导出model的xml文件
     */
    @RequestMapping("/model/export.do")
    public void export(String modelId, HttpServletResponse response) {
        try {
            Model modelData = repositoryService.getModel(modelId);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            JsonNode editorNode = new ObjectMapper()
                    .readTree(repositoryService.getModelEditorSource(modelData.getId()));
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            IOUtils.copy(in, response.getOutputStream());
            String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.flushBuffer();
        } catch (Exception e) {
            logger.error("导出model的xml文件失败：modelId={}", modelId, e);
        }
    }

    /**
     * 
     * @Description: 删除模型
     * @param modelId
     * @return
     */
    @RequestMapping(value = "/model/{modelId}/delete.do")
    @ResponseBody
    public String delete(@PathVariable("modelId") String modelId) {
        repositoryService.deleteModel(modelId);
        // return "redirect:/model/toModelList.do";
        return "{message:success}";
    }

    @RequestMapping(value = "/testNull.do")
    public @ResponseBody String testNull() {
        System.out.println("---------");
        return "success";
    }

}
