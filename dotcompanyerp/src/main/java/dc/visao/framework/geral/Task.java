package dc.visao.framework.geral;

public interface Task {
	
	public String getModuleId();

	public void setModuleId(String moduleID);
	
	public Controller getController();
	
	public Task getParent();
	
	public boolean isActive();
	
	public void setActive(boolean a);

	public String getTaskCaption();
	
	public String getTaskInstanceId();
	
	public void setTaskInstanceId(String id) ;

	public void dispose();

}
