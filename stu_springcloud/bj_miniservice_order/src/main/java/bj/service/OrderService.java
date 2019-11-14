package bj.service;

import bj.entity.Item;
import bj.entity.Order;
import bj.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    public static final Map<String, Order> MAP = new HashMap<>();
    static { //准备一些静态数据
        Order order = new Order();
        order.setId("1234567890");
        order.setCreateDate(new Date());
        order.setUpdateDate(order.getCreateDate());
        order.setUserId(1l);
        List<OrderDetail> details = new ArrayList<>();

        Item item1 = new Item();//此处并没有商品数据，需调用商品微服务获取数据
        item1.setId(1l);
        details.add(new OrderDetail(order.getId(), item1));

        Item item2 = new Item();//此处并没有商品数据，需调用商品微服务获取数据
        item2.setId(2l);
        details.add(new OrderDetail(order.getId(), item2));

        Item item3 = new Item();//此处并没有商品数据，需调用商品微服务获取数据
        item3.setId(3l);
        details.add(new OrderDetail(order.getId(), item3));
        order.setDetails(details);
        MAP.put(order.getId(), order);
    }
    @Autowired
    private ItemService itemService;
    /**
     * 模拟实现根据id获取订单信息
     */
    public Order queryOrderById(String id){
        Order order = MAP.get(id);
        List<OrderDetail> details = order.getDetails();
        for (OrderDetail detail : details){
            Item item = itemService.queryItemById(detail.getItem().getId());
            if (item == null){
                continue;
            }
            detail.setItem(item);
        }
        return order;
    }
}
