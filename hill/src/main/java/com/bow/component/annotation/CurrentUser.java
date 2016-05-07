
package com.bow.component.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bow.utils.Constant;

/**
 * 绑定当前登陆的用户
 * @author vivid
 * 2015-2-1
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字
     * @return
     */
    String value() default Constant.CURRENT_USER;

}
