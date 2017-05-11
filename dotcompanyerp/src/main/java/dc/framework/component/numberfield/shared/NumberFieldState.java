package dc.framework.component.numberfield.shared;

import com.vaadin.shared.ui.textfield.AbstractTextFieldState;

/**
 * Number field state class with shared strings
 * 
 * @author Kerim O.D.
 * 
 */
public class NumberFieldState extends AbstractTextFieldState {
	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -5803268179789404932L;
	/**
	 * Meta-chars of regular expression
	 */
	public static final String META_CHARS = "^$()<>[]{}|.*+?";
	/**
	 * Is number has negative sign
	 */
	public boolean isSigned = true;
	/**
	 * Is number has grouping
	 */
	public boolean isUseGrouping = false;
	/**
	 * Grouping separator character
	 */
	public char groupingSeparator = ' ';
	/**
	 * Decimal separator character
	 */
	public char decimalSeparator = '.';
	/**
	 * Length of decimal part
	 */
	public int decimalLength = 0;

	/**
	 * Getting character as string, method changes value if is meta-character by
	 * pre-adding "\"
	 * 
	 * @param character
	 * @return character as string
	 */
	public static String changeIfMetaChar(char character) {
		String string = String.valueOf(character);
		if (META_CHARS.contains(string)) { // if contains pre-add '\'
			return "\\" + character;
		}
		return string;
	}
}