package my.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.DisposableBean;

public class Dog implements InitializingBean, DisposableBean {
    private String message;

    @Override
    public void afterPropertiesSet() throws Exception {
      System.out.println("Dog object created");
    }

    @Override
    public void destroy() throws Exception {
      System.out.println("Dog object destroyed");
    }
 
    public void setMessage(String message){
       this.message  = message;
    }
    public void getMessage(){
       System.out.println("Your Message : " + message);
    }
 }
