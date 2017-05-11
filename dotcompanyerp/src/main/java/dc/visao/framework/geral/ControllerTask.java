package dc.visao.framework.geral;

public abstract class ControllerTask implements Task {
	
	
	private String moduleID;
	private boolean active;
	private String id;

	@Override
	public String getModuleId(){
		return this.moduleID;
	}

	@Override
	public void setModuleId(String id){
		this.moduleID = id;
		setChildModuleID(id);
	}
	

	public abstract void setChildModuleID(String id);

	@Override
	public abstract Controller getController();
	
	@Override
	public abstract void dispose();
	

	@Override
	public Task getParent(){
		return null;
	}

	@Override
	public boolean isActive(){
		return active;
	}

	@Override
	public void setActive(boolean a){
		this.active = a;
	}
	
	public abstract String getControllerTitle();
	
	@Override
	public String getTaskCaption() {
		if(this.getTaskInstanceId() != null && !this.getTaskInstanceId().trim().isEmpty()){
			return "("+ getTaskInstanceId() + ")" + this.getControllerTitle();
		}else{
			return this.getControllerTitle();	
		}
		
	}
	
	@Override
	public String getTaskInstanceId() {
		return this.id;
	}
	
	@Override
	public void setTaskInstanceId(String id) {
		 this.id = id;
	}

}
