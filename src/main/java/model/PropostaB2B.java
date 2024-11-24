package model;

public class PropostaB2B {
	private int idProposta;
	private int idOrcamento;
	private int idTipoChao;
	private double valor;
	private String descricao;
	private double reducaoKw;
	
	public PropostaB2B(int idProposta, int idOrcamento, int idTipoChao, double valor,String descricao,double reducaoKw) {
		this.idProposta=idProposta;
		this.idOrcamento=idOrcamento;
		this.idTipoChao=idTipoChao;
		this.valor=valor;
		this.descricao=descricao;
		this.reducaoKw=reducaoKw;
	}

	public int getIdProposta() {
		return idProposta;
	}

	public void setIdProposta(int idProposta) {
		this.idProposta = idProposta;
	}

	public int getIdOrcamento() {
		return idOrcamento;
	}

	public void setIdOrcamento(int idOrcamento) {
		this.idOrcamento = idOrcamento;
	}

	public int getIdTipoChao() {
		return idTipoChao;
	}

	public void setIdTipoChao(int idTipoChao) {
		this.idTipoChao = idTipoChao;
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

	public double getReducaoKw() {
		return reducaoKw;
	}

	public void setReducaoKw(double reducaoKw) {
		this.reducaoKw = reducaoKw;
	}

	@Override
	public String toString() {
		return "PropostaB2B [idProposta=" + idProposta + ", idOrcamento=" + idOrcamento + ", idTipoChao=" + idTipoChao
				+ ", valor=" + valor + ", descricao=" + descricao + ", reducaoKw=" + reducaoKw + "]";
	}
	
}
