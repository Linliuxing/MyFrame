package org.haiyu.web;

import org.haiyu.bean.BeanContainer;
import org.haiyu.helper.Classhelper;
import org.haiyu.helper.ControllerHelper;
import org.haiyu.helper.IocHelper;
import org.haiyu.utils.ClassUtil;

/**
 * Created by lyq on 2018/3/21.
 */

public class Loader {
    public static void init(){

        Class<?>[] cs ={Classhelper.class, BeanContainer.class, IocHelper.class, ControllerHelper.class};

        for(Class<?> c:cs){
            System.out.println(c.getName());
            ClassUtil.loadClass(c.getName(),true);
        }
    }
}
