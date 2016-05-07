/**  
 * @FileName: ModelSaveRestResource.java 
 * @Package com.bow.controller.activiti 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller.activiti.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @ClassName: ModelSaveRestResource
 * @Description: activiti-modeler-5.17.0.jar
 *               toolbar-default-actions.js中的SaveModel方法\n 将三部分信息传给服务器：
 *               模型的元数据：例如模型名称、分类、创建时间、最后一次更新时间等等
 *               模型JSON数据：将canvas内的图像数据转换成JSON数据UTF8字符串
 *               模型的SVG图像数据
 * @author ViVi
 * @date 2015年8月20日 下午9:29:26
 */

@Controller
public class ModelSaveRestResource implements ModelDataJsonConstants {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelSaveRestResource.class);

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/model/{modelId}/save", method = RequestMethod.PUT)
    public @ResponseBody String saveModel(@PathVariable String modelId,
            @RequestBody MultiValueMap<String, String> values) {
        try {

            Model model = repositoryService.getModel(modelId);

            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());

            modelJson.put(MODEL_NAME, values.getFirst("name"));
            modelJson.put(MODEL_DESCRIPTION, values.getFirst("description"));
            model.setMetaInfo(modelJson.toString());
            model.setName(values.getFirst("name"));

            // 将模型的元数据存入数据库的ACT_RE_MODEL表
            repositoryService.saveModel(model);

            // 将模型JSON数据UTF8字符串存入数据库的ACT_GE_BYTEARRAY表
            repositoryService.addModelEditorSource(model.getId(), values.getFirst("json_xml").getBytes("utf-8"));

            // 将模型的SVG图像数据转换成PNG格式
            InputStream svgStream = new ByteArrayInputStream(values.getFirst("svg_xml").getBytes("utf-8"));
            TranscoderInput input = new TranscoderInput(svgStream);

            PNGTranscoder transcoder = new PNGTranscoder();
            // Setup output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);

            // Do the transformation
            transcoder.transcode(input, output);
            final byte[] result = outStream.toByteArray();
            // 将PNG图像存入数据库的ACT_GE_BYTEARRAY表
            repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();

        } catch (Exception e) {
            LOGGER.error("Error saving model", e);
            throw new ActivitiException("Error saving model", e);
        }
        return "success";
    }
}
