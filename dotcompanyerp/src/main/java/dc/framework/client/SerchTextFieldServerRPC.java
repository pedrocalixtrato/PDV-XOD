package dc.framework.client;

import com.vaadin.shared.annotations.Delayed;
import com.vaadin.shared.communication.ServerRpc;

public interface SerchTextFieldServerRPC extends ServerRpc{
	  
	
	public void clicked(String buttonName);

	@Delayed(lastOnly=true)
	public void search(String value);
	
	public void schedule();

}
