package dc.servicos.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.vaadin.server.VaadinSession;

/**
 * Classe Utilitária com Médotos de validação.
 * 
 * @author douglasrehem
 * 
 */
public class Validator {

	private Validator() {

	}

	/**
	 * Método Utilitário de validação de String.
	 * 
	 * @param value
	 *            Valor para ser validado.
	 * @return Retorna <code>true</code> se estiver preenchido ou
	 *         <code>false</code> se for null ou vazio
	 */
	public static Boolean validateString(String value) {
		boolean valido = true;

		if (value == null || value.isEmpty()) {
			valido = false;
		}

		return valido;
	}

	/**
	 * Método Utilitário de validação de Números.
	 * 
	 * @param value
	 *            Valor para ser validado.
	 * @return Retorna <code>true</code> se for um Número v�lido ou
	 *         <code>false</code> se for inválido ou em branco
	 */
	public static Boolean validateNumber(String value) {
		boolean valido = validateString(value);

		if (valido) {
			try {
				Locale myLocale = VaadinSession.getCurrent().getLocale();

				NumberFormat f = NumberFormat.getInstance(myLocale);

				f.parse(value);
			} catch (NumberFormatException e) {
				valido = false;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return valido;
	}

	/**
	 * Método Utilitário de validação de Objetos.
	 * 
	 * @param value
	 *            Valor para ser validado.
	 * @return Retorna <code>true</code> se Não for null ou <code>false</code>
	 *         se for null.
	 */
	public static boolean validateObject(Object value) {
		return value != null;
	}

}