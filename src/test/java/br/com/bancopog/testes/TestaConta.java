package br.com.bancopog.testes;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import br.com.bancopog.dominio.Cliente;
import br.com.bancopog.dominio.Conta;
import br.com.bancopog.servico.Banco;
import br.com.bancopog.servico.OperacoesBancarias;

public class TestaConta {
	
	private static final double TOLERANCIA = 0.001;
	private Conta conta;
	private Cliente titular;
	
	@Before
	public void init() {
		
		String cpf = "123.456.789-10";
		String nome = "Ana";
		
		titular = new Cliente(nome, cpf);
	
		conta = new Conta(123, Calendar.getInstance(), titular);
		conta.setLimite(new BigDecimal(100));
	}
	
	@Test
	public void testaMovimentacao() {
		OperacoesBancarias opers = new OperacoesBancarias();
		conta.deposita(new BigDecimal(250));
		Assert.assertEquals(250.0, conta.getSaldo().doubleValue(), TOLERANCIA);
		conta.saca(new BigDecimal(99));
		Assert.assertEquals(151.0,  conta.getSaldo().doubleValue(), TOLERANCIA);
	}
	
	@Test
	public void testaNovaConta() {

		String cpf = "123.456.789-10";
		String nome = "Ana";
		
		Banco banco = new Banco();
		if (banco.consultaSerasa(cpf)) {
			try {
				
				Cliente titular = banco.criaCliente(nome, cpf);
				int numeroDaConta = banco.geraNumeroConta();
				Conta novaConta = banco.criaConta(titular);

				assertEquals(novaConta.getTitular().getNome(),nome);
				assertEquals(0.0,novaConta.getLimite().doubleValue(), TOLERANCIA);
				assertEquals(0.0,novaConta.getSaldo().doubleValue(), TOLERANCIA);
				
			} catch (SQLException e) {
				Assert.fail();
			}
		} else {
			Assert.fail();
		}
	}
	
}
