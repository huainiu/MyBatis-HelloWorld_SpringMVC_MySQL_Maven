package my.mybatis.persistence;

import java.util.List;

import my.mybatis.domain.User;

/**
 * This interface is implemented by a proxy implementation provided by MyBatis
 * base on UserMapper.xml
 * 
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 *         &lt;skalicky.tomas@gmail.com&gt;
 */
public interface UserMapper {

	public User get(int id);

	public List<User> getAll();

	public void create(User user);

	public void update(User user);

	public void delete(int id);
}
