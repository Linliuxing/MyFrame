package org.haiyu.helper;

import org.haiyu.annotation.RequestMapping;
import org.haiyu.bean.Handler;
import org.haiyu.bean.Request;
import org.haiyu.utils.ArrayUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyq on 2018/3/21.
 */

public class ControllerHelper {

    private  static  final Map<Request,Handler> requestMap = new HashMap<>();

    static {
        ArrayList<Class<?>> conClasses = Classhelper.getControllerClasses();
        if(conClasses!=null && conClasses.size()>0){
            init(conClasses);
        }
    }

    public static void init(ArrayList<Class<?>> conClasses){
        for (Class<?> c:conClasses){

            Method[] methods = c.getDeclaredMethods();
            if(ArrayUtil.isNotEmpty(methods)){
                for(Method m: methods){
                    if(m.isAnnotationPresent(RequestMapping.class)){
                        RequestMapping rm = m.getAnnotation(RequestMapping.class);
                        Request request = new Request(rm.method(),rm.path());
                        Handler handler = new Handler(c,m);
                        requestMap.put(request,handler);

                    }

                }
            }
        }

    }

    /**
     * 获取handler
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static  Handler getHander(String requestMethod,String requestPath){

        Request request = new Request(requestMethod,requestPath);
        return requestMap.get(request);
    }
}
