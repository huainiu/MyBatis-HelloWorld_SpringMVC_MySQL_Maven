package my.mybatis.presentation.controller;

import my.mybatis.persistence.UserMapper;
import my.mybatis.presentation.viewmodel.UsersList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 *         &lt;skalicky.tomas@gmail.com&gt;
 */
@Controller
public class ListUsersController extends UserController {

	private static final String BASE_URL = "/users/";
	private static final String VIEW_MODEL = "usersListModel";

	/**
	 * Constructor.
	 */
	@Autowired
	public ListUsersController(UserMapper userMapper) {
		super(userMapper);
	}

	/**
	 * Initializes the page with all users.
	 */
	@RequestMapping(value = ListUsersController.BASE_URL, method = RequestMethod.GET)
	public String initPage(ModelMap model) {

		final UsersList viewModel = new UsersList();
		viewModel.setUsers(this.userMapper.getAll());

		model.addAttribute(ListUsersController.VIEW_MODEL, viewModel);
		return PageViews.LIST.getViewName();
	}
}
