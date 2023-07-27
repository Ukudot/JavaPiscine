package	edu.Roma42.preProcessor;

public class	PreProcessorToUpperImpl implements PreProcessor {
	public	PreProcessorToUpperImpl() {};

	@Override
	public String	preprocess(String text) {
		return (text.toUpperCase());
	}
}
