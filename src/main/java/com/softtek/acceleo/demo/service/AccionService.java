package com.softtek.acceleo.demo.service;

import com.softtek.acceleo.demo.domain.Accion;
import com.softtek.acceleo.demo.exception.GenericException;

import java.util.List;

public interface AccionService {

	public void addAccion(Accion accion);

	public void editAccion(Accion accion);
	
	public List<Accion> listAccionss(Accion accion);

	public Accion getAccion(int empid);

	public void deleteAccion(Accion accion) throws GenericException;
	
	public List<Accion> listAccionssQuery(Accion accion, String query, int page, int size);

	public List<Accion> listAccionsPagination(Accion accion, int page, int size);
	

	public long getTotalRows();

	public long getTotalRows(String query);

	public long getTotalRowsSearch(String query);

	

	

	
}

