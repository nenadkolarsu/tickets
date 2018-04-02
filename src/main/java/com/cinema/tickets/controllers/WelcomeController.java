package com.cinema.tickets.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";
	@Value("${cinema.rows}")
	private Integer rows;
	@Value("${cinema.seats}")
	private Integer seats;
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		model.put("rows", this.rows);
		model.put("seats", this.seats);
		return "welcome";
	}
	
    @RequestMapping(value="/414.html")
    public String Error414(Map<String, Object> model, @RequestParam String ops, HttpServletRequest request){
		request.setAttribute("ops", ops);	
        return "414";
    }
    

}