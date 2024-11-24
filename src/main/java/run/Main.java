package run;

import java.sql.Connection;

import connection.ConnectionFactory;
import dao.*;
import model.*;
import view.MenuConsole;


public class Main {
	public static void main(String[] args) {
		try (Connection connection= ConnectionFactory.getConnection()){
			if (connection!=null) {
				System.out.println("Conexão realizada com sucesso");
			}
			
			MenuConsole menu=new MenuConsole();
			menu.iniciarSistema();
			
//			UsuarioDAO usuariodao=new UsuarioDAO();
//			Usuario us1=usuariodao.login("joao@hotmail.com", "1234");
//			System.out.println(us1);
//			System.out.println(usuariodao.isUsuarioAdmin(1));
//			TipoChaoDAO tipodao =new TipoChaoDAO();
//			tipodao.listarTodos().stream().forEach(System.out::println);
//			
//			RelatorioDAO relatoriodao=new RelatorioDAO();
			//relatoriodao.listarTodos().stream().forEach(System.out::println);
			//Relatorio rl1=new Relatorio(0,1,"descricaobasica","conclusao");
			//relatoriodao.criarRelatorio(rl1);
			//relatoriodao.removerRelatorio(2);
			//relatoriodao.listarRelatoriosPorEstadio(1).stream().forEach(System.out::println);
			
			//EstadioDAO estadiodao= new EstadioDAO();
			//Estadio est1= new Estadio(8,"Neoquimica","1234","corinthians123@hotmail.com","12 99999-9999", 100001,77000,2000,50000);
			//estadiodao.criarEstadio(est1);
			
			//estadiodao.removerEstadio(2);
			
			//estadiodao.atualizarEstadio(est1);
			
			//OrcamentoDAO orcamentodao=new OrcamentoDAO();
			//Orcamento orc1=new Orcamento(7,3,est1.getTamanho(),est1.getAreaCampo(),est1.getGastosMensalKw(),81000,false);
			//orcamentodao.criarOrcamento(orc1);
			//System.out.println(orcamentodao.buscarOrcamentoPorId(6));
			//orcamentodao.atualizarOrcamento(orc1);
			//orcamentodao.listarTodos().forEach(System.out::println);
			//orcamentodao.listarTodosPendentes().forEach(System.out::println);
			
			//PropostaB2BDAO propostaB2B=new PropostaB2BDAO();
			//PropostaB2B pro1=new PropostaB2B(0,7,2,120000,"Proposta feita para o tipo de chao",1000);
			//propostaB2B.criarPropostaB2B(pro1);
			//propostaB2B.listarTodos().forEach(System.out::println);
			
//			TransacaoB2BDAO transacaoB2B=new TransacaoB2BDAO();
//			TransacaoB2B tr=new TransacaoB2B(0,3,1,120000,"Transacao realizada");
//			transacaoB2B.criarTransacaoB2B(tr);
//			transacaoB2B.listarTodos() .forEach(System.out::println);
			
			//System.out.println(estadiodao.buscarPorUsuarioId(5));
			//estadiodao.listarTodos().stream().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
