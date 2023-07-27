package	edu.Roma42.renderer;
import	edu.Roma42.preProcessor.PreProcessor;

public class	RendererStandardImpl implements Renderer {
	private PreProcessor	preProcessor;

	public	RendererStandardImpl(PreProcessor preProcessor) {
		this.preProcessor = preProcessor;
	}

	@Override
	public void	render(String text) {
		System.out.println(this.preProcessor.preprocess(text));
	}
}
