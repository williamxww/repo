/**  
 * @FileName: JacksonTest.java 
 * @Package jackson 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package common.java;

import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/** 
 * @ClassName: JacksonTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年8月9日 下午3:10:38  
 */

public class JacksonTest {

    private JsonGenerator jsonGenerator = null;

    private ObjectMapper objectMapper = null;

    private Account account;

    @Before
    public void setUp() {
        account = new Account();
        account.setName("vv");
        account.setEmail("vv@163.com");
        objectMapper = new ObjectMapper();
        try {
            jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @After
    public void destroy() {

        try {
            if (jsonGenerator != null) {
                jsonGenerator.flush();
            }
            if (!jsonGenerator.isClosed()) {
                jsonGenerator.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        try {
            // 方式一
            // System.out.println("方式1");
            // objectMapper.writeValue(System.out, account);
            System.out.println("方式2");
            jsonGenerator.writeObject(account);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        ObjectNode node = objectMapper.createObjectNode();
        ObjectNode node2 = objectMapper.createObjectNode();
        node.put("aa", "aaa");
        node2.put("ee", node);
        System.out.println(node2);
    }

}
