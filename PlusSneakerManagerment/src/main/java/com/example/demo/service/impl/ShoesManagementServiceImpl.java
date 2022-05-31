package com.example.demo.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.response.ShoesResponseModel;
import com.example.demo.service.ShoesManagementService;
import com.google.gson.Gson;

@Service
public class ShoesManagementServiceImpl implements ShoesManagementService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<ShoesResponseModel> getShoes() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		System.out.println(restTemplate.exchange("https://nikestoreapp.000webhostapp.com/newrealeses.php",
		HttpMethod.GET, entity, String.class).getBody());
		String jsonString = restTemplate
				.exchange("https://nikestoreapp.000webhostapp.com/newrealeses.php", HttpMethod.GET, entity,
						String.class)
				.getBody();
		return Arrays.asList(new Gson().fromJson(jsonString, ShoesResponseModel[].class));
	}
	
	@Override
	public List<ShoesResponseModel> getShoes(String search) {
		String searchLowcase = search.toLowerCase().trim();
		List<ShoesResponseModel> shoesResponseModels = getShoes();
		shoesResponseModels = shoesResponseModels.stream().filter(ele -> ele.getName().toLowerCase().trim().contains(searchLowcase)).collect(Collectors.toList());
		return shoesResponseModels;
	}

}
