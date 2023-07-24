package	edu.Roma42.classes;
import	java.util.StringJoiner;

public class	University {
	private	String	name;
	private	String	city;
	private	int		year;
	private	int		studentNumber;

	public	University() {};

	public	University(String name, String city, int year, int studentNumber) {
		this.name = name;
		this.city = city;
		this.year = year;
		this.studentNumber = studentNumber;
	};

	public int	grow(int newStudents) {
		this.studentNumber += newStudents;
		return (this.studentNumber);
	}

	public int	shrink(int oldStudents) {
		if (oldStudents > this.studentNumber) {
			System.out.println("Invalid number of students");
			return (0);
		}
		this.studentNumber -= oldStudents;
		return (this.studentNumber);
	}

	@Override
	public String	toString() {
		return (new StringJoiner(", ", University.class.getSimpleName() + "|", "|")
				.add("name='" + this.name + "'")
				.add("city='" + this.city + "'")
				.add("year=" + this.year)
				.add("number of student='" + this.studentNumber)
				.toString()
				);
	}
}
