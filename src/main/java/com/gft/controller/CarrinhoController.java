package com.gft.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.model.Carrinho;
import com.gft.model.Compra;
import com.gft.model.Evento;
import com.gft.model.FormaPagto;
import com.gft.model.Usuario;
import com.gft.repository.CarrinhoCompraInter;
import com.gft.repository.CompraInter;
import com.gft.repository.EventosInter;
import com.gft.repository.UsuarioInter;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	private static final String CARRINHOVIEW = "Carrinho";

	@Autowired
	private EventosInter eventos;
	
	@Autowired
	private UsuarioInter usuarioInter;
	
	@Autowired
	private CompraInter compraInter;
	
	@Autowired
	private CarrinhoCompraInter carrinhoInter;
	
	private List<Carrinho> carrinhoCompra = new ArrayList<Carrinho>();
	
	private Usuario usuario;

	private Compra compra = new Compra();

	public void calcularTotal() {
		compra.setValorTotal(BigDecimal.valueOf(0));
		for (Carrinho it : carrinhoCompra) {
			compra.setValorTotal(compra.getValorTotal().add(it.getValorTotal()));
		}
	}

	@RequestMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView(CARRINHOVIEW);
		calcularTotal();
		mv.addObject("compra", compra);
		mv.addObject("listaCarrinho", carrinhoCompra);
		mv.addObject(new Carrinho());
		return mv;
	}

	@RequestMapping("{codigo}")
	public String addCarrinho(@PathVariable Long codigo) {

		Optional<Evento> evento = eventos.findById(codigo);
		Evento event = evento.get();
		Carrinho carrinho = new Carrinho();

		int controller = 0;

		for (Carrinho it : carrinhoCompra) {
			if (it.getEvento().getCodigo().equals(event.getCodigo()) && it.getIngressoDisp() > 0) {
				it.setQuantidade(it.getQuantidade() + 1);
				it.setIngressoDisp(it.getIngressoDisp() - 1);
				it.setValorTotal(BigDecimal.valueOf(0));
				it.setValorTotal(
						it.getValorTotal().add(BigDecimal.valueOf(it.getQuantidade()).multiply(it.getValorUnitario())));
				controller = 1;
				break;
			}
		}

		if (controller == 0) {
			carrinho.setEvento(event);
			carrinho.setValorUnitario(event.getValorIngresso());
			carrinho.setQuantidade(carrinho.getQuantidade() + 1);
			carrinho.setIngressoDisp(event.getCapacidade() - 1);
			carrinho.setValorTotal(carrinho.getValorTotal()
					.add(BigDecimal.valueOf(carrinho.getQuantidade()).multiply(carrinho.getValorUnitario())));
			carrinhoCompra.add(carrinho);
		}

		return "redirect:/carrinho";

	}

	@RequestMapping("/altera/{codigo}/{acao}")
	public String altera(@PathVariable Long codigo, @PathVariable int acao, RedirectAttributes attributes) {

		for (Carrinho it : carrinhoCompra) {
			if (it.getEvento().getCodigo().equals(codigo)) {
				if (acao == 1) {
					it.setQuantidade(it.getQuantidade() + 1);
					it.setIngressoDisp(it.getIngressoDisp() - 1);
					it.setValorTotal(BigDecimal.valueOf(0));
					it.setValorTotal(it.getValorTotal()
							.add(BigDecimal.valueOf(it.getQuantidade()).multiply(it.getValorUnitario())));
				} else if (acao == 0) {
					it.setQuantidade(it.getQuantidade() - 1);
					it.setIngressoDisp(it.getIngressoDisp() + 1);
					it.setValorTotal(BigDecimal.valueOf(0));
					it.setValorTotal(it.getValorTotal()
							.add(BigDecimal.valueOf(it.getQuantidade()).multiply(it.getValorUnitario())));
				}
				break;
			}
		}

		return "redirect:/carrinho";
	}

	@RequestMapping(value = "/excluir/{codigo}")
	public String excluir(@PathVariable Long codigo) {

		for (Carrinho it : carrinhoCompra) {
			if (it.getEvento().getCodigo().equals(codigo)) {
				carrinhoCompra.remove(it);
				break;
			}
		}
		return "redirect:/carrinho";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvarCompra(String formaPagtos) {
		//buscar usuario
		ModelAndView mv = new ModelAndView("Historico");
		mv.addObject(new Carrinho());
		mv.addObject("usuario", usuario);
		mv.addObject("listaCarrinho", carrinhoCompra);
		mv.addObject("compra", compra);
				
		//compra.setFormaPagto(formaPagtos);
		compra.setUsuario(usuario);		
		compraInter.save(compra);
		
		for(Carrinho it: carrinhoCompra) {
			it.setCompra(compra);
			carrinhoInter.saveAndFlush(it);
		}
		
		carrinhoCompra = new ArrayList<>();
		compra = new Compra();
		compraInter.save(compra);
		
		return mv;
	}

	@ModelAttribute("formaPagto")
	public List<FormaPagto> formaPagto() {
		return Arrays.asList(FormaPagto.values());
	}
}