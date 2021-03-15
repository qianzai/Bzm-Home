package ink.bzm.users.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * mainmenu表 实体类——主导航
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2021/3/10 10:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class MainMenu {
	private int id;
	private String title;
	private String path;
	private String icons;
	List<SubMenu> slist;
}
