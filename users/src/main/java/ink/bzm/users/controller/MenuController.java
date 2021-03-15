package ink.bzm.users.controller;

import ink.bzm.users.pojo.MainMenu;
import ink.bzm.users.service.MenuService;
import ink.bzm.users.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2021/3/10 10:57
 */
@RestController
@CrossOrigin(allowCredentials = "true")
public class MenuController {

	@Autowired
	MenuService MenuService;

	@RequestMapping("/menus")
	public Result getAllMenu(){
		Result result = new Result();
		List<MainMenu> mainMenus = MenuService.getMainMenus();

		result.setData(mainMenus);

		result.setMsg("成功");

//		return JSON.toJSONString(result);
		return result;
	}
}
