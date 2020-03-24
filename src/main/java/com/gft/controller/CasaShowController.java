package com.gft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.model.Casa;
import com.gft.repository.CasasShowsInter;

@Controller
@RequestMapping("/casashow")
public class CasaShowController {
	
	private static final String CASAVIEW = "CadastroCasaShow";
	
	@Autowired
	private CasasShowsInter casas;
	
	@RequestMapping
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView(CASAVIEW);
		mv.addObject(new Casa());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Casa casa, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return CASAVIEW;
		}
		casas.save(casa);
		attributes.addFlashAttribute("mensagem1", "Casa de Show salva com sucesso!");
		return "redirect:/casashow";
	}

	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Casa casa) {
		ModelAndView mv = new ModelAndView(CASAVIEW);
		mv.addObject(casa);
		return mv;
	}

	@RequestMapping(value="{codigo}", method= RequestMethod.POST)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		casas.deleteById(codigo);
		attributes.addFlashAttribute("mensagem1", "Casa de Show excluída com sucesso!");
		return "redirect:/casashow";
	}
	
	@ModelAttribute("todasCasaShow")
	public List<Casa> todasCasaShow() {
		return casas.findAll();
	}
}

