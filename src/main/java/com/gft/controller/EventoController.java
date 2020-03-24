package com.gft.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.gft.model.Evento;
import com.gft.model.GeneroMusical;
import com.gft.repository.CasasShowsInter;
import com.gft.repository.EventosInter;

@Controller
@RequestMapping("/evento")
public class EventoController {
	
	private static final String EVENTOVIEW = "CadastroEvento";
	
	@Autowired
	private EventosInter eventos;
	
	@Autowired
	private CasasShowsInter casas;

	@RequestMapping
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView(EVENTOVIEW);
		mv.addObject(new Evento());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Evento evento, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return EVENTOVIEW;
		}
		
		try {
		eventos.save(evento);
		attributes.addFlashAttribute("mensagem1", "Evento salvo com sucesso!");
		return "redirect:/evento";
		}
		catch(DataIntegrityViolationException e){
			errors.rejectValue("dataEvento", null, "Formato de data inválido");
			return EVENTOVIEW;
		}
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Evento evento) {
		ModelAndView mv = new ModelAndView(EVENTOVIEW);
		mv.addObject(evento);
		return mv;
	}
	
	@RequestMapping(value="{codigo}", method= RequestMethod.POST)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		eventos.deleteById(codigo);
		attributes.addFlashAttribute("mensagem1", "Evento excluído com sucesso!");
		return "redirect:/evento";
	}
	
	@ModelAttribute("todosGeneros")
	public List<GeneroMusical> todosGeneros(){
		return Arrays.asList(GeneroMusical.values());
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
