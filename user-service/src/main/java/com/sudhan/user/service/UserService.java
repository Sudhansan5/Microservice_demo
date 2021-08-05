package com.sudhan.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sudhan.user.VO.Department;
import com.sudhan.user.VO.ResponseTemplateVO;
import com.sudhan.user.entity.User;
import com.sudhan.user.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private RestTemplate restTemplate;
	
	public User saveUser(User user) {
//        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }
	
	   public ResponseTemplateVO getUserWithDepartment(Long userId) {
//	        log.info("Inside getUserWithDepartment of UserService");
	        ResponseTemplateVO vo = new ResponseTemplateVO();
	        User user = userRepository.findByUserId(userId);

	        Department department =
	                restTemplate.getForObject("http://localhost:9001/departments/" + user.getDepartmentId()
	                        ,Department.class);

	        vo.setUser(user);
	        vo.setDepartment(department);

	        return  vo;
	    }
}
