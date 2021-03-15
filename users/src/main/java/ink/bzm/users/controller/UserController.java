package ink.bzm.users.controller;

import ink.bzm.users.pojo.QueryInfo;
import ink.bzm.users.pojo.User;
import ink.bzm.users.service.UserService;
import ink.bzm.users.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2021/3/3 17:20
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true")   //解决跨域，5.0.2后，allowCredentials默认为false了
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 查看所有用户
	 * @param queryInfo
	 * @return
	 */
	@GetMapping("/findAll")
	public Result findAllUser(QueryInfo queryInfo) {
		Result result = new Result();
		HashMap<String, Object> data = new HashMap<>();

		int numbers = userService.getUserCounts("%" + queryInfo.getQuery() + "%");// 获取数据总数
		int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();    //当前编号

		List<User> userList = userService.findAllUser("%" + queryInfo.getQuery() + "%", pageStart, queryInfo.getPageSize());

		data.put("numbers", numbers);
		data.put("userList", userList);

		result.setData(data);

		return result;
	}

	@RequestMapping("/userState")
	public Result updateUserState(@RequestParam("id") int  id,
								  @RequestParam("state") Boolean state){
		Result result = new Result();

		int i = userService.updateState(id, state);
		System.out.println("用户编号:"+id);
		System.out.println("用户状态:"+state);
		String str = i >0?"success":"error";

		result.setMsg(str);

		return result;
	}

	@RequestMapping("/deleteUser")
	public Result deleteUser(int id){
		Result result = new Result();

		System.out.println(id);
		int i = userService.deleteUser(id);

		String str = i >0?"success":"error";

		result.setMsg(str);

		return result;

	}
}
