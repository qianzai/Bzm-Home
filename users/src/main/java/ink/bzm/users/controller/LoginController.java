package ink.bzm.users.controller;

import ink.bzm.users.pojo.User;
import ink.bzm.users.service.UserService;
import ink.bzm.users.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.SocketTimeoutException;
import java.util.Date;

/**
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2021/3/5 10:35
 */
@RestController
@CrossOrigin(allowCredentials = "true")
public class LoginController {
	@Autowired
	UserService UserService;

	@ResponseBody
	@RequestMapping("/test")
	public Result hello() {
		Result result = new Result();

		result.setMsg("登录成功");
		result.setStatus(true);

		return result;
	}

	/**
	 * 登录功能 控制器
	 * @param user
	 * @return
	 */
	@RequestMapping("/login")
	public Result userLogin(@RequestBody User user) {
		Result result = new Result();

		User user1 = UserService.loginMessage(user.getUsername(), user.getPassword());

		System.out.println(user.getUsername());

		System.out.println("user1 = " + user1);

		if (user1 != null) {
			result.setMsg("登录成功");
			result.setStatus(true);
		} else {
			result.setMsg("登录失败");
			result.setStatus(false);
		}
		return result;
	}

	/**
	 * 注册功能 控制器
	 * @param user
	 * @return
	 */
	@RequestMapping("/register")
	public Result register(@RequestBody User user) {
		Result result = new Result();


		if (user.getRole()==null){
			user.setRole("普通用户");
		}

		System.out.println("user.getRole() = " + user.getRole());

		/*设置用户为启动状态*/
		user.setState(true);
		/*设置注册时间为当前时间*/
		user.setRegdate(new Date());

		int i = UserService.saveUser(user);

		System.out.println("user.toString() = " + user.toString());
		if (i==1){
			//创建成功
			result.setMsg("创建成功！");
		}else {
			//创建失败
			result.setMsg("创建失败！");
			result.setStatus(false);
		}

		return result;

	}

}
