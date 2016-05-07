/**  
 * @FileName: Duty.java 
 * @Package com.bow.model.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.organization;

/**
 * @ClassName: Duty
 * @Description: 在Organization 中 其下层为 Employee,上层为Department或者为Company
 * @author ViVi
 * @date 2015年6月10日 下午10:42:29
 */

public class Duty extends OrgaNode {

    // type:1公司 2 部门 3职务 4员工
    private Integer type = 3;



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
