/**  
 * @FileName: TransportStation.java 
 * @Package com.bow.model.ie 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.ie;

import java.util.List;

/** 
 * @ClassName: TransportStation 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年10月5日 上午11:45:18  
 */

public class TransportStation {

    private Long id;

    private String name;

    private Cell departure;

    private Cell destination;

    private List<Tool> tools;
}
