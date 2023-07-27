package	edu.Roma42.printer;
import	edu.Roma42.renderer.Renderer;

public	class	PrinterWithPrefixImpl implements Printer {
	private Renderer		renderer;
	private	String			prefix;

	public	PrinterWithPrefixImpl(Renderer renderer) {
		this.renderer = renderer;
		this.prefix = "";
	}

	public String	getPrefix() {
		return (this.prefix);
	}

	public void	setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public void	print(String text) {
		this.renderer.render(this.prefix + text);
	}
}
