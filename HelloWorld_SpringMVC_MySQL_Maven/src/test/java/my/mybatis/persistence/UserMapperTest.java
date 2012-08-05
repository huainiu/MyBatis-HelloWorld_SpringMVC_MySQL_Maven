package my.mybatis.persistence;

import my.mybatis.domain.User;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 *         &lt;skalicky.tomas@gmail.com&gt;
 */
public class UserMapperTest extends MapperTest {

	@Test
	public void testNullUserName() {
		final SqlSession sqlSession = applicationContext.getBean(SqlSessionFactory.class)
				.openSession();
		final UserMapper userMapper = applicationContext.getBean(UserMapper.class);

		try {
			User user = new User();
			user.setName(null);
			userMapper.create(user);
			Assert.fail("User's name must be set up.");
		} catch (DataIntegrityViolationException e) {
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testCreateRetrieveUpdateDelete() {
		final SqlSession sqlSession = applicationContext.getBean(SqlSessionFactory.class)
				.openSession();
		final UserMapper userMapper = applicationContext.getBean(UserMapper.class);

		try {
			User user = new User();
			final String userNameV1 = "Tomas Skalicky";
			user.setId(-1);
			user.setName(userNameV1);
			userMapper.create(user);
			final int userId = user.getId();
			Assert.assertTrue(userId > 0);

			user = userMapper.get(userId);
			Assert.assertNotNull(user);
			Assert.assertEquals(userNameV1, user.getName());

			final String userNameV2 = "Tomáš Skalický";
			user.setName(userNameV2);
			userMapper.update(user);

			user = userMapper.get(userId);
			Assert.assertNotNull(user);
			Assert.assertEquals(userNameV2, user.getName());

			userMapper.delete(user.getId());

			user = userMapper.get(userId);
			Assert.assertNull(user);
		} finally {
			sqlSession.close();
		}
	}
}
