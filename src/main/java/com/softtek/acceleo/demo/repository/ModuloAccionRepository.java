
package com.softtek.acceleo.demo.repository;

import java.util.List;
import com.softtek.acceleo.demo.domain.ModuloAccion;
import com.softtek.acceleo.demo.exception.GenericException;

public interface ModuloAccionRepository {

	
	 public void addModuloAccion(ModuloAccion moduloaccion) throws GenericException;   
	 
	 public void editModuloAccion(ModuloAccion moduloaccion);
	   
	 public List<ModuloAccion> listModuloAccionss(ModuloAccion moduloaccion);   
	    
	 public ModuloAccion getModuloAccion(int empid);   
	    
	 public void deleteModuloAccion(ModuloAccion moduloaccion) throws GenericException; 

	 public List<ModuloAccion> listModuloAccionssQuery(ModuloAccion moduloaccion, String query, int page, int size);

	 public List<ModuloAccion> listModuloAccionsPagination(ModuloAccion moduloaccion, int page, int size);
	 
	 public List<ModuloAccion> listModuloAccion(int idModulo, int idAccion);
	 
	 public List<ModuloAccion> listModuloAccion(int idModulo);

     public long getTotalRows();

     public long getTotalRows(String query);

     public long getTotalRowsSearch(String query);

	

 			

	
}

