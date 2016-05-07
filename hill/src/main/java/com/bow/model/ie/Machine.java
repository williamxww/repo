/**  
 * @FileName: Machine.java 
 * @Package com.bow.model.ie 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.ie;

import java.math.BigDecimal;
import java.util.Map;

/** 
 * @ClassName: Machine 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年10月5日 上午10:56:37  
 */

public class Machine {

    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    /**
     * 外观属性 单位mm
     */
    private Integer longth;

    private Integer width;

    private Integer height;

    /**
     * 其他的一些属性
     */
    private Map<String, String> properties;


}
