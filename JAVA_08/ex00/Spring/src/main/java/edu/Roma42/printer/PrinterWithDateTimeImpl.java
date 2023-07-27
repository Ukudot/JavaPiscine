package	edu.Roma42.printer;
import	edu.Roma42.renderer.Renderer;
import	java.time.LocalDateTime;

public	class	PrinterWithDateTimeImpl implements Printer {
	private Renderer		renderer;

	public	PrinterWithDateTimeImpl(Renderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public void	print(String text) {	
		this.renderer.render(LocalDateTime.now().toString() + " " + text);
	}
}
