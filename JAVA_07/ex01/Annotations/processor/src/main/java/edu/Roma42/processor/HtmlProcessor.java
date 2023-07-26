package	edu.Roma42.processors;
import	edu.Roma42.annotations.HtmlForm;
import	edu.Roma42.annotations.HtmlInput;
import	java.util.Set;
import	java.util.StringJoiner;
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
import	javax.lang.model.type.TypeMirror;
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
	}

	@Override
	public boolean	process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		StringJoiner	stringJoiner;
		String			filename = null;

		for (Element elem : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
			TypeMirror	form = elem.asType();
			stringJoiner = new StringJoiner(", ", "<form ", ">");
			for (AnnotationMirror annMir : elem.getAnnotationMirrors()) {
				for (ExecutableElement execElem : annMir.getElementValues().keySet()) {
					if (execElem.getSimpleName().toString().equals("fileName")) {
						filename = annMir.getElementValues().get(execElem).toString();
						filename = filename.substring(1, filename.length() - 1);
					} else {
						stringJoiner.add(execElem.getSimpleName() + " = " + annMir.getElementValues().get(execElem));
					}
				}
			}
			try {
				FileObject	f = this.filer.createResource(StandardLocation.CLASS_OUTPUT, "", filename, null);
				this.writer = f.openWriter();
				this.printWriter = new PrintWriter(this.writer);
			} catch (IOException e) {
				this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.toString());
			}
			this.printWriter.println(stringJoiner.toString());	
			for (Element elem2 : elem.getEnclosedElements()) {
				if (elem2.getKind().isField()) {
					stringJoiner = new StringJoiner(", ", "<input ", ">");
					for (AnnotationMirror annMir : elem2.getAnnotationMirrors()) {
						for (ExecutableElement execElem : annMir.getElementValues().keySet()) {
							stringJoiner.add(execElem.getSimpleName() + " = " + annMir.getElementValues().get(execElem));
						}
					}
					this.printWriter.println(stringJoiner.toString());	
				}
			}
			this.printWriter.println("<input type = \"submit\" value = \"Send\">");	
			this.printWriter.println("</form>");	
			try {
				this.printWriter.flush();
				this.writer.close();
			} catch (IOException e) {
				this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.toString());
			}
		}
		return (true);
	}
}
