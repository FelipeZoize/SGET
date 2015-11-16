/**
 * 
 */
package com.sget.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author FelipeZoize
 *
 */
@ManagedBean
@SessionScoped
public class CurrentStateMB extends CurrentState{
		
	public boolean isAddState() {  
		return CurrentStateIF.ADD_STATE.equals(super.getCurrentState());  
	}  
	public boolean isUpdateState() {  
		return CurrentStateIF.UPDATE_STATE.equals(super.getCurrentState());  
	}
	public boolean isListState() {  
		return CurrentStateIF.LIST_STATE.equals(super.getCurrentState());  		
	}
	public void prepareUpdate(){
		super.setCurrentState(CurrentStateIF.UPDATE_STATE);
	}
}
