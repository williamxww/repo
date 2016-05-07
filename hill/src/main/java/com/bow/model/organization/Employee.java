/**  
 * @FileName: Employee.java 
 * @Package com.bow.model.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.organization;

/**
 * @ClassName: Employee
 * @Description: 
 *               在Organization中的员工实体，其上层为职务。一个人若有多个职务，则此人会有多条Employee实体数据,其中只有一条为主要职务
 * @author ViVi
 * @date 2015年6月10日 下午10:42:47
 */

public class Employee extends OrgaNode {

    // type:1公司 2 部门 3职务 4员工
    private Integer type = 4;

    // 该员工在职务上的汇报对象，注意，此人不一定是该员工的直接领导
    private String managerCode;

    // 这条数据是否是该员工的主职
    private Integer dutyFlag;

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

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
