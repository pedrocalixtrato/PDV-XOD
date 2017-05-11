package dc.visao.framework.geral;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

public class TaskChangeAction {
	
	private MainController mainController;
	private Task task;

	public TaskChangeAction(MainController controller, Task task){
		this.mainController = controller;
		this.task = task;
	}

	public void moveToTask() {
		if(!task.isActive()){
			new Notification("Alterando para tarefa: " + task.getTaskCaption()).show(Page.getCurrent());
			mainController.showTaskableContent(task);
			mainController.showMenu(mainController.getMenuBuilder().buildMenuPanel(task.getModuleId(),mainController));	
		}
	}

}
