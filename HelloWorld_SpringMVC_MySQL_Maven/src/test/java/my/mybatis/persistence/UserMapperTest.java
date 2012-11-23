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
	
	private static final boolean FORCE_COMMIT = true;

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
			// see http://stackoverflow.com/questions/4372640/why-do-mybatis-insert-update-functions-now-require-a-commit-after-adding-fk-to-d
			sqlSession.commit(FORCE_COMMIT);
			final int userId = user.getId();
			Assert.assertTrue(userId > 0);

			user = userMapper.get(userId);
			Assert.assertNotNull(user);
			Assert.assertEquals(userNameV1, user.getName());

			final String userNameV2 = "Tomáš Skalický";
			user.setName(userNameV2);
			userMapper.update(user);
			sqlSession.commit(FORCE_COMMIT);

			user = userMapper.get(userId);
			Assert.assertNotNull(user);
			Assert.assertEquals(userNameV2, user.getName());

			userMapper.delete(user.getId());
			sqlSession.commit(FORCE_COMMIT);

			user = userMapper.get(userId);
			Assert.assertNull(user);
		} finally {
			sqlSession.close();
		}
	}
}
