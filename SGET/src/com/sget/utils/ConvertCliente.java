/**
 * 
 */
package com.sget.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.sget.mb.ClienteMB;
import com.sgetejb.model.Cliente;
import com.sgetejb.model.Produto;

/**
 * @author FelipeZoize
 *
 */
@FacesConverter(value="convertCliente")
public class ConvertCliente implements Converter {

	@Override  
	public Object getAsObject(FacesContext context, UIComponent component, String value) {  
		if (value != null && value.trim().length() > 0) {  
			try {  
				FacesContext fContext = FacesContext.getCurrentInstance();
		        ClienteMB clienteMB = (ClienteMB) fContext.getELContext().getELResolver().getValue(fContext.getELContext(), null, "clienteMB");
		 
		        return clienteMB.findCliente(Integer.parseInt(value));								

			} catch (Exception e) {  
				throw new ConverterException("Não foi possível obter o cliente." + value + "." + e.getMessage());  
			}  
		}  
		return null;  
	}  

	@Override  
	public String getAsString(FacesContext context, UIComponent component, Object value) {  
		if (value == null) {
            return "";
        }
		Cliente cliente = (Cliente) value;  
		return String.valueOf( cliente.getId() );  
	}

}
