/**  
 * @FileName: Organization.java 
 * @Package com.bow.model.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.organization;

/**
 * @ClassName: Organization
 * @Description: DAO所用的实体类，与表字段对应
 * @author ViVi
 * @date 2015年9月27日 上午11:30:15
 */

public class Organization extends OrgaNode {



    private String managerCode;

    // 1主职 2副职
    private Integer dutyFlag;


    /**
     * @return the managerCode
     */
    public String getManagerCode() {
        return managerCode;
    }

    /**
     * @param managerCode
     *            the managerCode to set
     */
    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    /**
     * @return the dutyFlag
     */
    public Integer getDutyFlag() {
        return dutyFlag;
    }

    /**
     * @param dutyFlag
     *            the dutyFlag to set
     */
    public void setDutyFlag(Integer dutyFlag) {
        this.dutyFlag = dutyFlag;
    }

}
