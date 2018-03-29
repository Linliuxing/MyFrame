package org.haiyu.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lyq on 2018/3/17.
 */

public class PropsUtil {

    public static Properties loadProps(String fileName){
        Properties properties = null;
        InputStream is = null;
        try{
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(is == null) {
                throw new FileNotFoundException(fileName + "不存在");
            }
            properties = new Properties();
            properties.load(is);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return properties;
        }
    }

    public static String getString(Properties properties,String key){
        return properties.getProperty(key);
    }

    public static String getString(String key){

        return getString(loadProps("config.properties"),key);
    }



    public static void main(String[] args){
        Properties properties = PropsUtil.loadProps("config.properties");
        String value = PropsUtil.getString(properties,"app.jsp_path");
        System.out.println(value);
    }


}
