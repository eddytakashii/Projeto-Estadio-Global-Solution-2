package model;

public class Orcamento {
	private int idOrcamento;
	private int idEstadio;
	private double tamanho;
	private double areaCampo;
	private double gastosMensalKw;
	private double tetoGasto;
	private boolean aprovado;
	
	public Orcamento(int idOrcamento, int idEstadio, double tamanho,double areaCampo, double gastosMensalKw, double tetoGasto,boolean aprovado) {
		this.idOrcamento=idOrcamento;
		this.idEstadio=idEstadio;
		this.tamanho=tamanho;
		this.areaCampo=areaCampo;
		this.gastosMensalKw=gastosMensalKw;
		this.tetoGasto=tetoGasto;
		this.aprovado=aprovado;
	}

	public int getIdOrcamento() {
		return idOrcamento;
	}

	public void setIdOrcamento(int idOrcamento) {
		this.idOrcamento = idOrcamento;
	}

	public int getIdEstadio() {
		return idEstadio;
	}

	public void setIdEstadio(int idEstadio) {
		this.idEstadio = idEstadio;
	}

	public double getTamanho() {
		return tamanho;
	}

	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;
	}

	public double getAreaCampo() {
		return areaCampo;
	}

	public void setAreaCampo(double areaCampo) {
		this.areaCampo = areaCampo;
	}

	public double getGastosMensalKw() {
		return gastosMensalKw;
	}

	public void setGastosMensalKw(double gastosMensalKw) {
		this.gastosMensalKw = gastosMensalKw;
	}

	public double getTetoGasto() {
		return tetoGasto;
	}

	public void setTetoGasto(double tetoGasto) {
		this.tetoGasto = tetoGasto;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	@Override
	public String toString() {
		return "Orcamento [idOrcamento=" + idOrcamento + ", idEstadio=" + idEstadio + ", tamanho=" + tamanho
				+ ", areaCampo=" + areaCampo + ", gastosMensalKw=" + gastosMensalKw + ", tetoGasto=" + tetoGasto
				+ ", aprovado=" + aprovado + "]";
	}
	
}

