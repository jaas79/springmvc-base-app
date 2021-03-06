package com.softtek.spring.seguridad.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.acceleo.demo.domain.ModuloAccion;
import com.softtek.acceleo.demo.domain.ModuloAccionAuthority;
import com.softtek.acceleo.demo.domain.User;
import com.softtek.acceleo.demo.exception.GenericException;
import com.softtek.acceleo.demo.service.ModuloAccionAuthorityService;
import com.softtek.acceleo.demo.service.ModuloAccionService;
import com.softtek.acceleo.demo.service.UserService;
import com.softtek.spring.seguridad.JwtAuthenticationProvider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/test/resources/applicationContext.xml")
@TransactionConfiguration
@Transactional
public class VariosTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final Logger logger = Logger.getLogger(VariosTest.class);
		
	@Autowired
	JwtAuthenticationProvider jwtap;
	
	@Autowired
	private UserService userService;	
	
	@Autowired
	private ModuloAccionService moduloaccionService;	
	
	@Autowired
	private ModuloAccionAuthorityService moduloAccionAuthorityService;
	

	@Before
	public void initJUnit() {
		logger.info("****** Iniciando prueba de JUnit - VariosTest... ******");
	}
	
	//@Test
	public void testUserServices() {
		User user = new User();
		//user.setIdUser(new Integer(5));
		user.setUserName("user05");
		user.setPassword("user05");
		user.setRol("user");
		user.setImagen("user05.png");
		
		List<User> lstUserPrv = userService.consultarUser(user.getUserName());
		if( lstUserPrv == null || lstUserPrv.isEmpty() ) {
			logger.info("-------->> NO EXISTE INFORMACION DEL USER...");
		}else {
			logger.info("-------->> YA EXISTE INFORMACION DEL USER...");
		}
		
		//Se almacena la informacion del user, con un password temporal al cual no se le genero un token.
		user.setPassword("temporal");
		try {
			logger.info("-------->> antes de hacer el insert...");
			userService.addUser(user);
			logger.info("-------->> despues de hacer el insert...");
		}catch(Exception e) {
			logger.error("*** EError: " + e);
		}
		
		//Se consulta la informacion del user persistido anteriormente, esto con la finalidad de obtener el iduser
		List<User> lstUser = userService.consultarUser(user.getUserName());
		if( lstUser == null || lstUser.isEmpty() ) {
			logger.info("Error: No se pudo obtener la informacion del usuario.");
		}else {
			User userPrst = lstUser.get(0);
			userPrst.setPassword("user05");
			
			String token = jwtap.makerToken(userPrst);
			userPrst.setPassword(token);
			
			//Se actualiza la informacion del user persistido anteriormente, se sustituye el password temporal, por el token generado con base a la informacion del user.
			userService.editUser(userPrst);
		}
		
	}
	
	//@Test
	public void testModuloAccionService() {
		int idmodulo = 1;
		int idaccion = 1;
		
		ModuloAccion moduloAccion = new ModuloAccion();
		moduloAccion.setId(null);
		moduloAccion.setIdModulo(idmodulo);
		moduloAccion.setIdAccion(idaccion);
		moduloAccion.setEstatus(true);
		moduloAccion.setFechaCreacion(new Date());
		moduloAccion.setFechaModificacion(null);
	 
		try {
		 	//Se almacena la informacion del user, con un password temporal al cual no se le genero un token.
			moduloaccionService.addModuloAccion(moduloAccion);
	
			List<ModuloAccion> lstModuloAccion = moduloaccionService.listModuloAccion(idmodulo, idaccion);
			if( lstModuloAccion == null || lstModuloAccion.isEmpty()  ) {
				logger.info("NO EXISTE INFORMACION DEL REGISTRO.");
			}else {
				logger.info("SI EXISTE INFORMACION DEL REGISTRO.");
			}
		}catch(GenericException e) {
			logger.error("Error - " + e);
			if( e.getCause().getCause().getMessage().contains("Duplicate entry") ) {
				List<ModuloAccion> lstModuloAccion = moduloaccionService.listModuloAccion(idmodulo, idaccion);
				if( lstModuloAccion == null || lstModuloAccion.isEmpty()  ) {
					logger.info("NO EXISTE INFORMACION DEL REGISTRO.");
				}else {
					logger.info("SI EXISTE INFORMACION DEL REGISTRO.");
				}
			}
		}
	}
	
	@Test
	public void testModuloAccionAuthority() {
		int idModulo = 1;
		int idAccion = 1;
		int idAuthority = 1;
		
		 List<ModuloAccion> lstModuloAccion = moduloaccionService.listModuloAccion(idModulo, idAccion);
		 
		 if( lstModuloAccion == null || lstModuloAccion.isEmpty() ) {
			 logger.info("La relacion Modulo - Accion, no existe.");
		 }else {
			 ModuloAccion moduloAccion = lstModuloAccion.get(0);
			 List<ModuloAccionAuthority> lstModuloAccionAuthority = moduloAccionAuthorityService.listModuloAccionAuthority(moduloAccion.getId(), idAuthority);
			 
			 if( lstModuloAccionAuthority == null || lstModuloAccionAuthority.isEmpty() ) {
				 logger.info("No existe informacion del registro ModuloAccionAuthority.");
			 }else {
				 logger.info("Si existre informacion del registro ModuloAccionAuthority.");
			 }
		 }		
	}
	
	@After
	public void finJUnit(){
		logger.info("****** Finalizando prueba de JUnit - VariosTest... ******");
	}
}
