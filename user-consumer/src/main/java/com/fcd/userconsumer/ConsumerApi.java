package com.fcd.userconsumer;

import com.fcd.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author fcd
 * @date 2020/6/4 11:35
 * @version 1.0
 */

@FeignClient(name = "user-provider", fallback = UserProviderBack.class)
public interface ConsumerApi extends UserApi {

}
