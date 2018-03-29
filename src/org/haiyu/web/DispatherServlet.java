package org.haiyu.web;

import org.haiyu.annotation.RequestBoby;
import org.haiyu.bean.BeanContainer;
import org.haiyu.bean.BeanFactory;
import org.haiyu.bean.Handler;
import org.haiyu.bean.ModelAndView;
import org.haiyu.helper.ControllerHelper;
import org.haiyu.utils.JsonUtil;
import org.haiyu.utils.PropsUtil;
import org.haiyu.utils.StringUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by lyq on 2018/3/17.
 */

@WebServlet(urlPatterns = "/",loadOnStartup =0)
public class DispatherServlet extends HttpServlet{

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("****Init****");
        Loader.init();
        ServletContext servletContext = config.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(PropsUtil.getString("app.jsp_path")+"*");

        ServletRegistration assetServlet = servletContext.getServletRegistration("default");
        assetServlet.addMapping(PropsUtil.getString("app.asset_path")+"*");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("****Service****");
        String requestMethod = req.getMethod().toLowerCase();
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        String requestPath = null;
        if(contextPath!=null){
            requestPath = url.substring(contextPath.length());
        }

        Handler handler = ControllerHelper.getHander(requestMethod,requestPath);

        if(handler!=null){

            Class<?>  controllerClass = handler.getControllerClass();
            Object controllerBean = BeanContainer.getBean(controllerClass.getName());
            Object result ;

            Method method = handler.getMethod();
            int paramNum = method.getParameterCount();
            if(paramNum==0){
                result = BeanFactory.invokeMethod(controllerBean,method);
            }else {
                result = BeanFactory.invokeMethod(controllerBean,method,req);

            }
            if(result  instanceof ModelAndView){
                jspView((ModelAndView) result,req,resp);
            }else if(method.isAnnotationPresent(RequestBoby.class)){
                jdataView(result,resp);
            }
        }
    }

    public static void jspView(ModelAndView view , HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        String path = view.getPath();
        if(StringUtil.isNotEmpty(path)){
            if(path.startsWith("/")){
                res.sendRedirect(req.getContextPath()+path);
            }else{
                Map<String,Object> data = view.getData();
                for(Map.Entry<String,Object> entry :data.entrySet()){
                    req.setAttribute(entry.getKey(),entry.getValue());
                }
                req.getRequestDispatcher(PropsUtil.getString("app.jsp_path")+path).forward(req,res);
            }
        }

    }


    public static void jdataView(Object str,HttpServletResponse resp) throws IOException {
        if(str != null){
            resp.setContentType("text/plan");
            resp.setCharacterEncoding("utf-8");
            PrintWriter printWriter = resp.getWriter();
            if(str instanceof String){
                printWriter.write(str.toString());
            }else{
                printWriter.write(JsonUtil.toJson(str));
            }
            printWriter.flush();
            printWriter.close();
        }
    }
}
