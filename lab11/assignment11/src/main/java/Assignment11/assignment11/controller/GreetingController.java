package Assignment11.assignment11.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @RequestMapping(value="/greeting")
    public String greeting() {
        return "Hello World";
    }
}