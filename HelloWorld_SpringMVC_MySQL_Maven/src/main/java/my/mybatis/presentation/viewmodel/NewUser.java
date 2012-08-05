package my.mybatis.presentation.viewmodel;

import my.mybatis.domain.User;

import org.springframework.stereotype.Component;

/**
 * Holder of user's data when a new user is being created.
 * 
 * @author Tomas Skalicky
 */
@Component
public class NewUser {

	private User user = null;

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
