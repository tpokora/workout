package org.tpokora.workout;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @RequestMapping("/api/hello")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<>();
        model.put("content", "Hello World");
        return model;
    }
}