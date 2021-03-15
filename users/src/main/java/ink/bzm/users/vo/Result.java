package ink.bzm.users.vo;

import lombok.Data;

/**
 * 传给前端的 Result
 * @author: buzhengmiao    www.bzm.ink
 * @time: 2020/7/25 14:36
 */
@Data
public class Result {
    private Boolean status = true;
    private String msg;
    private Object data;

}
