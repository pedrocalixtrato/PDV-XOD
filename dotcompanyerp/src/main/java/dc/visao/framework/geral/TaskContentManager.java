package dc.visao.framework.geral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
	
@org.springframework.stereotype.Component
@Scope(value="session")
public class TaskContentManager  implements Serializable{
	
	private static final long serialVersionUID = -5990160688116065890L;

	public List<dc.visao.framework.geral.Task> tasks  = new LinkedList<dc.visao.framework.geral.Task>();
	
	public Map<String,TaskIDGenerator> taskIDS = new HashMap<String,TaskIDGenerator>();
	
	public static final int LIMIT_TASK = 3;

	public static final int MAIN_TASKS_LIMT = 7;
	
	private Task activeTask;

	public boolean addOrActivate(dc.visao.framework.geral.Task t) {
		if(tasks.contains(t)){
			changeActiveTask(t);
			return true;
		}else{
			int taskCount = getTaskCount(t);
			
			boolean result = taskCount < LIMIT_TASK;
			if (result == false){
				return false;
			}else{
				assignTaskID(t);	
				changeActiveTask(t);
				this.tasks.add(t);
				return true;
			}		
		}
	
	}

	private void assignTaskID(dc.visao.framework.geral.Task t) {
		String viewID = getIdentifier(t);
		
		if(taskIDS.containsKey(viewID)){
			TaskIDGenerator generator = taskIDS.get(viewID);
			generator.assignID(t);
		}else{
			TaskIDGenerator generator = new TaskIDGenerator();
			taskIDS.put(viewID,generator);
			generator.assignID(t);
		}
	}

	private String getIdentifier(dc.visao.framework.geral.Task t) {
		String viewID = "";
		if(t.getParent() != null){
			viewID = t.getParent().getController().getViewIdentifier();
		}else{
			viewID = t.getController().getViewIdentifier();
		}
		return viewID;
	}

	private void changeActiveTask(dc.visao.framework.geral.Task t) {
		if(activeTask != null){
			this.activeTask.setActive(false);	
		}
		
		t.setActive(true);
		this.activeTask = t;
	}
	
	private int getTaskCount(Task t ) {
		int count = 0;
		for(Task tk : tasks){
			if(tk.getParent() != null){
				if(tk.getParent().getController().getViewIdentifier().equals(t.getController().getViewIdentifier())){
					count++;
				}
			}else if(t.getParent() != null){
				if(t.getParent().getController().getViewIdentifier().equals(tk.getController().getViewIdentifier())){
					count++;
				}
			} else if(tk.getController().getViewIdentifier().equals(t.getController().getViewIdentifier())){
				count++;
			}
		}
		
		return count ;
	}

	public List<Task> getAllTasks() {
		return tasks;
	}
	
	public List<Task> getMainTasks() {
		List<Task> mainTasks = getOriginalMainTasks();
		if(!mainTasks.isEmpty() && activeTask != null && !mainTasks.contains(activeTask)){
			int indexOfChange = mainTasks.size() -1; 
			Task changed = mainTasks.remove(indexOfChange);
			mainTasks.add(activeTask);
			int nIndex = tasks.indexOf(changed);
			tasks.remove(activeTask);
			tasks.remove(changed);
			tasks.add(nIndex,activeTask);
			tasks.add(changed);
			
		}
		
		return mainTasks;
	}

	private List<Task> getOriginalMainTasks() {
		int limit = MAIN_TASKS_LIMT;
		if(tasks.size() < limit){
			limit = tasks.size();
		}
		
		List<Task> mainTasks = new LinkedList<Task>(tasks.subList(0, limit));
		return mainTasks;
	}

	
	public List<Task> getNonMainTasks() {
		if(tasks.size() <= MAIN_TASKS_LIMT){
			return new ArrayList<Task>();
		}else{
			List<Task> nonMainTasks = new LinkedList<Task>(tasks.subList(MAIN_TASKS_LIMT ,tasks.size()));
			if(activeTask != null && nonMainTasks.contains(activeTask)){
				nonMainTasks.remove(activeTask);
				List<Task> originalMain = getOriginalMainTasks();
				int size = originalMain.size();
				Task changed = (Task) originalMain.remove(size - 1);
				nonMainTasks.add(changed);
			}
			return nonMainTasks;
		}
		
		
		
	}

	public void removeTask(Task task) {
		if (tasks.contains(task)){
			task.dispose();
			tasks.remove(task);
			this.releaseIdentifier(task);
			if((activeTask == null || task.equals(activeTask)) && !tasks.isEmpty()){
				activeTask = tasks.get(tasks.size() -1);
			}else if (task.equals(activeTask)){
				activeTask = null;
			}
		}
		
	}

	private void releaseIdentifier(Task task) {
		String viewIdentifier = getIdentifier(task);
		if(taskIDS.containsKey(viewIdentifier)){
			TaskIDGenerator generator = taskIDS.get(viewIdentifier);
			generator.releaseId(task);
		}
		
	}

	public Task getActiveTask() {
		return this.activeTask;
	}

	public boolean isEmpry() {
		return tasks.isEmpty();
	}

	public void inativateActiveTask() {
		if(this.activeTask != null){
			this.activeTask.setActive(false);
			this.activeTask = null;	
		}
		
	}

	public void removeAll() {
		for(Task t: tasks){
			t.dispose();
		}
		this.taskIDS = new HashMap<String,TaskIDGenerator>();
		this.tasks.clear();
		this.activeTask = null;
	}

}
