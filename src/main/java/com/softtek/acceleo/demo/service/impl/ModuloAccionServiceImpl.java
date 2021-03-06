

package com.softtek.acceleo.demo.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.acceleo.demo.repository.ModuloAccionRepository;
import com.softtek.acceleo.demo.domain.ModuloAccion;
import com.softtek.acceleo.demo.exception.GenericException;
import com.softtek.acceleo.demo.service.ModuloAccionService;

@Service("moduloaccionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ModuloAccionServiceImpl implements ModuloAccionService {
	private static final Logger logger = Logger.getLogger(ModuloAccionServiceImpl.class);
	
	@Autowired
	private ModuloAccionRepository moduloaccionRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addModuloAccion(ModuloAccion moduloaccion) throws GenericException {
		
		moduloaccionRepository.addModuloAccion(moduloaccion);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editModuloAccion(ModuloAccion moduloaccion) {
		
		moduloaccionRepository.editModuloAccion(moduloaccion);
	}
	
	
	public List<ModuloAccion> listModuloAccionss(ModuloAccion moduloaccion) {

		return moduloaccionRepository.listModuloAccionss(moduloaccion);
	}

	public ModuloAccion getModuloAccion(int empid) {

		return moduloaccionRepository.getModuloAccion(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteModuloAccion(ModuloAccion moduloaccion) throws GenericException {
		logger.info("Entrando al deleteModuloAccion");

		 moduloaccionRepository.deleteModuloAccion(moduloaccion);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<ModuloAccion> listModuloAccionsPagination(ModuloAccion moduloaccion, int page, int size) {

		return moduloaccionRepository.listModuloAccionsPagination(moduloaccion, page, size);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRowsSearch(String query) {

		return moduloaccionRepository.getTotalRowsSearch(query);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows(String query) {

		return moduloaccionRepository.getTotalRows(query);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows() {

		return moduloaccionRepository.getTotalRows();
	}

	


	public List<ModuloAccion> listModuloAccionssQuery(ModuloAccion moduloaccion, String query, int page, int size) {

		return moduloaccionRepository.listModuloAccionssQuery(moduloaccion, query, page, size);
	}

	@Override
	public List<ModuloAccion> listModuloAccion(int idModulo, int idAccion) {
		return moduloaccionRepository.listModuloAccion(idModulo, idAccion);
	}

	@Override
	public List<ModuloAccion> getModuloAccionPorIdModulo(int idModulo) {
		return moduloaccionRepository.listModuloAccion(idModulo);
	}



}

