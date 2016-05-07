/**  
 * @FileName: Cell.java 
 * @Package com.bow.model.ie 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.ie;

import java.util.List;

/**
 * @ClassName: Cell
 * @Description: 工作单元，工站和机器的结合体，能够独立的完成某一个功能点。
 * @author ViVi
 * @date 2015年10月5日 上午10:54:37
 */

public class Cell {

    /**
     * 在制品数量
     */
    private Long wipNum;

    private List<Station> stations;

    private List<Machine> machines;

    private double efficiency;
}
