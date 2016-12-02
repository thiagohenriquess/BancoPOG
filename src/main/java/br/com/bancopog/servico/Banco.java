package br.com.bancopog.servico;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

import br.com.bancopog.dominio.Cliente;
import br.com.bancopog.dominio.Conta;
import br.com.bancopog.persistencia.Dao;

public class Banco {

	public boolean consultaSerasa(String cpf) {
		return true;
	}

	public Cliente criaCliente(String campoNome, String campoCpf) throws SQLException {
	
		Cliente cliente = new Cliente(campoNome, campoCpf);
		
		registraCliente(cliente);

		return cliente;
	}

	private Boolean registraCliente(Cliente cliente) {
		Dao<Cliente> dao = new Dao<Cliente>();
		try {
			dao.adiciona(cliente);
		} catch (SQLException e) {
			e.getMessage();
			return false;
		}

		return true;
	}

	private static int geradorDeNumeros = 10000;

	public int geraNumeroConta() {
		
		geradorDeNumeros += new Random(42).nextInt(1000);
		return geradorDeNumeros++;
	}

	public Conta criaConta(Cliente titular) throws SQLException {

		if (consultaSerasa(titular.getCpf())) {

			int numero = geraNumeroConta();
			Calendar hoje = Calendar.getInstance();
			Conta novaConta = new Conta(numero, hoje, titular);

			registraConta(novaConta);

			return novaConta;
		}

		return null;
	}

	private Boolean registraConta(Conta conta) {

		Dao<Conta> dao = new Dao<Conta>();
		try {
			dao.adiciona(conta);
		} catch (SQLException e) {
			e.getMessage();
			return false;
		}

		return true;
	}

}
