package ink.bzm.users.service;

import ink.bzm.users.dao.UserDAO;
import ink.bzm.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2020/7/24 10:22
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
	UserDAO userDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAllUser(String username, int pageStart, int pageSize) {
        return userDAO.findAllUser(username,pageStart,pageSize);
    }

    @Override
    public int getUserCounts(String username) {
        return userDAO.getUserCounts(username);
    }

    @Override
    public int updateState(Integer id, Boolean state) {
        return userDAO.updateState(id, state);
    }

    @Override
    public int deleteUser(int id) {
        return userDAO.deleteUser(id);
    }

    @Override
    public User loginMessage(String username, String password) {
        return userDAO.loginMessage(username, password);
    }

    @Override
    public int saveUser(User user) {
        return userDAO.saveUser(user);
    }


}
