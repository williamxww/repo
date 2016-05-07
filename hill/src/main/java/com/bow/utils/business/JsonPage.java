/**  
 * @FileName: JsonPage.java 
 * @Package com.bow.utils.business 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.utils.business;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: JsonPage
 * @Description: 此类为了方便eaysui详情页展示而用
 * @author ViVi
 * @date 2015年8月15日 下午10:19:46
 */

public class JsonPage<T> {

    private static final Logger logger = LoggerFactory.getLogger(JsonPage.class);

    private List<T> rows;

    private int total;

    public JsonPage(List<T> list) {
        if (list != null) {
            this.rows = list;
            this.total = list.size();
        }
    }

    public JsonPage(List<T> list, int totalCount) {
        this.rows = list;
        this.total = totalCount;
    }

    /**
     * @return the rows
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @Description: 获取此对象对应easyui格式的 json字符串
     * @return
     */
    public String getJsonString() {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(new JsonPage<T>(rows, total));
        } catch (IOException e) {
            logger.error("IOException", e);
        }
        return result;
    }

}
