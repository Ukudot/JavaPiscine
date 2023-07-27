package	edu.Roma42.preProcessor;

public class	PreProcessorToLowerImpl implements PreProcessor {
	public	PreProcessorToLowerImpl() {};

	@Override
	public String	preprocess(String text) {
		return (text.toLowerCase());
	}
}
