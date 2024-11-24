package model;

public class Usuario {
	private int id;
	private String nome;
	private String senha;
	private String email;
	private String numero;
	
	public Usuario(int id, String nome, String senha, String email, String numero) {
		this.id=id;
		this.nome=nome;
		this.senha=senha;
		this.email=email;
		this.numero=numero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", senha=" + senha + ", email=" + email + ", numero=" + numero
				+ "]";
	}
	
	
}
