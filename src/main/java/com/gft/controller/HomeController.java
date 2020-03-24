package com.gft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.model.Casa;
import com.gft.model.Evento;
import com.gft.repository.CasasShowsInter;
import com.gft.repository.EventosInter;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private EventosInter eventos;
	
	@Autowired
	private CasasShowsInter casas;
	
	@RequestMapping
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView("Home");
		return mv;
	}
	
	@ModelAttribute("eventos")
	public List<Evento> eventos(){
		return eventos.findAll();
	}
	
	@ModelAttribute("casaShow")
	public List<Casa> casaShow(){
		return casas.findAll();
	}
}
