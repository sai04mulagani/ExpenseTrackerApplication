 package com.jsp.ExpenseTracker.dao;
  


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.ExpenseTracker.entity.User;
import com.jsp.ExpenseTracker.repository.UserRepository;
@Component
public class UserDaoImpl implements UserDao
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) 
	{
		/*
		 * to insert record in User Table
		 */
		return userRepository.save(user);
	}
	
	@Override
	public User loginUser(String username, String password)
	{
		/*
		 * to verify user based on username and password
		 */
//		User user=userRepository.findByUserNameAndPassword(username , password).orElse(null);
//		return user;
//		
		return userRepository.findByUserNameAndPassword(username, password).orElse(null);
	}

	@Override
	public boolean deleteUser(int userId) {
		try {
			userRepository.deleteById(userId);
			return true;
		} 
		catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public User updateUserData(User user , int userId) {
		User userDataDromDB=userRepository.findById(userId).orElse(null);
		System.out.println(userDataDromDB);
		//update data
		if(userDataDromDB !=null)
		{
			String username=user.getUserName();
			userDataDromDB.setUserName(username);
			userDataDromDB.setFullName(user.getFullName());
			userDataDromDB.setMobile(user.getMobile());
			userDataDromDB.setEmail(user.getEmail());
			userDataDromDB.setPassword(user.getPassword());
			//to save data
			return userRepository.save(userDataDromDB);
		}
		
		
		return null;
	}

	@Override
	public User forgetPassword(String username, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}