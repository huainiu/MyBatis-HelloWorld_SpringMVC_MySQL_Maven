package my.mybatis.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import my.mybatis.config.SpringConfig;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 *         &lt;skalicky.tomas@gmail.com&gt;
 */
public abstract class MapperTest {

	protected static final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			SpringConfig.class);

	private static final String SQL_COMMENT_BEGINNING = "--";
	private static final String SQL_COMMAND_ENDING = ";";

	@BeforeClass
	public static void setUpDatabase() throws Exception {
		executeSqlStatementsInFile("src/test/resources/sql/create_schema.sql");
	}

	@AfterClass
	public static void tearDownDatabase() throws Exception {
		executeSqlStatementsInFile("src/test/resources/sql/drop_schema.sql");
	}

	private static void executeSqlStatementsInFile(final String filePath) throws Exception {
		synchronized (MapperTest.class) {
			DataSource dataSource = applicationContext.getBean(DataSource.class);
			Connection connection = dataSource.getConnection();

			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			StringBuilder sqlBuilder = new StringBuilder();
			for (String nextLine = reader.readLine(); nextLine != null; nextLine = reader
					.readLine()) {
				// Ignores comments.
				if (nextLine.indexOf(SQL_COMMENT_BEGINNING) == 0) {
					continue;
				}

				sqlBuilder.append(nextLine).append("\n");

				// Executes all complete statements.
				while (sqlBuilder.indexOf(SQL_COMMAND_ENDING) >= 0) {
					final String sql = sqlBuilder.toString();
					final int commandEndIndex = sql.indexOf(SQL_COMMAND_ENDING) + 1;
					final String sqlStatement = sql.substring(0, commandEndIndex);
					executeSql(sqlStatement, connection);
					sqlBuilder.delete(0, commandEndIndex);
				}
			}
			reader.close();
			connection.close();
		}
	}

	private static void executeSql(String sqlStatement, Connection connection) throws Exception {
		Statement statement = connection.createStatement();
		statement.execute(sqlStatement);
	}
}
