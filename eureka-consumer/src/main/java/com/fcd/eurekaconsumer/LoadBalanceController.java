package com.fcd.eurekaconsumer;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * @author fcd
 * @date 2020/6/2 20:53
 * @version 1.0
 */
@RestController
public class LoadBalanceController {

    @Autowired DiscoveryClient client;

    @Autowired EurekaClient eurekaClient;

    @Autowired DiscoveryClient discoveryClient;

    /**
     * 负载均衡
     */
    @Autowired LoadBalancerClient loadBalancerClient;

    @Autowired RestTemplate restTemplate;

    @GetMapping("/getInstanceLb")
    public Object getInstanceLb() {

        //ribbon完成客户端的负载均衡，过滤down的节点
        ServiceInstance instance = loadBalancerClient.choose("provider");

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";

        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 手动负载均衡
     * @return
     */
    @GetMapping("/getInstanceDiy")
    public Object getInstanceDiy() {

        List<ServiceInstance> instanceList = discoveryClient.getInstances("provider");

        //todo:自定义轮询算法
        int nextInt = new Random().nextInt(instanceList.size());
        ServiceInstance instance = instanceList.get(nextInt);

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";

        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/getInstanceAutoUrl")
    public Object getInstanceAutoUrl() {

        //自动处理url
        String url = "http://provider/getHi";

        return restTemplate.getForObject(url, String.class);
    }

}
