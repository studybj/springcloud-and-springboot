package bj.servoce;
import bj.entity.Item;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ItemService {
    public static final Map<Long, Item> MAP = new HashMap<>();
    static { //准备一些静态数据
        MAP.put(1l, new Item(1l, "商品标题1", "http://图片1", "商品描述1", 1l));
        MAP.put(2l, new Item(2l, "商品标题2", "http://图片2", "商品描述2", 2l));
        MAP.put(3l, new Item(3l, "商品标题3", "http://图片3", "商品描述3", 3l));
        MAP.put(4l, new Item(4l, "商品标题4", "http://图片4", "商品描述4", 4l));
        MAP.put(5l, new Item(5l, "商品标题5", "http://图片5", "商品描述5", 5l));
        MAP.put(6l, new Item(6l, "商品标题6", "http://图片6", "商品描述6", 6l));
    }
    /**
     * 模拟实现根据id获取商品信息
     */
    public Item queryItemById(Long id){
        return MAP.get(id);
    }
}
