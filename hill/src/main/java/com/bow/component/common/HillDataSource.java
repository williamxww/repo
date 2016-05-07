/**  
 * @FileName: HillDataSource.java 
 * @Package com.bow.component.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.common;

import org.apache.commons.dbcp.BasicDataSource;

import com.bow.utils.common.EncryptUtils;

/** 
 * @ClassName: HillDataSource 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年9月12日 下午2:20:11  
 */

public class HillDataSource extends BasicDataSource {

    @Override
    public void setPassword(String password) {
        // 解密后重置
        String newPassword = EncryptUtils.decodeBase64String(password);
        super.setPassword(newPassword);
    }
}
