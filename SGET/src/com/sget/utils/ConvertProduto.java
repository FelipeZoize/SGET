/**
 * 
 */
package com.sget.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.sget.mb.ProdutoMB;
import com.sgetejb.model.Produto;

/**
 * @author FelipeZoize
 *
 */
@FacesConverter(value="convertProduto")
public class ConvertProduto implements Converter {

	@Override  
	public Object getAsObject(FacesContext context, UIComponent component, String value) {  
		if (value != null && value.trim().length() > 0) {  
			try {  
				FacesContext fContext = FacesContext.getCurrentInstance();
		        ProdutoMB produtoMB = (ProdutoMB) fContext.getELContext().getELResolver().getValue(fContext.getELContext(), null, "produtoMB");
		 
		        return produtoMB.findProduto(Integer.parseInt(value));								

			} catch (Exception e) {  
				throw new ConverterException("Não foi possível obter o produto." + value + "." + e.getMessage());  
			}  
		}  
		return null;  
	}  

	@Override  
	public String getAsString(FacesContext context, UIComponent component, Object value) {  
		if (value == null) {
            return "";
        }
		Produto produto = (Produto) value;  
		return String.valueOf( produto.getId() );  
	}

}
