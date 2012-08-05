package my.mybatis.domain;

import java.io.Serializable;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 *         &lt;skalicky.tomas@gmail.com&gt;
 */
public class User implements Serializable {

	private static final long serialVersionUID = -7664090302482664413L;

	private int id;
	private String name = null;
	private String standard = null;
	private int age;
	private String sex = null;

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(final String standard) {
		this.standard = standard;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(final int age) {
		this.age = age;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(final String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		StringBuffer userBuilder = new StringBuffer("User [id=");
		userBuilder.append(this.id).append(", name=").append(this.name).append(", standard=");
		userBuilder.append(this.standard).append(", age=").append(this.age).append(", sex=");
		userBuilder.append(this.sex).append("]");
		return userBuilder.toString();
	}
}
