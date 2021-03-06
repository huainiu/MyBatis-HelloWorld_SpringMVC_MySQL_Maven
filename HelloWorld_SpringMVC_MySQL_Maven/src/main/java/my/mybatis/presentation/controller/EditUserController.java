package my.mybatis.presentation.controller;

import my.mybatis.domain.User;
import my.mybatis.persistence.UserMapper;
import my.mybatis.presentation.viewmodel.NewUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 *         &lt;skalicky.tomas@gmail.com&gt;
 */
@Controller
public class EditUserController extends UserController {

	private static final String BASE_URL = "/users/";
	private static final String VIEW_MODEL = "editUserModel";

	/**
	 * Constructor.
	 */
	@Autowired
	public EditUserController(UserMapper userMapper) {
		super(userMapper);
	}

	/**
	 * Initializes the form.
	 * 
	 * @param userId
	 *            The ID of the edited user.
	 */
	@RequestMapping(value = EditUserController.BASE_URL + "{userId}/edit/", method = RequestMethod.GET)
	public String initForm(@PathVariable int userId, ModelMap model) {

		NewUser viewModel = new NewUser();

		User user = this.userMapper.get(userId);
		viewModel.setUser(user);

		model.addAttribute(EditUserController.VIEW_MODEL, viewModel);
		return PageViews.EDIT.getViewName();
	}

	/**
	 * Updates date of the user with the given <code>userId</code>. No data
	 * validator is introduced.
	 * 
	 * @return The name of the view which is to be shown.
	 */
	@RequestMapping(value = EditUserController.BASE_URL + "{userId}/edit/", method = RequestMethod.POST)
	public String processSubmittedForm(
			@ModelAttribute(EditUserController.VIEW_MODEL) NewUser viewModel,
			SessionStatus status, @PathVariable int userId) {

		try {
			// Stores the modified user.
			this.userMapper.update(viewModel.getUser());

			// Clears the command object from the session.
			status.setComplete();

			// Returns the form success view.
			return UserController.REDIRECT_PREFIX + EditUserController.BASE_URL;

		} catch (DataAccessException e) {
			return PageViews.EDIT.getViewName();
		}
	}
}
