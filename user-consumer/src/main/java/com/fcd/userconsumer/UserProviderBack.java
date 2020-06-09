package com.fcd.userconsumer;

import org.springframework.stereotype.Component;

/**
 * @author fcd
 * @date 2020/6/5 16:38
 * @version 1.0
 */
@Component
public class UserProviderBack implements ConsumerApi{

    @Override public String alive() {
         return "alive方法降级了";
    }

    @Override public String getById(Integer id) {
        return "getById方法降级了";
    }
}
