package isa.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IsaController {
	@RequestMapping(value="/")
	public String hello(){
		return "Evo ise";
	}
}
