package dc.visao.framework.geral;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.event.ShortcutListener;

public class MoveToIndexShortcutHandler  {
	
	private TaskChangeAction taskAction;
	private ShortcutListener shortcut;
	
	public MoveToIndexShortcutHandler(String shorthandCaption,TaskChangeAction action,final int index) {
		this.taskAction = action;
		shortcut = new ShortcutListener("Trocar de tarefa", getNumKeyCode(index + 1),new int[] {ModifierKey.CTRL,ModifierKey.SHIFT}) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void handleAction(Object sender, Object target) {
				System.out.println("move to index...:" + index);
				taskAction.moveToTask();
			}
		};
		
	}
	
	private int getNumKeyCode(int i) {
		switch (i) {
			case 0:
				return KeyCode.NUM0;
			case 1:
				return KeyCode.NUM1;
			case 2:
				return KeyCode.NUM2;
			case 3:
				return KeyCode.NUM3;
			case 4:
				return KeyCode.NUM4;
			case 5:
				return KeyCode.NUM5;
			case 6:
				return KeyCode.NUM6;
			case 7:
				return KeyCode.NUM7;
			case 8:
				return KeyCode.NUM8;
			case 9:
				return KeyCode.NUM9;
		default:
			break;
		}
		return -1;		
	}

	public ShortcutListener getShortCutListener(){
		return shortcut;
	}


	


	

}
