/**
 * 
 */
package com.sget.utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * @author FelipeZoize
 *
 */
public class JSFMessageUtil {

	public void sendInfoMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,message));
	}
	public void sendErrorMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage (FacesMessage.SEVERITY_ERROR, message, message));
	}

	public FacesContext getContext(){
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}
	private FacesMessage createMessage(Severity severity, String mensagemErro) {
		return new FacesMessage(severity, mensagemErro, mensagemErro);
	}

	private void addMessageToJsfContext(FacesMessage facesMessage) {
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
