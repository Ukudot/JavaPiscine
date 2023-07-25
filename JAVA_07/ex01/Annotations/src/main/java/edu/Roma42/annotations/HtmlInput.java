
package	edu.Roma42.Annotations.annotations;
import	java.lang.annotation.Retention;
import	java.lang.annotation.RetentionPolicy;
import	java.lang.annotation.Target;
import	java.lang.annotation.ElementType;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface	HtmlInput {
	String	type();
	String	name();
	String	placeholder();
}
