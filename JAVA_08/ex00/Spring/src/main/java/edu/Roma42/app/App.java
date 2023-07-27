package	edu.Roma42.app;
import	edu.Roma42.printer.*;
import	edu.Roma42.renderer.*;
import	edu.Roma42.preProcessor.*;
import	org.springframework.context.ApplicationContext;
import	org.springframework.context.support.ClassPathXmlApplicationContext;

public class	App {
	public static void	main(String args[]) {
		ApplicationContext	context = new ClassPathXmlApplicationContext("context.xml");
		Printer	printer = context.getBean("printerWithPrefix", Printer.class);
		printer.setPrefix("Prefix");
		printer.print("Hello!");
	}
}
