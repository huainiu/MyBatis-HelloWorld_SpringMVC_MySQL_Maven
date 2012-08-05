package my.mybatis.presentation.controller;

import my.mybatis.domain.User;
import my.mybatis.persistence.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Parent of all controllers which handle all operations concerning
 * administration of {@link User}s.
 * 
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a> &lt;skalicky.tomas@gmail.com&gt;
 */
@Controller
abstract class UserController {

	protected static final String REDIRECT_PREFIX = "redirect:";
	protected UserMapper userMapper = null;

	/**
	 * Constructor.
	 */
	@Autowired
	UserController(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
}
