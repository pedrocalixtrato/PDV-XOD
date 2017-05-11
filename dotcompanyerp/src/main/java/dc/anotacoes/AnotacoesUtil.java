package dc.anotacoes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnotacoesUtil {

	/**
	 * Retorna a anotação do tipo <code>anotacaoClass</code> se houver, ou
	 * <code>null</code> se Não houver. anotacaoClass beanClass propriedade
	 */
	public static <T extends Annotation, E> T getAnotacao(
			Class<T> anotacaoClass, Class<E> beanClass, String propriedade) {
		if (propriedade.contains(".")) {
			propriedade = propriedade.substring(0, propriedade.indexOf("."));
		}
		try {
			Field f = beanClass.getDeclaredField(propriedade);
			f.setAccessible(true);
			return f.getAnnotation(anotacaoClass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
