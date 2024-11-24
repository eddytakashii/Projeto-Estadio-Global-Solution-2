package model;

public class Relatorio {
	private int idRelatorio;
	private int idEstadio;
	private String descricao;
	private String conclusao;
	
	public Relatorio(int idRelatorio, int idEstadio, String descricao, String conclusao) {
		this.idRelatorio=idRelatorio;
		this.idEstadio=idEstadio;
		this.descricao=descricao;
		this.conclusao=conclusao;
	}

	public int getIdRelatorio() {
		return idRelatorio;
	}

	public void setIdRelatorio(int idRelatorio) {
		this.idRelatorio = idRelatorio;
	}

	public int getIdEstadio() {
		return idEstadio;
	}

	public void setIdEstadio(int idEstadio) {
		this.idEstadio = idEstadio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConclusao() {
		return conclusao;
	}

	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}

	@Override
	public String toString() {
		return "Relatorio [idRelatorio=" + idRelatorio + ", idEstadio=" + idEstadio + ", descricao=" + descricao
				+ ", conclusao=" + conclusao + "]";
	}
	
}
