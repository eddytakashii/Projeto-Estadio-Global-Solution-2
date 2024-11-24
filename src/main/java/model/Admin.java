package model;

public class Admin extends Usuario {

	public Admin(int id, String nome, String senha, String email, String numero) {
		super(id, nome, senha, email, numero);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Admin [getId()=" + getId() + ", getNome()=" + getNome() + ", getSenha()=" + getSenha() + ", getEmail()="
				+ getEmail() + ", getNumero()=" + getNumero() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}
