package com.hemanth.demo.objectrepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hemanth.demo.object.object;

public interface objectrepo extends JpaRepository<object,Long>{
	
	object findByEmail(String email);
}
