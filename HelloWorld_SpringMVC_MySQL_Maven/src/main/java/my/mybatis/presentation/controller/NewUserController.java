package my.mybatis.presentation.controller;

import my.mybatis.domain.User;
import my.mybatis.persistence.UserMapper;
import my.mybatis.presentation.viewmodel.NewUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 *         &lt;skalicky.tomas@gmail.com&gt;
 */
@Controller
public class NewUserController extends UserController {

	private static final String BASE_URL = "/users/";
	private static final String VIEW_MODEL = "newUserModel";

	/**
	 * Constructor.
	 */
	@Autowired
	public NewUserController(UserMapper userMapper) {
		super(userMapper);
	}

	/**
	 * Initializes the form.
	 */
	@RequestMapping(value = NewUserController.BASE_URL + "new/", method = RequestMethod.GET)
	public String initForm(ModelMap model) {

		final NewUser viewModel = new NewUser();
		viewModel.setUser(new User());

		model.addAttribute(NewUserController.VIEW_MODEL, viewModel);
		return PageViews.NEW.getViewName();
	}

	/**
	 * New user is stored to a repository. No data validator is introduced.
	 * 
	 * @return The name of the view which is to be shown.
	 */
	@RequestMapping(value = NewUserController.BASE_URL + "new/", method = RequestMethod.POST)
	public String processSubmittedForm(
			@ModelAttribute(NewUserController.VIEW_MODEL) NewUser viewModel, SessionStatus status) {

		try {
			// Stores the new user.
			this.userMapper.create(viewModel.getUser());

			// Clears the command object from the session.
			status.setComplete();

			// Returns the form success view.
			return UserController.REDIRECT_PREFIX + NewUserController.BASE_URL;

		} catch (DataAccessException e) {
			return PageViews.NEW.getViewName();
		}
	}
}
