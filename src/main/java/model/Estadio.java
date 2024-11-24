package model;

public class Estadio extends Usuario {
	private double tamanho;
	private double areaCampo;
	private double gastosMensalKw;
	private double gastosMensalReais;
	public Estadio(int id, String nome, String senha, String email, String numero, double areaCampo,double tamanho, double gastosMensalKw, double gastosMensalReais) {
		super(id, nome, senha, email, numero);
		this.tamanho=tamanho;
		this.areaCampo=areaCampo;
		this.gastosMensalKw=gastosMensalKw;
		this.gastosMensalReais=gastosMensalReais;
	}
	public Estadio(String nome, double tamanho) {
		super(0,nome,null,null,null);
		this.tamanho=tamanho;
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
	public double getGastosMensalReais() {
		return gastosMensalReais;
	}
	public void setGastosMensalReais(double gastosMensalReais) {
		this.gastosMensalReais = gastosMensalReais;
	}
	@Override
	public String toString() {
	    return "Estadio [tamanho=" + tamanho + ", areaCampo=" + areaCampo + ", gastosMensaisKw=" + gastosMensalKw
	            + ", gastosMensaisReais=" + gastosMensalReais + ", id=" + getId() + ", nome=" + getNome()
	            + ", email=" + getEmail() + ", numero=" + getNumero() + "]";
	}
	
}
