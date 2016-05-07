/**  
 * @FileName: ProcessInstanceDiagramLayoutResource.java 
 * @Package com.bow.controller.activiti.diagram 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller.activiti.diagram;

import org.activiti.rest.diagram.services.BaseProcessDefinitionDiagramLayoutResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @ClassName: ProcessInstanceDiagramLayoutResource
 * @Description: activiti-diagram-rest-5.17.0.jar
 * @author ViVi
 * @date 2015年8月22日 上午11:26:32
 */

@RestController
public class ProcessInstanceDiagramLayoutResource extends BaseProcessDefinitionDiagramLayoutResource {

    @RequestMapping(value = "/service/process-instance/{processInstanceId}/diagram-layout", method = RequestMethod.GET, produces = "application/json")
    public ObjectNode getDiagram(@PathVariable String processInstanceId) {
        return getDiagramNode(processInstanceId, null);
    }
}