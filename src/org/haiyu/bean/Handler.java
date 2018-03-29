package org.haiyu.bean;

import java.lang.reflect.Method;

/**
 * Created by lyq on 2018/3/21.
 */

public class Handler {
    private  Class<?> controllerClass;

    private Method method;

    public Handler(Class<?> controllerClass, Method method) {
        this.controllerClass = controllerClass;
        this.method = method;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getMethod() {
        return method;
    }

}
