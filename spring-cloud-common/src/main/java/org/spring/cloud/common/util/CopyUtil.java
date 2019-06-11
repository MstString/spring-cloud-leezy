package org.spring.cloud.common.util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: spring-cloud-leezy
 * @description: 用于VO、BO、PO相同属性字段之间地复制
 * @author: LEEZY
 * @create: 2019-03-20 10:08
 **/
public class CopyUtil {
    
   /** 
   * @Description: 复制bean的属性
   * @Param: [source, clazz] 
   * @return: T 
   * @Author: LEEZY
   * @Date: 2019/3/20 
   */ 
    public static <T> T copyBean(Object source, Class<T> targetClass) {
        if (null == source) {
            return null;
        } 
        return JSON.parseObject(JSON.toJSONString(source), targetClass);
    }

    /** 
    * @Description: 复制ListBean的属性
    * @Param: [sourceList, targetClass] 
    * @return: java.util.List<T> 
    * @Author: LEEZY
    * @Date: 2019/3/20 
    */ 
    public static <T, E> List<T> copyList(List<E> sourceList, Class<T> targetClass) {
        if (sourceList == null) {
            return null;
        } else if (sourceList.size() == 0) {
            return new ArrayList<>();
        }
        return JSON.parseArray(JSON.toJSONString(sourceList), targetClass);
    }
}
