package ink.bzm.users.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 查询信息
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2021/3/12 17:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class QueryInfo {
	private String query;	//查询信息 username
	private int pageNum=1;
	private int pageSize=1;
}
