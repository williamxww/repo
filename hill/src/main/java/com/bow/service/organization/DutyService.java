/**  
 * @FileName: DutyService.java 
 * @Package com.bow.service.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.organization;

import com.bow.component.exception.ParameterException;
import com.bow.model.organization.Duty;

/** 
 * @ClassName: DutyService 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年6月12日 下午10:31:59  
 */

public interface DutyService {

    public boolean hasDuty(Duty duty);

    public Duty getDuty(long dutyId);

    public int addDuty(Duty duty) throws ParameterException;

    public int updateDuty(Duty duty);

    int deleteDuty(long dutyId);


}
