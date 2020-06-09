package com.fcd.eurekaconsumer;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EurekaServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author fcd
 * @date 2020/6/1 20:10
 * @version 1.0
 */
@RestController
public class MainController {

    @Autowired DiscoveryClient client;

    @Autowired EurekaClient eurekaClient;

    /**
     * 负载均衡
     */
    @Autowired LoadBalancerClient loadBalancerClient;

    @GetMapping("/getHi")
    public String getHi() {
        return "Hi";
    }

    @GetMapping("/client")
    public String client() {
        List<String> services = client.getServices();
        StringBuilder sb = new StringBuilder();
        for (String str : services) {
            sb.append(str);
        }
        return sb.toString();
    }

    @GetMapping("/client2")
    public Object client2() {
        return client.getInstances("provider");
    }

    @GetMapping("/getInstanse")
    public Object getInstanse() {
        //getInstanceById 具体服务
        //List<InstanceInfo> instances = eurekaClient.getInstancesById("YCKJ2791.ad.yc:provider:80");

        //获取服务列表
        List<InstanceInfo> instances = eurekaClient.getInstancesByVipAddress("provider", false);
        String url = "init";
        String res = "res";
        if (instances.size() > 0) {
            // 服务
            InstanceInfo instanceInfo = instances.get(0);
            if (instanceInfo.getStatus().equals(InstanceInfo.InstanceStatus.UP)) {
                url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/getHi";

                RestTemplate restTemplate = new RestTemplate();

                res = restTemplate.getForObject(url, String.class);
            }
        }
        return res;
    }

    @GetMapping("/getInstanceWithRibbon")
    public Object getInstanceWithRibbon() {

        //ribbon完成客户端的负载均衡，过滤down的节点
        ServiceInstance instance = loadBalancerClient.choose("provider");

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, String.class);
    }
}
