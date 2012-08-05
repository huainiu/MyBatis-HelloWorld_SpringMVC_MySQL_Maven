package my.mybatis.presentation.controller;

import my.mybatis.persistence.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 *         &lt;skalicky.tomas@gmail.com&gt;
 */
@Controller
public class DeleteUserController extends UserController {

	private static final String BASE_URL = "/users/";

	/**
	 * Constructor.
	 */
	@Autowired
	public DeleteUserController(UserMapper userMapper) {
		super(userMapper);
	}

	@RequestMapping(value = DeleteUserController.BASE_URL + "{userId}/delete/", method = RequestMethod.GET)
	public String delete(@PathVariable int userId) {
		try {
			// Deletes the user.
			this.userMapper.delete(userId);

			// Returns the form success view.
			return REDIRECT_PREFIX + DeleteUserController.BASE_URL;

		} catch (DataAccessException e) {
			return PageViews.LIST.getViewName();
		}
	}
}
