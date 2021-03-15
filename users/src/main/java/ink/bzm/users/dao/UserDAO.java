package ink.bzm.users.dao;

import ink.bzm.users.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2021/3/3 17:00
 */
@Mapper
public interface UserDAO {
	User loginMessage(@Param("username") String username, @Param("password") String password);

	List<User> findAllUser(@Param("username") String username, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

	int getUserCounts(@Param("username") String username);

	int saveUser(User user);

	int updateState(@Param("id")Integer id,@Param("state") Boolean state);

	 int deleteUser( @Param("id") int id);



}
