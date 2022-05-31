package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ShoesManagementService;

@Controller
@RequestMapping("/shoes-management")
public class ShoesManagerController {

	@Autowired
	private ShoesManagementService shoesManagementService;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("shoesResponseModels", shoesManagementService.getShoes());
		return "shoes-management";
	}

	@GetMapping("/search")
	public String search(@RequestParam("searchInfo") String search, Model model) {
		if (search == null || "".equals(search)) {
			return "redirect:/shoes-management";
		}

		model.addAttribute("shoesResponseModels", shoesManagementService.getShoes(search));
		return "shoes-management";
	}
}
