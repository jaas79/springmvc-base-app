/**
 * Autor: PSG.
 * Proyecto:
 * Version: 0.1 
 * Clase para invocar servicios de los UserAuthority. 
 */
package com.softtek.acceleo.demo.catalogo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;


import com.softtek.acceleo.demo.catalogo.bean.UserAuthorityBean;
import com.softtek.acceleo.demo.domain.UserAuthority;
import com.softtek.acceleo.demo.exception.GenericException;
import com.softtek.acceleo.demo.service.UserAuthorityService;

/**
 * Clase UserAuthorityController.
 * @author PSG.
 *
 */
@Controller
public class UserAuthorityController {
	private static final Logger logger = Logger.getLogger(UserAuthorityController.class);
	
	@Autowired
	private UserAuthorityService userauthorityService;
	
	@RequestMapping(value = "/userauthority", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody  List<UserAuthority> getUserAuthoritys(@RequestParam Map<String,String> requestParams, HttpServletRequest request, HttpServletResponse response) {
		UserAuthority userauthority = new UserAuthority();
		
       	String query=requestParams.get("q");
		int page= requestParams.get("_page")==null?0:Integer.parseInt(requestParams.get("_page"));
		long rows = 0;

		

		List<UserAuthority> listUserAuthority = null;

		if (query==null && page == 0 ) {
       		listUserAuthority = userauthorityService.listUserAuthorityss(userauthority);
			rows = userauthorityService.getTotalRows();
		} /**else if (query!= null){
			
		}**/ else /**if (page != 0)**/{
			listUserAuthority = userauthorityService.listUserAuthoritysPagination(userauthority, page, 10);
			rows = userauthorityService.getTotalRows();
		} 	

		response.setHeader("Access-Control-Expose-Headers", "x-total-count");
		response.setHeader("x-total-count", String.valueOf(rows));	

		return listUserAuthority;
	}
	
	@RequestMapping(value = "/userauthority/{id}", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody  UserAuthority getUserAuthority(@PathVariable("id") int id) {
	        UserAuthority userauthority = null;
	        userauthority = userauthorityService.getUserAuthority(id);
			return userauthority;
	 }



	 @RequestMapping(value = "/userauthority", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUserAuthority(@RequestBody UserAuthority userauthority,    UriComponentsBuilder ucBuilder) {
	   
	        userauthorityService.addUserAuthority(userauthority);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/userauthority/{id}").buildAndExpand(userauthority.getId()).toUri());
	        return new ResponseEntity<>(headers, HttpStatus.CREATED);
	 }
		
	 @RequestMapping(value = "/userauthority/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<UserAuthority> actualizarUserAuthority(@PathVariable("id") int id, @RequestBody UserAuthority userauthority) {
	        
	        
	        UserAuthority userauthorityFound = userauthorityService.getUserAuthority(id);
	         
	        if (userauthorityFound==null) {
	            logger.info("User with id " + id + " not found");
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	 
				userauthorityFound.setIdUserAuthority(userauthority.getIdUserAuthority());
				userauthorityFound.setIdUser(userauthority.getIdUser());
				userauthorityFound.setIdRol(userauthority.getIdRol());
				userauthorityFound.setEstatus(userauthority.getEstatus());
				userauthorityFound.setFechaCreacion(userauthority.getFechaCreacion());
				userauthorityFound.setFechaModificacion(userauthority.getFechaModificacion());
			userauthority.setId(null);
	        
	        userauthorityService.editUserAuthority(userauthorityFound);
	        return new ResponseEntity<>(userauthorityFound, HttpStatus.OK);
	  } 	
	
		
		@RequestMapping(value = "/userauthority/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<UserAuthority> deleteUserAuthority(@PathVariable("id") int id) {
	    	 logger.info("Fetching & Deleting User with id " + id);
	    	 
             try{
    	         UserAuthority userauthority = userauthorityService.getUserAuthority(id);
    	         if (userauthority == null) {
    	             logger.info("Unable to delete. Cuenta with id " + id + " not found");
    	             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	         }
            	 
	             userauthorityService.deleteUserAuthority(userauthority);
            	 return new ResponseEntity<>(HttpStatus.OK);
             } catch(GenericException e) {
            	 return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
             }
			
		}


	@RequestMapping(value = "/saveUserAuthority", method = RequestMethod.POST)
	public @ResponseBody String saveUserAuthority(
			@ModelAttribute("command") UserAuthorityBean userauthorityBean) {


		UserAuthority userauthority = prepareModel(userauthorityBean);
		userauthorityService.addUserAuthority(userauthority);

		return "SUCCESS";
	}
	
	@RequestMapping(value = "/editUserAuthority", method = RequestMethod.POST)
	public @ResponseBody String editUserAuthority(
			@ModelAttribute("command") UserAuthorityBean userauthorityBean) {


		UserAuthority userauthority = prepareModel(userauthorityBean);
		userauthorityService.editUserAuthority(userauthority);
		return "SUCCESS";
	}

	@RequestMapping(value = "/searchUserAuthority", method = RequestMethod.GET)
	public ModelAndView addUserAuthority(
			@ModelAttribute("command") UserAuthorityBean userauthorityBean,
			BindingResult result) {

		Map<String, Object> model = new HashMap<>();
		UserAuthority userauthority = null;
		if (userauthorityBean != null)
			userauthority = prepareModel(userauthorityBean);
		model.put("userauthoritys",
				prepareListofBean(userauthorityService.listUserAuthorityss(userauthority)));
		return new ModelAndView("searchUserAuthority", model);
	}

	@RequestMapping(value = "/entryUserAuthority", method = RequestMethod.GET)
	public ModelAndView entryUserAuthority() {
		return new ModelAndView("redirect:/searchUserAuthority");
	}

	private UserAuthority prepareModel(UserAuthorityBean userauthorityBean) {
		UserAuthority userauthority = new UserAuthority();

		userauthority.setIdUserAuthority(userauthorityBean.getId());
		userauthority.setIdUser(userauthorityBean.getIdUser());
		userauthority.setIdRol(userauthorityBean.getIdRol());
		userauthority.setEstatus(userauthorityBean.getEstatus());
		userauthority.setFechaCreacion(userauthorityBean.getFechaCreacion());
		userauthority.setFechaModificacion(userauthorityBean.getFechaModificacion());
		userauthority.setId(userauthorityBean.getId());
		userauthorityBean.setId(null);
		return userauthority;
	}

	private List<UserAuthorityBean> prepareListofBean(List<UserAuthority> userauthoritys) {
		List<UserAuthorityBean> beans = null;
		if (userauthoritys != null && !userauthoritys.isEmpty()) {
			beans = new ArrayList<>();
			UserAuthorityBean bean = null;
			for (UserAuthority userauthority : userauthoritys) {
				bean = new UserAuthorityBean();

                bean.setId(userauthority.getIdUserAuthority());
                bean.setIdUser(userauthority.getIdUser());
                bean.setIdRol(userauthority.getIdRol());
                bean.setEstatus(userauthority.getEstatus());
                bean.setFechaCreacion(userauthority.getFechaCreacion());
                bean.setFechaModificacion(userauthority.getFechaModificacion());
				bean.setId(userauthority.getId());
				beans.add(bean);
			}
		}
		return beans;
	}

}


