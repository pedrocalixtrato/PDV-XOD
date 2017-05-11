package dc.framework.component.numberfield.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.VTextField;
import com.vaadin.client.ui.textfield.TextFieldConnector;
import com.vaadin.shared.ui.Connect;

import dc.framework.component.numberfield.NumberField;
import dc.framework.component.numberfield.shared.NumberFieldState;

/**
 * Client side number field connector class
 * 
 * @author Kerim O.D.
 * 
 */
@Connect(value = NumberField.class)
public class NumberFieldConnector extends TextFieldConnector {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -5531145613926511796L;
	/**
	 * Negative zero
	 */
	public static final String NEGATIVE_ZERO = "-0";
	/**
	 * Negative zero
	 */
	public static final String ZERO = "0";
	/**
	 * Negative string
	 */
	public static final String NEGATIVE_STRING = "-";
	// sign that special key down
	private boolean specialKeyDown;
	// sign that input has negative sign
	private boolean hasNegativeSign;
	// sign that input has decimal separator
	private boolean hasSeparator;
	// current length of decimal part
	private int decimalLen;
	// focus in handler
	private FocusHandler focusInHanlder = new FocusHandler() {

		@Override
		public void onFocus(FocusEvent event) {
			VTextField widget = getWidget();
			String value = widget.getValue();
			if (value != null) { // if focus in and value != null
				widget.setCursorPos(value.length()); // set cursor to the end
			}
		}

	};
	// click handler
	private ClickHandler clickHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			VTextField widget = getWidget();
			String value = widget.getValue();
			// if click and value != null
			if (value != null) {
				// set cursor to the end
				widget.setCursorPos(value.length());
			}
		}
	};
	// key down handler
	private KeyDownHandler keyDownHandler = new KeyDownHandler() {
		@Override
		public void onKeyDown(KeyDownEvent event) {
			int nativeKeyCode = event.getNativeKeyCode();
			// is special key down
			specialKeyDown = event.isAnyModifierKeyDown()
					|| event.isAltKeyDown() || event.isControlKeyDown()
					|| event.isMetaKeyDown() || event.isShiftKeyDown()
					|| event.isDownArrow() || event.isLeftArrow()
					|| event.isRightArrow() || event.isUpArrow()
					|| (nativeKeyCode == KeyCodes.KEY_END)
					|| (nativeKeyCode == KeyCodes.KEY_HOME)
					|| (nativeKeyCode == KeyCodes.KEY_DOWN)
					|| (nativeKeyCode == KeyCodes.KEY_UP)
					|| (nativeKeyCode == KeyCodes.KEY_LEFT)
					|| (nativeKeyCode == KeyCodes.KEY_RIGHT)
					|| (nativeKeyCode == KeyCodes.KEY_BACKSPACE)
					|| (nativeKeyCode == KeyCodes.KEY_DELETE)
					|| (nativeKeyCode == KeyCodes.KEY_PAGEDOWN)
					|| (nativeKeyCode == KeyCodes.KEY_PAGEUP)
					|| (nativeKeyCode == KeyCodes.KEY_ENTER)
					|| (nativeKeyCode == KeyCodes.KEY_ESCAPE)
					|| (nativeKeyCode == KeyCodes.KEY_ALT)
					|| (nativeKeyCode == KeyCodes.KEY_CTRL)
					|| (nativeKeyCode == KeyCodes.KEY_SHIFT)
					|| (nativeKeyCode == KeyCodes.KEY_TAB);
			// ignoring cursor moving and when shift key inputs
			if (nativeKeyCode == KeyCodes.KEY_LEFT
					|| nativeKeyCode == KeyCodes.KEY_RIGHT
					|| event.isShiftKeyDown()) {
				event.preventDefault();
			}
		}
	};
	// key press handler
	private KeyPressHandler keyPressHandler = new KeyPressHandler() {
		@Override
		public void onKeyPress(KeyPressEvent event) {
			char charCode = event.getCharCode();
			NumberFieldState state = getState();
			char separatorChar = state.decimalSeparator;
			VTextField widget = getWidget();
			String value = widget.getValue();
			int curPos = widget.getCursorPos();
			// if pressed number key
			if (('0' <= charCode) && (charCode <= '9')) {
				// if is integer field
				if (state.decimalLength == 0) {
					// if first number is '0'
					if (NEGATIVE_ZERO.equals(value)) {
						// setting "-"
						widget.setValue(NEGATIVE_STRING);
					} else if (ZERO.equals(value)) {
						// setting empty string
						widget.setValue("");
					}
				} else {
					// if is decimal and entered "-0" or "0"
					if (NEGATIVE_ZERO.equals(value) || ZERO.equals(value)) {
						// appending separator char
						value += separatorChar;
						widget.setValue(value);
					}
				}
				// entered number of decimal part
				if (hasSeparator && !specialKeyDown) {
					// if decimal is full
					if (decimalLen == state.decimalLength) {
						// canceling
						event.preventDefault();
					} else {
						// increase decimal length
						decimalLen++;
					}
				}
				// if entered negative char
			} else if (charCode == KeyCodes.KEY_NUM_MINUS) {
				// if not signed
				// or cursor on in first position
				// or all ready has negative sign
				if (!state.isSigned || (curPos != 0) || hasNegativeSign) {
					// canceling
					event.preventDefault();
				}
				// if entered decimal separator char
			} else if (charCode == separatorChar) {
				// if is integer or
				if (state.decimalLength == 0 || hasSeparator) {
					// canceling
					event.preventDefault();
					return;
				}
				int pos = 0;
				if (hasNegativeSign) {
					pos = 1;
				}
				// if cursor on the first position or after '-'
				if (curPos == pos) {
					// canceling
					event.preventDefault();
				} else {
					decimalLen = 0;
				}
				// if another key was down
			} else if (!specialKeyDown) {
				// canceling
				event.preventDefault();
			}
		}
	};
	// key up handler
	private KeyUpHandler keyUpHandler = new KeyUpHandler() {
		@Override
		public void onKeyUp(KeyUpEvent event) {
			VTextField widget = getWidget();
			String value = widget.getValue();
			NumberFieldState state = getState();
			if (specialKeyDown) {
				if (value != null) {
					int code = event.getNativeKeyCode();
					if (code == KeyCodes.KEY_BACKSPACE) {
						hasSeparator = state.decimalLength > 0
								&& value.contains(Character
										.toString(state.decimalSeparator));
						if (hasSeparator) {
							if (decimalLen > 0) {
								decimalLen--;
							}
						}
					}
					value = formatString(value);
					if (code != KeyCodes.KEY_A && code != KeyCodes.KEY_CTRL) {
						widget.setValue(value);
					}
				}
			} else {
				if (value == null || value.isEmpty()) {
					hasSeparator = false;
					hasNegativeSign = false;
				} else {
					String sign = value.substring(0, 1);
					hasNegativeSign = NEGATIVE_STRING.equals(sign);
					hasSeparator = state.decimalLength > 0
							&& value.contains(Character
									.toString(state.decimalSeparator));
					value = formatString(value);
					if (value.isEmpty()) {
						event.preventDefault();
					} else {
						widget.setValue(value);
					}
				}
			}
			if (!hasSeparator) {
				decimalLen = 0;
			}
		}
	};

	public NumberFieldConnector() {
		super();
		VTextField widget = getWidget();
		// adding handlers
		widget.addKeyDownHandler(keyDownHandler);
		widget.addKeyPressHandler(keyPressHandler);
		widget.addKeyUpHandler(keyUpHandler);
		widget.addFocusHandler(focusInHanlder);
		widget.addClickHandler(clickHandler);
	}

	@Override
	public NumberFieldState getState() {
		return (NumberFieldState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent event) {
		super.onStateChanged(event);
		VTextField widget = getWidget();
		String value = widget.getValue();
		if (value != null) {
			// updating local fields state
			String sign = value.substring(0, 1);
			hasNegativeSign = NEGATIVE_STRING.equals(sign);
			NumberFieldState state = getState();
			hasSeparator = state.decimalLength > 0
					&& value.contains(String.valueOf(state.decimalSeparator));
			if (!hasSeparator) {
				decimalLen = 0;
			} else {
				String decsep = NumberFieldState
						.changeIfMetaChar(state.decimalSeparator);
				String dec = value.split(decsep)[1];
				decimalLen = dec.length();
			}
		}
	}

	/**
	 * Formatting entered string by field's state
	 * @param str string to format
	 * @return formatted string
	 */
	private String formatString(String str) {
		str = str.trim();
		NumberFieldState state = getState();
		String groupsep = NumberFieldState
				.changeIfMetaChar(state.groupingSeparator);
		str = str.replaceAll(groupsep, "");
		if (str.isEmpty() || str.equals(NEGATIVE_STRING)) {
			return str;
		}
		int p = str.indexOf(state.decimalSeparator);
		String pre, suf;
		if (p != -1) {
			pre = str.substring(0, p);
			suf = str.substring(p);
			int len = state.decimalLength + 1;
			if (suf.length() > len) {
				suf = suf.substring(0, len);
			}
		} else {
			pre = str;
			suf = "";
		}
		if (state.isUseGrouping) {
			// group numbers
			pre = useGrouping(pre);
			pre = pre.replaceAll(" ", groupsep);
		}
		// adding decimal part
		return pre + suf;
	}

	/**
	 * Grouping digit values 
	 * @param s number string to modify
	 * @return modified string with number grouping
	 */
	private native String useGrouping(String s)/*-{
												return s.replace(/(\s)+/g, '').replace(/(\d{1,3})(?=(?:\d{3})+$)/g, '$1 ');
												}-*/;

}
