package	edu.Roma42.game;
import	edu.Roma42.repositories.TilesRepository;
import	edu.Roma42.repositories.TilesRepositoryImpl;
import	edu.Roma42.services.MapService;
import	edu.Roma42.services.MapServiceImpl;
import	java.util.Scanner;

public class	Game {
	public static void	main(String args[]) {
		Scanner	scanner;
		int		move;

		scanner = new Scanner(System.in);
		TilesRepository	ts = new TilesRepositoryImpl("application-production.properties");
		MapService		ms= new MapServiceImpl(ts, 2, 11, 8);
		ms.display(true, false);
		System.out.println("Choose your path");
		System.out.print("-> ");
		try {
			move = scanner.nextInt();
			if (!scanner.nextLine().equals("") || move < 1 || move > 4) {
				System.out.println("\033[2A\033[2K\rInvalid input");	
				System.out.print("\033[2K-> ");	
				move = 0;
			}
		} catch (Exception e) {
			scanner.nextLine();
			System.out.println("\033[2A\033[2K\rInvalid input");	
			System.out.print("\033[2K-> ");	
			move = 0;
		}
		while (move == 0 || ms.update(move, scanner)) {
			if (move != 0) {
				ms.display(false, false);
				System.out.println("\033[2KChoose your path");
				System.out.print("\033[2K-> ");
				move = 0;
			} else {
				try {
					move = scanner.nextInt();
					if (!scanner.nextLine().equals("") || move < 1 || move > 4) {
						System.out.println("\033[2A\033[2K\rInvalid input");	
						System.out.print("\033[2K-> ");	
						move = 0;
					}
				} catch (Exception e) {
					scanner.nextLine();
					System.out.println("\033[2A\033[2K\rInvalid input");	
					System.out.print("\033[2K-> ");	
					move = 0;
				}
				if (move == 9) {
					System.out.println("PAVIDO");
					break ;
				}
			}
		}
	}
}
