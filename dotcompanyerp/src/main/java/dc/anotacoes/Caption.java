package dc.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para definir o nome do campo para ser usado em interfaces de Usuário.
 * Utilizamos nas classes de TO (ENTIDADE)
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Caption {

	String value() default "NULO";
	boolean sum() default false;
}
