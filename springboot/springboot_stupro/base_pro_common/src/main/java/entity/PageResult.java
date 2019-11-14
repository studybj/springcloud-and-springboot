package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    /**总记录数*/
    private long total;
    /**记录信息*/
    private List<T> rows;
}
