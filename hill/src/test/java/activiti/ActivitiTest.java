/**  
 * @FileName: ActivitiTest.java 
 * @Package activiti 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: ActivitiTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年7月8日 下午10:41:53  
 */

public class ActivitiTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);



    @Test
    public void testDeploy() {
        ProcessEngine peocessEngine = (ProcessEngine) context.getBean("processEngine");
        RepositoryService repositoryService = peocessEngine.getRepositoryService();
        // 部署
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("activiti/myProcess.bpmn")
                .deploy();
        logger.debug("id:{},name:{}", deployment.getId(), deployment.getName());
        // 删除部署
        // repositoryService.deleteDeployment("5001");

    }

    @Test
    public void testExecute() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", new Integer(4));
        variables.put("vacationMotivation", "I'm really tired!");

        RuntimeService rumtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = rumtimeService.startProcessInstanceById("financialReport:1:3", variables);
        // activityId:当前execution在哪个节点上
        logger.debug("processInstanceId:{},activityId:{}", processInstance.getId(), processInstance.getActivityId());

    }

    @Test
    public void testTask() {
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().list();
        for (Task task : tasks) {
            logger.info("Task available: " + task.getName());
        }
    }
}
