package dc.framework.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VOverlay;
import com.vaadin.client.ui.VTextField;
import com.vaadin.shared.ui.Connect;

import dc.framework.SearchTextFieldExtension;

@Connect(SearchTextFieldExtension.class)
public class SearchTextFieldConnector extends AbstractExtensionConnector {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void extend(ServerConnector target) {

		final Widget txtFieldWidget = ((ComponentConnector) target).getWidget();

		final VOverlay warning = new VOverlay();
		final VTextField textField = (VTextField) ((ComponentConnector) target).getWidget();
		warning.setOwner(txtFieldWidget);
		warning.add(new HTML("Caps Lock está ativo!"));
		System.out.println("Extending............................................");
		txtFieldWidget.addDomHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				doSearchScheduling(textField);
			}

		}, KeyPressEvent.getType());

		txtFieldWidget.addDomHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE || event.getNativeKeyCode() == KeyCodes.KEY_DELETE) {
					doSearchScheduling(textField);
				}
			}
		}, KeyDownEvent.getType());

		/*
		 * txtFieldWidget.addHandler(new ValueChangeHandler<String>() {
		 * 
		 * @Override public void onValueChange(ValueChangeEvent<String> event) {
		 * 
		 * Scheduler.get().scheduleDeferred(new ScheduledCommand() {
		 * 
		 * @Override public void execute() { MyComponentServerRpc rpc =
		 * getRpcProxy(MyComponentServerRpc.class);
		 * rpc.search(textField.getValue()); } });
		 * 
		 * 
		 * } }, ValueChangeEvent.getType());
		 */

		txtFieldWidget.addDomHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				/*
				 * MouseEventDetails details
				 * =MouseEventDetailsBuilder.buildMouseEventDetails
				 * (event.getNativeEvent()); MyComponentServerRpc rpc =
				 * getRpcProxy(MyComponentServerRpc.class);
				 * rpc.clicked(details.getButtonName());
				 */
			}
		}, MouseDownEvent.getType());

	}

	// will execute search after some time, only one because is delayed and last
	// only
	private void doSearchScheduling(final VTextField textField) {

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {

				SerchTextFieldServerRPC rpc = getRpcProxy(SerchTextFieldServerRPC.class);
				rpc.search(textField.getValue());

			}

		});

		Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {

			@Override
			public boolean execute() {
				SerchTextFieldServerRPC rpc = getRpcProxy(SerchTextFieldServerRPC.class);
				rpc.schedule();
				return false;
			}
		}, 1300);
	}

	private boolean isCapsLockOn(KeyPressEvent e) {
		return e.isShiftKeyDown() ^ Character.isUpperCase(e.getCharCode());
	}
}
