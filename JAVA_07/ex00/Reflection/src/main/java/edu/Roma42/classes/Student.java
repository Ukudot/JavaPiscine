package	edu.Roma42.classes;
import	java.util.Random;
import	java.util.StringJoiner;

public class	Student {
	private	String	name;
	private String	surname;
	private String	department;
	private	Integer	age;
	private Integer	year;

	public	Student() {}

	public	Student(String name, String surname, String department, Integer age, Integer year) {
		this.name = name;
		this.surname = surname;
		this.department = department;
		this.age = age;
		this.year = year;
	}

	public int	makeExam() {
		return (new Random().nextInt(30) + 1);
	}

	public String	changeDepartment(String newDepartment) {
		this.department = newDepartment;
		return (this.department);
	}

	@Override
	public String	toString() {
		return (new StringJoiner(", ", Student.class.getSimpleName() + "|", "|")
				.add("name='" + this.name + "'")
				.add("surname='" + this.surname + "'")
				.add("department='" + this.department + "'")
				.add("age=" + this.age)
				.add("year=" + this.year)
				.toString()
				);
	}
}
