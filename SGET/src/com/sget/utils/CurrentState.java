/**
 * 
 */
package com.sget.utils;



/**
 * @author FelipeZoize
 *
 */

public class CurrentState {
		
	/** diz qual o estado da pagina **/
	private String currentState; 	
	
	public String getCurrentState() {
		if(currentState == null){
			currentState =  CurrentStateIF.ADD_STATE;
		}
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
}
