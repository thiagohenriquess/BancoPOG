package br.com.bancopog.dominio;

public class Cliente {

	private String nome;
	private String cpf;
	
	public Cliente(String nome, String cpf){
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Cliente) {
			Cliente outro = (Cliente)obj;
			if(!outro.cpf.equals(this.cpf)) return false;
			if(!outro.nome.equals(this.nome)) return false;
			
		} else {
			return false;
		}
		return super.equals(obj);
	}
	
}
