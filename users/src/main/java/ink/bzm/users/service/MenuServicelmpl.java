package ink.bzm.users.service;

import ink.bzm.users.dao.MenuDAO;
import ink.bzm.users.pojo.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2021/3/10 10:50
 */
@Service
@Transactional
public class MenuServicelmpl implements MenuService {

	@Autowired
	MenuDAO menuDAO;

	@Override
	public List<MainMenu> getMainMenus() {
		return menuDAO.getMainMenus();
	}
}
