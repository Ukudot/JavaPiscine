package	edu.Roma42.annotations;
import	java.lang.annotation.Retention;
import	java.lang.annotation.RetentionPolicy;
import	java.lang.annotation.Target;
import	java.lang.annotation.ElementType;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface	HtmlForm {
	String	fileName();
	String	action();
	String	method();
}
