package	edu.Roma42.models;
import	edu.Roma42.annotations.*;
import	java.util.StringJoiner;
import	java.util.Objects;

@OrmEntity(table = "simple_user")
public class	User {
	@OrmColumnId
	private Long	id;
	@OrmColumn(name = "first_name", length = 10)
	private String	firstName;
	@OrmColumn(name = "last_name", length = 10)
	private String	lastName;
	@OrmColumn(name = "age")
	private Integer	age;
	@OrmColumn(name = "level")
	private Double	level;
	@OrmColumn(name = "test")
	private Boolean	test;

	public	User() {}

	public	User(Long id, String firstName, String lastName, Integer age, Double level, Boolean test) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.level = level;
		this.test = test;
	}

	public Long	getId() {
		return (this.id);
	}

	public String	getFirstName() {
		return (this.firstName);
	}

	public String	getLastName() {
		return (this.lastName);
	}

	public Integer	getAge() {
		return (this.age);
	}

	public Double	getLevel() {
		return (this.level);
	}

	public Boolean	getTest() {
		return (this.test);
	}

	public void	setId(Long id) {
		this.id = id;
	}

	public void	setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void	setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void	setAge(Integer age) {
		this.age = age;
	}

	public void	setLevel(Double level) {
		this.level = level;
	}

	public void	setTest(Boolean test) {
		this.test = test;
	}

	@Override
	public String	toString() {
		StringJoiner	stringJoiner;

		stringJoiner = new StringJoiner(", ", "User|", "|")
			.add("id = " + this.id)
			.add("first name = " + this.firstName)
			.add("last name = " + this.lastName)
			.add("age = " + this.age)
			.add("level = " + this.level)
			.add("test = " + this.test);
		return (stringJoiner.toString());
	}

	@Override
	public boolean	equals(Object o) {
		User	tmpUser;

		if (this == o) {
			return (true);
		} else if (!(o instanceof User)) {
			return (false);
		}
		tmpUser = (User) o;
		return (this.id.equals(tmpUser.getId())
				&& this.firstName.equals(tmpUser.getFirstName())
				&& this.lastName.equals(tmpUser.getLastName())
				&& this.age.equals(tmpUser.getAge())
				&& this.level.equals(tmpUser.getAge())
				&& this.test.equals(tmpUser.getAge()));
	}

	@Override
	public int	hashCode() {
		return (Objects.hash(this.id, this.firstName, this.lastName, this.age, this.level, this.test));
	}
}
