package bj.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class Order {
    private String id;
    private Long userId;
    private Date createDate;
    private Date updateDate;
    private List<OrderDetail> details;

    public Order(String id, Long userId, Date createDate, Date updateDate) {
        this.id = id;
        this.userId = userId;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
