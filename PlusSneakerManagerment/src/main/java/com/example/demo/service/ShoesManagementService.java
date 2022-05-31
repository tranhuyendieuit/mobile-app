package com.example.demo.service;

import java.util.List;

import com.example.demo.model.response.ShoesResponseModel;

public interface ShoesManagementService {
	
	List<ShoesResponseModel> getShoes();
	
	List<ShoesResponseModel> getShoes(String search);
}
