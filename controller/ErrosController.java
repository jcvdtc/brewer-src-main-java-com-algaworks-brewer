package com.algaworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrosController {

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String paginaNaoEncontrada() {
		return "404";
	}

	@RequestMapping("/500")
	public String erroNoServidor() {
		return "500";
	}
}
