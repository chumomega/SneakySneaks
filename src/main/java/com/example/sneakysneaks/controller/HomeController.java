package com.example.sneakysneaks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({"/", "/login", "/signup", "/landing"})
	public String index() {
		return "index";
	}
}
