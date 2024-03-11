package com.hemanth.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hemanth.demo.object.object;
import com.hemanth.demo.objectrepo.objectrepo;

@Service
public class myUserDetailsService implements UserDetailsService {
	
	@Autowired
	private objectrepo repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		object obj = repo.findByEmail(email);
		if(obj==null) {
			throw new UsernameNotFoundException("404 not found.");
		}
		
		return new UserPrincipal(obj);
	}

}
