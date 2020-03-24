package com.gft.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.model.Usuario;
import com.gft.repository.UsuarioInter;

@Controller
@RequestMapping("/cadastrousuario")
public class CadastroUsuarioController {

	@Autowired
	private UsuarioInter usuarios;
	
	@RequestMapping
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView("CadastroUsuario");
		mv.addObject(new Usuario());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Usuario usuario, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return "CadastroUsuario";
		}
		usuarios.save(usuario);
		attributes.addFlashAttribute("mensagem1", "Usuário cadastrado com sucesso!");
		return "redirect:/cadastrousuario";
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public String userExiste(RedirectAttributes attributes) {
		attributes.addFlashAttribute("mensagem", "Este usuário já existe!");
		return "redirect:/cadastrousuario";
	}
}
