package	edu.Roma42.renderer;
import	edu.Roma42.preProcessor.PreProcessor;

public class	RendererErrImpl implements Renderer {
	private PreProcessor	preProcessor;

	public	RendererErrImpl(PreProcessor preProcessor) {
		this.preProcessor = preProcessor;
	}

	@Override
	public void	render(String text) {
		System.err.println(this.preProcessor.preprocess(text));
	}
}
