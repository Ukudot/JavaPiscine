package	edu.Roma42.app;
import	edu.Roma42.printer.*;
import	edu.Roma42.renderer.*;
import	edu.Roma42.preProcessor.*;

public class	App {
	public static void	main(String args[]) {
		PreProcessor preProcessor = new PreProcessorToUpperImpl();
		Renderer renderer = new RendererErrImpl(preProcessor);
		PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
		printer.setPrefix ("Prefix ");
		printer.print ("Hello!");
	}
}
