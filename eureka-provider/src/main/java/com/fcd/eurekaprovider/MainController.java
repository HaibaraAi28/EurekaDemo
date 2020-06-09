package com.fcd.eurekaprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fcd
 * @date 2020/6/1 20:10
 * @version 1.0
 */
@RestController
public class MainController {

    @Autowired
    HealthStatusService healthStatusSrv;

    @Value("${server.port}")
    String port;

    @GetMapping("getHi")
    public String getHi() {
        return "Hi, 我的端口是：" + port;
    }

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {

        healthStatusSrv.setStatus(status);
        return healthStatusSrv.getStatus();
    }
}
