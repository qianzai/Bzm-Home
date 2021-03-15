package ink.bzm.users;

import ink.bzm.users.pojo.User;
import ink.bzm.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2021/3/3 17:16
 */
@SpringBootTest
public class UsersApplicationTests {
	@Autowired
	UserService userService;

	Logger logger = LoggerFactory.getLogger(getClass());


	@Test
	void Test(){
		logger.trace("这是trace日志...");
		logger.debug("这是debug日志...");
		//SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
		logger.info("这是info日志...");
		logger.warn("这是warn日志...");
		logger.error("这是error日志...");
	}

	@Test
	void testFindAll() {
//		userService.findAllUser().forEach(user -> System.out.println("user = " + user));
	}

	@Test
	void testloginMessage() {
		System.out.println(userService.loginMessage("admin", "123456"));
	}

	@Test
	void saveUser(){
		User user = new User();

		user.setUsername("Bzm1");
		user.setPassword("123456");
		user.setEmail("bzm@qq.com");
		user.setRole("gly");
		user.setState(true);
		user.setRegdate(new Date());

		System.out.println(userService.saveUser(user));

	}

	@Test
	void deleteUser(){
		userService.deleteUser(69);
	}

}
