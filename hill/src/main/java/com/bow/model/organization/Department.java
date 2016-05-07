/**  
 * @FileName: Department.java 
 * @Package com.bow.model.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.organization;

/**
 * @ClassName: Department
 * @Description: 在 Organization 中，其上层为 Company 下层为 Duty 或者为Department
 * @author ViVi
 * @date 2015年6月10日 下午10:42:19
 */

public class Department extends OrgaNode {

    // type:1公司 2 部门 3职务 4员工
    private Integer type = 2;


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
