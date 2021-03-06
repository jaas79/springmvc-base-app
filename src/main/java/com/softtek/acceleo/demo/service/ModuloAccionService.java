package com.softtek.acceleo.demo.service;

import com.softtek.acceleo.demo.domain.ModuloAccion;
import com.softtek.acceleo.demo.exception.GenericException;

import java.util.List;

public interface ModuloAccionService {

	public void addModuloAccion(ModuloAccion moduloaccion) throws GenericException;

	public void editModuloAccion(ModuloAccion moduloaccion);
	
	public List<ModuloAccion> listModuloAccionss(ModuloAccion moduloaccion);
	
	public List<ModuloAccion> listModuloAccion(int idModulo, int idAccion);

	public ModuloAccion getModuloAccion(int empid);
	
	public List<ModuloAccion> getModuloAccionPorIdModulo(int idModulo);

	public void deleteModuloAccion(ModuloAccion moduloaccion) throws GenericException;
	
	public List<ModuloAccion> listModuloAccionssQuery(ModuloAccion moduloaccion, String query, int page, int size);

	public List<ModuloAccion> listModuloAccionsPagination(ModuloAccion moduloaccion, int page, int size);
	

	public long getTotalRows();

	public long getTotalRows(String query);

	public long getTotalRowsSearch(String query);

	

	

	
}

