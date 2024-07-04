package spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/beans")
public class BeanController {

    private final ApplicationContext applicationContext;

    @Autowired
    public BeanController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/something")
    public ResponseEntity<String> handle() {
        String body = "";

        for(String bean : applicationContext.getBeanDefinitionNames()) {
            body = body + String.format("%n") + bean;
        }
        return ResponseEntity.ok().body(body);
    }

    @GetMapping
    public List<String> getAllBeans() {

        // return applicationContext.getBeanDefinitionNames();
        // return new String[] { "hello" };
        List<String> list = new ArrayList<String>();
        list.add("yoav");
        return list;
    }
}
