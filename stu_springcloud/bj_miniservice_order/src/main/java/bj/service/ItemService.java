package bj.service;

import bj.entity.Item;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
@Service
public class ItemService {

    @Autowired
    private ItemFeignClient itemFeignClient;
    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") //容错
    public Item queryItemById(Long id){
        String serviceid = "bj-miniservice-item";  //服务id
        return itemFeignClient.queryItemById(id);
    }

//    @Autowired
//    private RestTemplate restTemplate;
//    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") //容错
//        public Item queryItemById(Long id){
//        String serviceid = "bj-miniservice-item";  //服务id
//        return restTemplate.getForObject("http://" + serviceid + "/item/" + id, Item.class);
//    }
    public Item queryItemByIdFallbackMethod(Long id){//请求失败执行的方法
        return new Item(id, "查询出错了", null, null, null);
    }

//    public Item queryItemById(Long id){
//        String serviceid = "bj-miniservice-item";  //服务id
//        List<ServiceInstance> list = discoveryClient.getInstances(serviceid);   //通过服务id获取服务列表
//        if (list.isEmpty()){
//            return null;
//        }
//        //此处单独获取一个实例
//        ServiceInstance serviceInstance = list.get(0);
//        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
//        return restTemplate.getForObject("http://" + url + "/item/" + id, Item.class);
//
//    }

//    //调用商品微服务提供的接口查询商品信息
//    public Item queryItemById(Long id){
//        String url = "http://localhost:7788/item/" + id;
//        return restTemplate.getForObject(url, Item.class);
//    }
}
