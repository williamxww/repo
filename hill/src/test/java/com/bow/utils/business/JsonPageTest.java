/**  
 * @FileName: JsonPageTest.java 
 * @Package com.bow.utils.business 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.utils.business;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bow.model.common.Node;

/** 
 * @ClassName: JsonPageTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年8月15日 下午10:51:20  
 */

public class JsonPageTest {

    @Test
    public void test() {
        List<Node> nodes = new ArrayList<Node>();
        JsonPage<Node> p = new JsonPage<Node>(nodes);
        System.out.println(p.getJsonString());
    }
}
