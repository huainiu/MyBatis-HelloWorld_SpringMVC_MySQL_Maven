package my.mybatis.config;

import java.beans.PropertyVetoException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.sql.DataSource;

import my.mybatis.persistence.UserMapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Creates all necessary beans.
 * 
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 *         &lt;skalicky.tomas@gmail.com&gt;
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
public class SpringConfig {

	/**
	 * Used together with the {@link PropertySource} annotation of this class.
	 */
	@Autowired
	private Environment environment;

	private <T> T getMapper(Class<T> clazz) {
		MapperFactoryBean<T> factory = new MapperFactoryBean<>();
		factory.setMapperInterface(clazz);
		factory.setSqlSessionFactory(this.sqlSessionFactory());
		factory.afterPropertiesSet();
		try {
			return factory.getObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Bean
	public UserMapper userMapper() {
		return this.getMapper(UserMapper.class);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setConfigLocation(new FileSystemResource("src/main/webapp/WEB-INF/mybatis/config.xml"));
		factory.setDataSource(this.dataSource());
		try {
			return factory.getObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Creates a {@link Bean} for a MySQL database {@link DataSource}.
	 */
	@Bean
	public DataSource dataSource() {
		final ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(this.environment.getProperty("jdbc.driverClassName"));
			dataSource.setJdbcUrl(this.environment.getProperty("jdbc.url"));
			dataSource.setUser(this.environment.getProperty("jdbc.username"));
			dataSource.setPassword(this.environment.getProperty("jdbc.password"));
			dataSource.setLogWriter(new PrintWriter(System.out));
			return dataSource;
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
