package	edu.Roma42.annotations.processors;
import	java.util.Set;
import	javax.tools.JavaFileObject;
import	javax.tools.Diagnostic;
import	javax.tools.Diagnostic;
import	java.io.Writer;
import	java.io.PrintWriter;
import	java.io.IOException;
import	javax.lang.model.element.TypeElement;
import	javax.lang.model.SourceVersion;
import	javax.annotation.processing.Filer;
import	javax.annotation.processing.Processor;
import	javax.annotation.processing.AbstractProcessor;
import	javax.annotation.processing.RoundEnvironment;
import	javax.annotation.processing.ProcessingEnvironment;
import	javax.annotation.processing.SupportedAnnotationTypes;
import	javax.annotation.processing.SupportedSourceVersion;
import	com.google.auto.service.AutoService;

@SupportedAnnotationTypes("edu.Roma42.Annotations.annotations.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class	HtmlProcessor extends AbstractProcessor {
	private	Filer					filer;
	private ProcessingEnvironment	processingEnv;

	@Override
	public void	init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		this.filer = processingEnv.getFiler();
		this.processingEnv = processingEnv;
	}

	@Override
	public boolean	process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		try {
			JavaFileObject	f = this.filer.createClassFile("user_form.html");
			this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "prova");
			Writer	w = f.openWriter();
			PrintWriter pw = new PrintWriter(w);
			pw.println("prova");
			pw.flush();
			w.close();
		} catch (IOException e) {
			this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.toString());
		}
		return (true);
	}
}
