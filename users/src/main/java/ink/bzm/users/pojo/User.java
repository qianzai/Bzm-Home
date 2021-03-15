package ink.bzm.users.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * t_users 实体类——用户
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2021/3/3 16:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {
	private String id;
	private String username;
	private String password;
	private String email;
	private String role;
	private boolean state;
	@JsonFormat(pattern = "yyyy-MM-dd")     //日期格式化
	private Date regdate;

}
