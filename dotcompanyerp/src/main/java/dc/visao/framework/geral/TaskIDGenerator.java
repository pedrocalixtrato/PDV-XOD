package dc.visao.framework.geral;

import java.util.ArrayList;
import java.util.List;

public class TaskIDGenerator {
	
	public Integer nextIndex = 0;
	public List<Integer> usedIndex = new ArrayList<Integer>();
	
	public void assignID(Task t){
		if(t.getTaskInstanceId() == null || t.getTaskInstanceId().trim().isEmpty()){
			this.nextIndex = this.nextIndex + 1;
			while(usedIndex.contains(nextIndex)){
				this.nextIndex = this.nextIndex + 1;
			}
			if(nextIndex == 1){
				t.setTaskInstanceId(null);
			}else{
				t.setTaskInstanceId(nextIndex.toString());	
			}
			
		}
	}
	
	
	public void reset(){
		this.nextIndex = 0;
		this.usedIndex = new ArrayList<Integer>();
	}

	public void releaseId(Task task) {
		if(task.getTaskInstanceId() != null){
			this.usedIndex.remove(new Integer(task.getTaskInstanceId()));
			this.nextIndex = new Integer(task.getTaskInstanceId());
			task.setTaskInstanceId(null);
			if(usedIndex.isEmpty()){
				reset();
			}	
		}
		
		
	}

}
