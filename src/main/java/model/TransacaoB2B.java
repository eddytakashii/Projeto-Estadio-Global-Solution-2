package model;

public class TransacaoB2B {
	private int idTransacao;
	private int idProposta;
	private int idAdmin;
	private double valor;
	private String descricao;
	
	public TransacaoB2B(int idTransacao, int idProposta,int idAdmin, double valor, String descricao) {
		this.idTransacao=idTransacao;
		this.idProposta=idProposta;
		this.idAdmin=idAdmin;
		this.valor=valor;
		this.descricao=descricao;
	}

	public int getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(int idTransacao) {
		this.idTransacao = idTransacao;
	}

	public int getIdProposta() {
		return idProposta;
	}

	public void setIdProposta(int idProposta) {
		this.idProposta = idProposta;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "TransacaoB2B [idTransacao=" + idTransacao + ", idProposta=" + idProposta + ", idAdmin=" + idAdmin
				+ ", valor=" + valor + ", descricao=" + descricao + "]";
	}
	
}
