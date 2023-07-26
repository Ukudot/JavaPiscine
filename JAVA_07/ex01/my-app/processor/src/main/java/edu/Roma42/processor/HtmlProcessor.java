package	edu.Roma42.processors;
import	edu.Roma42.annotations.HtmlForm;
import	edu.Roma42.annotations.HtmlInput;
import	java.util.Set;
import	java.util.Random;
import	javax.tools.FileObject;
import	javax.tools.Diagnostic;
import	javax.tools.StandardLocation;
import	java.io.Writer;
import	java.io.PrintWriter;
import	java.io.IOException;
import	javax.lang.model.element.AnnotationMirror;
import	javax.lang.model.element.Element;
import	javax.lang.model.element.TypeElement;
import	javax.lang.model.element.ExecutableElement;
import	javax.lang.model.SourceVersion;
import	javax.annotation.processing.Filer;
import	javax.annotation.processing.Processor;
import	javax.annotation.processing.AbstractProcessor;
import	javax.annotation.processing.RoundEnvironment;
import	javax.annotation.processing.ProcessingEnvironment;
import	javax.annotation.processing.SupportedAnnotationTypes;
import	javax.annotation.processing.SupportedSourceVersion;
import	com.google.auto.service.AutoService;

@SupportedAnnotationTypes("edu.Roma42.annotations.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class	HtmlProcessor extends AbstractProcessor {
	private	Filer					filer;
	private ProcessingEnvironment	processingEnv;
	private	Writer					writer;
	private	PrintWriter				printWriter;

	@Override
	public void	init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		this.filer = processingEnv.getFiler();
		this.processingEnv = processingEnv;
		try {
			FileObject	f = this.filer.createResource(StandardLocation.CLASS_OUTPUT, "", "user_form.html", null);
			this.writer = f.openWriter();
			this.printWriter = new PrintWriter(this.writer);
		} catch (IOException e) {
			this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.toString());
		}
	}

	@Override
	public boolean	process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (Element elem : roundEnv.getElementsAnnotatedWith(HtmlInput.class)) {
			//FileObject	f = this.filer.createResource(StandardLocation.CLASS_OUTPUT, "", ".html", null);
			//this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "prova");
			//Writer	w = f.openWriter();
			//PrintWriter pw = new PrintWriter(w);
			for (AnnotationMirror annMir : elem.getAnnotationMirrors()) {
				for (ExecutableElement execElem : annMir.getElementValues().keySet()) {
					this.printWriter.println(execElem.getSimpleName());	
				}
			}
			this.printWriter.println(elem.asType().toString());
			this.printWriter.println(elem.getSimpleName());
		}
		try {
			this.printWriter.flush();
			this.writer.close();
		} catch (IOException e) {
			this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.toString());
		}
		return (true);
	}
}
