package com.fcd.userconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fcd
 * @date 2020/6/4 11:21
 * @version 1.0
 */
@RestController
public class MainController {

    @Autowired ConsumerApi api;

    @GetMapping("/alive")
    public String alive() {
        return api.alive();
    }

    @GetMapping("/getById")
    public String getById(@RequestParam("id") Integer id) {
        System.out.println("===============id:" + id + "=========================");
        return api.getById(id);
    }
}
