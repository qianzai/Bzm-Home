package ink.bzm.users.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * submenu表 实体类——分支导航
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2021/3/10 10:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class SubMenu {
	private int id;
	private String title;
	private String path;
	private String icons;
}
