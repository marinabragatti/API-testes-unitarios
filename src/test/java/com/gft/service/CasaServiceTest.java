package com.gft.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.gft.model.Casa;
import com.gft.repository.CasasShowsInter;

@SpringBootTest
@AutoConfigureMockMvc
public class CasaServiceTest {
	
	@InjectMocks
	private CasasService casasService;
	
	@Mock
	private CasasShowsInter casasInterface;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void deveSalvarCasaComSucesso() {
		//cenario
		Casa casa = new Casa();
		casa.setNomeCasa("Casa de Show 1");
		casa.setEndereco("Rua das Palmeiras");
		
		//acao
		casasService.salvar(casa);
		
		//verificacao
		Assert.assertThat(casa.getNomeCasa(), is(equalTo("Casa de Show 1")));
		Assert.assertThat(casa.getEndereco(), is(equalTo("Rua das Palmeiras")));
	}
	
	@Test
	public void deveDeletarCasaComSucesso() throws Exception {
		//cenario
		Casa casa = new Casa();
		casa.setCodigo((long) 1);
		casa.setNomeCasa("Casa de Show 1");
		casa.setEndereco("Rua das Palmeiras");
		
		//acao
		casasService.deletar(casa.getCodigo());
		
		//verificacao
		Mockito.verify(casasInterface, times(1)).deleteById(casa.getCodigo());
	}
	
	@Test
	public void deveListarCasa() {
		//cenario
		Casa casa = new Casa();
		casa.setNomeCasa("Casa de Show 1");
		casa.setEndereco("Rua das Palmeiras");
		
		Casa casa2 = new Casa();
		casa2.setNomeCasa("Casa de Show 2");
		casa2.setEndereco("Rua das Oliveiras");
		
		List<Casa> casas = new ArrayList<Casa>();
		casas.add(casa);
		casas.add(casa2);
		
		//acao
		casasService.salvar(casa);
		casasService.salvar(casa2);
		Mockito.when(casasService.listar()).thenReturn(casas);
		
		//verificacao
		
	}
	
	@Test
	public void deveAtualizarCasa() {
		//cenario
		Casa casa = new Casa();
		casa.setNomeCasa("Casa de Show 1");
		casa.setEndereco("Rua das Palmeiras");
		
		casasService.salvar(casa);
		
		casa.setNomeCasa("Casa de Show 2");
		casa.setEndereco("Rua das Oliveiras");
		
		//acao
		casasService.salvar(casa);
		
		//verificacao
		Assert.assertThat(casa.getNomeCasa(), is(equalTo("Casa de Show 2")));
		Assert.assertThat(casa.getEndereco(), is(equalTo("Rua das Oliveiras")));
	}
	
	@Test
	public void devePesquisarCasa() throws Exception {
		//cenario
		Casa casa = new Casa();
		casa.setNomeCasa("Casa de Show A");
		casa.setEndereco("Rua das Palmeiras");
		
		Casa casa2 = new Casa();
		casa2.setNomeCasa("Casa de Show B");
		casa2.setEndereco("Rua das Oliveiras");
		List<Casa> casasList = new ArrayList<Casa>();
		
		//acao
		casasService.pesquisar(casasList, "Casa de Show B");
		
		//verificacao
		Assert.assertThat(casasList.size(), is(equalTo(1)));
	}
}
