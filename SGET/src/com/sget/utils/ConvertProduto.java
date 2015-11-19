/**
 * 
 */
package com.sget.utils;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.context.ApplicationContext;

import sun.awt.AppContext;

import com.sgetejb.dao.ProdutoDAO;
import com.sgetejb.model.Produto;

/**
 * @author FelipeZoize
 *
 */
@FacesConverter(value="convertProduto") 
public class ConvertProduto implements Converter {

	ProdutoDAO produtoDAO = new ProdutoDAO();  
	

	@Override  
	public Object getAsObject(FacesContext context, UIComponent component, String value) {  
		if (value != null && value.trim().length() > 0) {  
			try {  
				int i = Integer.valueOf(value).intValue();
				//Produto produto = new Produto();

				List<Produto> lista = produtoDAO.findAll();
				for(Produto prod : lista){
					if(prod.getId() == i){
						return prod;
					}
				}
			//	produto = produtoDAO.findProduto(i);
				//return produto; 
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
