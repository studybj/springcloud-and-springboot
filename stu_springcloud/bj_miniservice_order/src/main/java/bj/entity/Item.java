package bj.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long id;
    private String title;
    private String pic;
    private String desc;
    private Long price;
}
