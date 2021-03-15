package ink.bzm.users.dao;

import ink.bzm.users.pojo.MainMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: buzhengmiao
 * @time: 2021/3/10 10:29
 */
@Mapper
public interface MenuDAO {
	List<MainMenu> getMainMenus();
}
