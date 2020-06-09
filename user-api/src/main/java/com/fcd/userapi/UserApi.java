package com.fcd.userapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author fcd
 * @date 2020/6/4 15:54
 * @version 1.0
 */

@RequestMapping("/User")
public interface UserApi {

    /**
     * 获取状态
     * @return ok
     */
    @GetMapping("/alive")
    public String alive();

    /**
     * 根据id获取信息
     * @param id id
     * @return 信息
     */
    @GetMapping("/getById")
    public String getById(@RequestParam("id") Integer id);
}
