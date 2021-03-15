package ink.bzm.users.service;

import ink.bzm.users.pojo.User;

import java.util.List;

/**
 * @author: buzhengmiao
 * @time: 2020/7/24 10:21
 */
public interface UserService {

	User loginMessage(String username, String password);

	int saveUser(User user);

	List<User> findAllUser(String username, int pageStart, int pageSize);

	int getUserCounts(String username);

	int updateState(Integer id, Boolean state);

	int deleteUser(int id);


}
