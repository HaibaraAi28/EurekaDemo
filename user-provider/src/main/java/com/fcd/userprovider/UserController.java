package com.fcd.userprovider;

import com.fcd.userapi.UserApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fcd
 * @date 2020/6/4 10:35
 * @version 1.0
 */
@RestController
public class UserController implements UserApi {

    @Value("${server.port}")
    private String port;

    private AtomicInteger count = new AtomicInteger();

    @Override public String alive() {
        System.out.println("睡眠");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = count.getAndIncrement();
        System.out.println(port + "的第" + i + "次调用");
        return "port:" + port;
    }

    @Override public String getById(@RequestParam("id") Integer id) {
        System.out.println("===收到请求========，id为" + id);

        Map<Integer, String> res =  Collections.singletonMap(id, "fcd");
        System.out.println(res);
        return res.toString();
    }


}
