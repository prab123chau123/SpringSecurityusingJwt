package com.SpringSecurity.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controllers {

	 
    @GetMapping("/hello")
    public ModelAndView sayHello(ModelAndView model) {
        model.addObject("message", "Hello, World!");
        model.setViewName("hello"); 
        return  model; // Returns the view name "hello" to be resolved by a ViewResolver
    }
}
