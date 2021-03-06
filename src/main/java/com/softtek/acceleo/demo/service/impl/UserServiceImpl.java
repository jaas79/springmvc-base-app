

package com.softtek.acceleo.demo.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.acceleo.demo.domain.User;
import com.softtek.acceleo.demo.exception.GenericException;
import com.softtek.acceleo.demo.repository.UserRepository;
import com.softtek.acceleo.demo.service.UserService;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(User user) {
		
		userRepository.addUser(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editUser(User user) {
		
		userRepository.editUser(user);
	}
	
	
	public List<User> listUserss(User user) {

		return userRepository.listUserss(user);
	}

	public User getUser(int empid) {

		return userRepository.getUser(empid);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteUser(User user) throws GenericException {
		logger.info("Entrando al deleteUser");

		 userRepository.deleteUser(user);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<User> listUsersPagination(User user, int page, int size) {

		return userRepository.listUsersPagination(user, page, size);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRowsSearch(String query) {

		return userRepository.getTotalRowsSearch(query);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows(String query) {

		return userRepository.getTotalRows(query);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long getTotalRows() {

		return userRepository.getTotalRows();
	}

	


	public List<User> listUserssQuery(User user, String query, int page, int size) {

		return userRepository.listUserssQuery(user, query, page, size);
	}


	@Override
	public List<User> consultarUser(User user) {		
		return userRepository.consultarInformacionPorUsuario(user);
	}

	@Override
	public List<User> consultarUser(String userName) {
		return userRepository.consultarInformacionPorUsuario(userName);
	}
	

	

}

