/**  
 * @FileName: Company.java 
 * @Package com.bow.model.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.organization;


/**
 * @ClassName: Company
 * @Description: Organization 的最上层,其下级可以为Department或Duty或Company
 * @author ViVi
 * @date 2015年6月10日 下午10:41:40
 */

public class Company extends OrgaNode {


    // type:1公司 2 部门 3职务 4员工
    private Integer type = 1;

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



}
