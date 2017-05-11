package dc.control.validator;

import java.math.BigDecimal;
import java.util.Date;

import com.vaadin.data.Property.ValueChangeEvent;

/**
 * Esta classe tem como objetivo validar objetos para a camada de visão como
 * para a camada de controle.
 * 
 * 
 */

public class ObjectValidator {

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static Boolean validateString(String value) {
		boolean valido = true;

		if (value == null || value.isEmpty()) {
			valido = false;
		}

		return valido;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static boolean validateObject(Object value) {
		return value != null;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static Boolean validateNumber(String value) {
		boolean valido = validateString(value);

		if (valido) {
			try {
				Double.parseDouble(value);
			} catch (NumberFormatException e) {
				valido = false;
			}
		}

		return valido;
	}

	public synchronized static Boolean validateNumber(ValueChangeEvent event) {
		Object s = event.getProperty().getValue();

		boolean valido = validateString(s.toString().trim());

		if (valido) {
			try {
				Double.parseDouble(s.toString().trim());
			} catch (NumberFormatException e) {
				valido = false;
			}
		}

		return valido;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static Boolean validateNotRequiredNumber(String value) {
		try {
			if (value != null && !value.equals("") && !value.isEmpty()) {
				Double.parseDouble(value);
			}

			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static Boolean validateInteger(String value) {
		boolean valido = validateString(value);

		if (valido) {
			try {
				Integer.parseInt(value);
			} catch (NumberFormatException e) {
				valido = false;
			}
		}

		return valido;
	}

	public synchronized static Boolean validateNotRequiredInteger(String value) {
		try {
			if (value != null && !value.equals("") && !value.isEmpty()) {
				Integer.parseInt(value);
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public synchronized static Boolean validateNotRequiredBigDecimal(
			String value) {
		try {
			if (value != null && !value.equals("") && !value.isEmpty()) {
				new BigDecimal(value);
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static Boolean validateDate(String value) {
		boolean valido = validateString(value);

		if (valido) {
			try {
				Date.parse(value);
			} catch (Exception e) {
				valido = false;
			}
		}

		return valido;
	}

	public synchronized static Boolean validateNotRequiredDate(
			ValueChangeEvent event) {
		boolean isValid = true;

		try {
			Object obj = event.getProperty().getValue();

			Date d = (Date) obj;
		} catch (Exception e) {
			isValid = false;
		}

		return isValid;
	}

	/**
	 * Este método valida um valor de data requerida. Usado na camada de
	 * controle.
	 * 
	 * @param value
	 * @return Boolean
	 */

	public synchronized static Boolean validateDate(Object value) {
		boolean valido = validateObject(value);

		if (valido) {
			try {
				Date d1 = (Date) value;
			} catch (Exception e) {
				valido = false;
			}
		}

		return valido;
	}

	/**
	 * Este método valida um valor de data não requerida. Usado na camada de
	 * controle.
	 * 
	 * @param value
	 * @return Boolean
	 */

	public synchronized static Boolean validateNotRequiredDate(Object value) {
		try {
			if (value != null) {
				Date d = (Date) value;
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public synchronized static Boolean validateInteger(ValueChangeEvent event) {
		Object s = event.getProperty().getValue();

		boolean valido = validateString(s.toString().trim());

		if (valido) {
			try {
				Integer.parseInt(s.toString().trim());
			} catch (NumberFormatException e) {
				valido = false;
			}
		}

		return valido;
	}

	/**
	 * Este método valida um valor, retirando os caracteres especiais.
	 * 
	 * @param event
	 * @return Boolean
	 */

	public synchronized static Boolean validateValue(ValueChangeEvent event) {
		boolean valido = true;

		Object s = event.getProperty().getValue();

		String s1 = s.toString();

		if (s1.equals("")) {
			valido = false;

			return valido;
		}

		s1 = s1.replaceAll("[^\\,0-9]+", "");
		s1 = s1.replaceFirst(",", ".");
		s1 = s1.replaceAll("[\\,]+", "");

		String[] s2 = s1.split("\\.");

		if (s2.length != 2) {
			valido = false;

			return valido;
		}

		return valido;
	}

	public synchronized static boolean validateEventValue(ValueChangeEvent event) {
		return event.getProperty().getValue() != null;
	}

	/**
	 * Verifica se o campo está com valor nulo ou vazio retornando um valor
	 * booleano.
	 * 
	 * @param event
	 * @return boolean
	 */
	public synchronized static boolean blankOrNull(ValueChangeEvent event) {
		if (event.getProperty().getValue() == null
				|| event.getProperty().getValue().toString().equals("")) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

}