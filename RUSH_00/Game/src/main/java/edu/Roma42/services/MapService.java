package	edu.Roma42.services;
import	java.util.Scanner;

public interface	MapService {
	boolean	update(int move, Scanner scanner);
	boolean	updateDev(int move, Scanner scanner);
	void	display(boolean first, boolean dev);
}
