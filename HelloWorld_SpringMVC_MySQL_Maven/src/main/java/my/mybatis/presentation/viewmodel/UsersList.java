package my.mybatis.presentation.viewmodel;

import java.util.Collection;

import my.mybatis.domain.User;

import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a> &lt;skalicky.tomas@gmail.com&gt;
 */
@Component
public class UsersList {

	private Collection<User> users;

	public Collection<User> getUsers() {
		return this.users;
	}

	public void setUsers(final Collection<User> users) {
		this.users = users;
	}
}
