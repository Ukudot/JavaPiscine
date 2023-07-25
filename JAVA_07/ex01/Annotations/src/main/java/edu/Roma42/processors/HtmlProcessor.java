package	edu.Roma42.Annotations.processors;
import	java.util.Set;
import	javax.lang.model.element.TypeElement;
import	javax.lang.model.SourceVersion;
import	javax.annotation.processing.AbstractProcessor;
import	javax.annotation.processing.RoundEnvironment;
import	javax.annotation.processing.SupportedAnnotationTypes;
import	javax.annotation.processing.SupportedSourceVersion;
import	com.google.auto.service.AutoService;

@SupportedAnnotationTypes("edu.Roma42.Annotations.annotations.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(AbstractProcessor.class)
public class	HtmlProcessor extends AbstractProcessor {
	@Override
	public boolean	process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {return (true);}

}
