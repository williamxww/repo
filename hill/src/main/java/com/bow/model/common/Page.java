/**  
 * @FileName: Page.java 
 * @Package com.bow.model.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: Page
 * @Description: 页码实体类，用PageInterceptor分页
 * @author ViVi
 * @date 2015年6月27日 上午11:00:37
 */

public class Page<T> {

    private static final Logger logger = LoggerFactory.getLogger(Page.class);

    private int currentPage;

    private int pageSize;

    /**
     * 总记录数
     */
    private int total;

    /**
     * 详细记录
     */
    private List<T> rows;

    /**
     * 总页数
     */
    private int totalPage;

    public Page() {

    }

    /**
     * <a>PageHelper</a>要用到
     * @param currentPage
     * @param pageSize
     */
    public Page(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Page(int currentPage, int pageSize, List<T> rows) {
        this(currentPage, pageSize, rows.size(), rows);
    }

    public Page(int currentPage, int pageSize, int totalCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = totalCount;
        this.totalPage = caculateTotalPage(pageSize, totalCount);
    }

    public Page(int currentPage, int pageSize, int totalCount, List<T> rows) {
        this.rows = rows;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = totalCount;
        this.totalPage = caculateTotalPage(pageSize, totalCount);
    }

    /**
     * @Description: 求总页数
     * @param pageSize
     * @param totalCount
     * @return
     */
    private int caculateTotalPage(int pageSize, int totalCount) {
        int totalPage = 0;
        if (pageSize > 0) {
            totalPage = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
        }
        return totalPage;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage
     *            the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * @return the totalPage
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * @param totalPage
     *            the totalPage to set
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the rows
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * @param rows
     *            the rows to set
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    /**
     * @Description: 获取此对象对应easyui格式的 json字符串
     * @return
     */
    public String getJsonString() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", rows);
        map.put("total", total);
        String result = "";
        try {
            result = mapper.writeValueAsString(map);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
        return result;
    }

}
