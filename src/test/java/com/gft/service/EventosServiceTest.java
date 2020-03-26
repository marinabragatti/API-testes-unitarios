package com.gft.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.gft.model.Casa;
import com.gft.model.Evento;
import com.gft.model.GeneroMusical;
import com.gft.repository.CasasShowsInter;
import com.gft.repository.EventosInter;

@SpringBootTest
@AutoConfigureMockMvc
public class EventosServiceTest {

	@InjectMocks
	private EventosService eventosService;

	@Mock
	private EventosInter eventosInterface;
	
	@Mock
	private CasasShowsInter casasInterface;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void deveSalvarEventoComSucesso() {
		//cenario
		Casa casa = new Casa();
		
		Evento evento = new Evento();
		evento.setNomeEvento("Show 1");
		evento.setCapacidade(10);
		evento.setDataEvento(Calendar.getInstance().getTime());
		evento.setGenero(GeneroMusical.AXE);
		evento.setValorIngresso(BigDecimal.valueOf(100));
		evento.setCasaShow(casa);
		
		//acao
		eventosService.salvar(evento);
		
		//verificacao
		Assert.assertThat(evento.getNomeEvento(), is(equalTo("Show 1")));
		Assert.assertThat(evento.getCapacidade(), is(equalTo(10)));
		Assert.assertThat(evento.getDataEvento(), is(equalTo(Calendar.getInstance().getTime())));
		Assert.assertThat(evento.getGenero(), is(equalTo("Axé")));
		Assert.assertThat(evento.getValorIngresso(), is(equalTo(100)));
		Assert.assertThat(evento.getCasaShow(), is(equalTo(casa)));
	}
	
}
