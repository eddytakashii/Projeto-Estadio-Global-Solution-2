package model;

public class TipoChao {
	private int idTipoChao;
	private String nome;
	private double geracaoEstimativaKw;
	private double custoPorMetro;
	
	public TipoChao(int idTipoChao, String nome, double geracaoEstimativaKw,double custoPorMetro) {
		this.idTipoChao=idTipoChao;
		this.nome=nome;
		this.geracaoEstimativaKw=geracaoEstimativaKw;
		this.custoPorMetro=custoPorMetro;
	}

	public int getIdTipoChao() {
		return idTipoChao;
	}

	public void setIdTipoChao(int idTipoChao) {
		this.idTipoChao = idTipoChao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getGeracaoEstimativaKw() {
		return geracaoEstimativaKw;
	}

	public void setGeracaoEstimativaKw(double geracaoEstimativaKw) {
		this.geracaoEstimativaKw = geracaoEstimativaKw;
	}

	public double getCustoPorMetro() {
		return custoPorMetro;
	}

	public void setCustoPorMetro(double custoPorMetro) {
		this.custoPorMetro = custoPorMetro;
	}

	@Override
	public String toString() {
		return "TipoChao [idTipoChao=" + idTipoChao + ", nome=" + nome + ", geracaoEstimativaKw=" + geracaoEstimativaKw
				+ ", custoPorMetro=" + custoPorMetro + "]";
	}
	
}
