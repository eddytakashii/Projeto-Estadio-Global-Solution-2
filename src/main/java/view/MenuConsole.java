package view;

import java.util.List;
import java.util.Scanner;

import dao.*;
import model.*;

public class MenuConsole {
	private final UsuarioDAO usuarioDAO;
	private final EstadioDAO estadioDAO;
	private final RelatorioDAO relatorioDAO;
	private final OrcamentoDAO orcamentoDAO;
	private final PropostaB2BDAO propostaB2BDAO;
	private final TransacaoB2BDAO transacaoB2BDAO;
	
	public MenuConsole() {
		this.usuarioDAO=new UsuarioDAO();
		this.estadioDAO=new EstadioDAO();
		this.relatorioDAO=new RelatorioDAO();
		this.orcamentoDAO=new OrcamentoDAO();
		this.propostaB2BDAO=new PropostaB2BDAO();
		this.transacaoB2BDAO=new TransacaoB2BDAO();
	}
	
	public void iniciarSistema() {
		try(Scanner scanner=new Scanner(System.in)){
			System.out.println("=== Bem vindo ao sistema de gestão elétrica de estádio ===");
			
			Usuario usuarioLogado=null;
			while(usuarioLogado==null) {
				System.out.println("1- Login");
				System.out.println("2- Cadastro");
				System.out.println("3- Sair");
				System.out.print("Escolha umas das opções: ");
				String input = scanner.nextLine();
				int escolha;
				
				try {
					escolha = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Opção inválida. Insira uma opção válida.");
					continue;
				}
				
				if (escolha ==1) {
					System.out.print("Digite seu email: ");
					String email = scanner.nextLine();
					System.out.print("Digite sua senha: ");
					String senha = scanner.nextLine();
					
					usuarioLogado=usuarioDAO.login(email, senha);
					if(usuarioLogado==null) {
						System.out.println("Credenciais inválidas. Digite novamente. ");
					}else {
						System.out.println("Login relizado com sucesso! Bem vindo, " + usuarioLogado.getNome());
					}
				}else if(escolha==2){
					
					
					cadastrarEstadioUsuario(scanner);
				}else if(escolha==3) {
					System.out.println("Encerrando o sistema");
					return;
				}else {
					System.out.println("Opção inválida. Digite novamente.");
				}
				}
			if(isAdmin(usuarioLogado)) {
				adminMenu(scanner, usuarioLogado);
			}else {
				clienteMenu(scanner, usuarioLogado);
			}
			scanner.close();
		}
		}
	
	private void cadastrarEstadioUsuario(Scanner scanner) {
		System.out.println("\n=== Cadastro de estádio ===");
		System.out.println("Nome do estádio(também do usuário): ");
		String nome=scanner.nextLine();
		
		System.out.println("Email do estádio(também do usuário): ");
		String email=scanner.nextLine();
		
		System.out.println("Senha do estádio(também do usuário): ");
		String senha=scanner.nextLine();
		
		System.out.println("Número de contato do estádio(também do usuário): ");
		String numero=scanner.nextLine();
		
		System.out.println("Digite o tamanho do estádio: ");
		double tamanho =scanner.nextDouble();
		
		System.out.println("Digite a área do campo do estádio: ");
		double areaCampo =scanner.nextDouble();
		
		System.out.println("Digite os gastos mensais(kW) do estádio: ");
		double gastosMensaisKw =scanner.nextDouble();
		
		System.out.println("Digite os gastos mensais(R$) do estádio: ");
		double gastosMensaisReais =scanner.nextDouble();
		
		Estadio estadio=new Estadio(0,nome,senha,email,numero,tamanho,areaCampo,gastosMensaisKw, gastosMensaisReais);
		boolean criado=estadioDAO.criarEstadio(estadio);
		
		if(criado) {
			System.out.println("Cadastrado com sucesso!");
		}else {
			System.out.println("Falha ao cadastrar.");
		}
		
	}

	private void clienteMenu(Scanner scanner, Usuario usuarioLogado) {
		int opcao;
		do {
			System.out.println("\n=== Menu Cliente ===");
			System.out.println("1- Ver os dados do estádio");
			System.out.println("2- Gastos energéticos");
			System.out.println("3- Solicitar o orçamento");
			System.out.println("4- Criar estádio");
			System.out.println("5- Sair");
			System.out.print("Escolha uma opção: ");
			
			opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				verDadosEstadio(usuarioLogado);
				break;
				
			case 2:
				gastosEnergeticosMenu(scanner,usuarioLogado);
				break;
				
			case 3:
				solicitarOrcamento(scanner,usuarioLogado);
				break;
				
			case 4:
				cadastrarEstadio(scanner,usuarioLogado);
				break;
				
			case 5:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida.");
			}
		}while(opcao!=5);
		
	}

	private void cadastrarEstadio(Scanner scanner, Usuario usuarioLogado) {
		if(estadioDAO.buscarPorUsuarioId(usuarioLogado.getId())!=null) {
			System.out.println("Você já tem um estádio cadastrado. Não é possível criar um novo. Contate o admin.");
			return;
		}
		System.out.println("\n=== Cadastrar estádio ===");
		System.out.println("Digite o tamanho do estádio: ");
		double tamanho =scanner.nextDouble();
		
		System.out.println("Digite a área do campo do estádio: ");
		double areaCampo =scanner.nextDouble();
		
		System.out.println("Digite os gastos mensais(kW) do estádio: ");
		double gastosMensaisKw =scanner.nextDouble();
		
		System.out.println("Digite os gastos mensais(R$) do estádio: ");
		double gastosMensaisReais =scanner.nextDouble();
		
		Estadio estadio = new Estadio(usuarioLogado.getId(),usuarioLogado.getNome(),usuarioLogado.getSenha(),usuarioLogado.getEmail(),usuarioLogado.getNumero(), tamanho, areaCampo, gastosMensaisKw,gastosMensaisReais);
		boolean criado=estadioDAO.criarEstadioParaUsuario(estadio, usuarioLogado);
		if (criado) {
			System.out.println("Cadastro realizado com sucesso!");
		} else {
			System.out.println("Falha ao cadastrar estádio. Tente novamente.");
		}
		
	}

	private void solicitarOrcamento(Scanner scanner, Usuario usuarioLogado) {
		Estadio estadio=estadioDAO.buscarIdEstadioPorEmail(usuarioLogado.getEmail());
		if(estadio==null) {
			System.out.println("Existem inconsistências no seu cadastro.");
			return;
		}
		System.out.println("\n=== Solicitar orçamento ===");
		System.out.print("Defina o teto de gasto do orçamento em R$: ");
		double tetoGasto=scanner.nextDouble();
		
		Orcamento orcamento= new Orcamento(0,estadio.getId(),estadio.getTamanho(),estadio.getAreaCampo(),estadio.getGastosMensalKw(),tetoGasto,false);
		boolean criado=orcamentoDAO.criarOrcamento(orcamento);
		if (criado) {
			System.out.println("Orçamento criado com sucesso!");
		}else {
			System.out.println("Falha ao criar. Tente novamente.");
		}
	}

	private void gastosEnergeticosMenu(Scanner scanner, Usuario usuarioLogado) {
			Estadio estadio = estadioDAO.buscarPorUsuarioId(usuarioLogado.getId());
			if(estadio==null) {
				System.out.println("Existem inconsistências no seu cadastro.");
				return;
			}
			
			System.out.println("\n=== Gastos energéticos do estádio ===");
			System.out.println("1- Visualizar gastos atuais");
			System.out.println("2- Atualizar gastos");
			System.out.print("Escolha uma opção:");
			int escolha = scanner.nextInt();
			if(escolha==1) {
				System.out.println("Gastos mensais(kW): " + estadio.getGastosMensalKw());
				System.out.println("Gastos mensais(R$): " + estadio.getGastosMensalReais());
			}else if(escolha==2) {
				System.out.print("Digite um novo gasto mensal(kW): ");
				double novoGastoKw=scanner.nextDouble();
				System.out.print("Digite um novo gasto mensal(R$): ");
				double novoGastoReais=scanner.nextDouble();
				
				estadio.setGastosMensalKw(novoGastoKw>0?novoGastoKw:estadio.getGastosMensalKw());
				estadio.setGastosMensalReais(novoGastoReais>0?novoGastoReais:estadio.getGastosMensalReais());
				
				if(estadioDAO.atualizarEstadio(estadio)) {
					System.out.println("Gastos atualizados com sucesso!");
				}else {
					System.out.println("Erro ao atualizar os gastos. Tente novamente!");
				}
			}else {
				System.out.println("Opção inválida.");
			}
	}

	private void verDadosEstadio(Usuario usuarioLogado) {
		System.out.println("\n=== Dados do Estádio ===");
		Estadio estadio=estadioDAO.buscarPorUsuarioId(usuarioLogado.getId());
		if (estadio!=null) {
			estadio=estadioDAO.buscarIdEstadioPorEmail(usuarioLogado.getEmail());
			System.out.println("id: "+ estadio.getId());
			System.out.println("Tamanho do estádio: " + estadio.getTamanho());
			System.out.println("Área do campo: " + estadio.getAreaCampo());
			System.out.println("Gastos mensais(kW): " + estadio.getGastosMensalKw());
			System.out.println("Gastos mensais(R$): " + estadio.getGastosMensalReais());
		}else {
			System.out.println("Erro ao localizar dados.Cadastre um estádio ou contate o administrador.");
		}
	}

	private void adminMenu(Scanner scanner, Usuario usuarioLogado) {
		int opcao;
		do {
			System.out.println("\n=== Menu do Administrador ===");
			System.out.println("1- Checar os orçamento");
			System.out.println("2- Validar orçamentos");
			System.out.println("3- Checar relatórios");
			System.out.println("4- Remover estádios");
			System.out.println("5- Gerenciar propostas B2B");
			System.out.println("6- Gerenciar transações B2B");
			System.out.println("7- Sair");
			System.out.print("Escolha uma opção: ");
			opcao=scanner.nextInt();
			
			switch(opcao) {
			
			case 1:
				checarSolicitacoesOrcamento();
				break;
				
			case 2:
				validarOrcamento(scanner);
				break;
				
			case 3:
				checarRelatorios();
				break;
				
			case 4:
				removerEstadios(scanner);
				break;
				
			case 5:
				gerenciarPropostas(scanner);
				break;
				
			case 6:
				gerenciarTransacoes(scanner,usuarioLogado);
				break;
				
			case 7:
				System.out.println("Saindo...");
				break;
				
			default:
				System.out.println("Opção inválida.");
			}
		} while (opcao!=7);
		
	}

	private void gerenciarTransacoes(Scanner scanner, Usuario usuario) {
		int opcao;
		do {
			System.out.println("\n=== Gerenciar transações B2B ===");
			System.out.println("1- Criar transação ");
			System.out.println("2- Listar transações");
			System.out.println("3- Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			
			switch(opcao) {
			
			case 1:
				criarTransacao(scanner, usuario);
				break;
			
			case 2:
				listarTransacao();
				break;
				
			case 3:
				System.out.println("Voltando...");
				break;
				
			default:
				System.out.println("Opção inválida.");
			}
		}while(opcao!=3);
		
	}

	private void listarTransacao() {
		System.out.println("\n=== Listagem de Transações ===");
		try {
			List<TransacaoB2B> transacoes= transacaoB2BDAO.listarTodos();
			transacoes.forEach(System.out::println);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void criarTransacao(Scanner scanner,Usuario usuario) {
		System.out.println("\n=== Criar transação B2B ===");
		System.out.print("Digite o id da proposta: ");
		int idProposta = scanner.nextInt();
		
		System.out.print("Digite o valor da transação: ");
		double valor = scanner.nextDouble();
		
		System.out.print("Digite a descrição da transação: ");
		String descricao = scanner.nextLine();
		
		TransacaoB2B transacao= new TransacaoB2B(0,idProposta,usuario.getId(),valor,descricao);
		boolean criado=transacaoB2BDAO.criarTransacaoB2B(transacao);
		
		if(criado) {
			System.out.println("Transação criado com sucesso.");
		}else {
			System.out.println("Erro ao criar transação.");
		}
	}

	private void gerenciarPropostas(Scanner scanner) {
		int opcao;
		do {
			System.out.println("\n=== Gerenciar propostas B2B ===");
			System.out.println("1- Criar proposta ");
			System.out.println("2- Listar propostas");
			System.out.println("3- Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			
			switch(opcao) {
			
			case 1:
				criarProposta(scanner);
				break;
			
			case 2:
				listarProposta();
				break;
				
			case 3:
				System.out.println("Voltando...");
				break;
				
			default:
				System.out.println("Opção inválida.");
			}
		}while(opcao!=3);
		
		
	}

	private void listarProposta() {
		System.out.println("\n=== Listagem de Proposta ===");
		try {
			List<PropostaB2B> proposta= propostaB2BDAO.listarTodos();
			proposta.forEach(System.out::println);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private void criarProposta(Scanner scanner) {
		System.out.println("\n=== Criar proposta B2B ===");
		System.out.print("Digite o id do orçamento: ");
		int idOrcamento = scanner.nextInt();
		
		System.out.print("Digite o id do tipo de chao: ");
		int idTipoChao = scanner.nextInt();
		
		System.out.print("Digite a descrição da proposta: ");
		String descricao = scanner.nextLine();
		
		scanner.nextLine();
		System.out.print("Digite o valor da proposta: ");
		Double valor = scanner.nextDouble();
		
		System.out.print("Digite a redução em kW: ");
		Double reducao = scanner.nextDouble();
		
		PropostaB2B proposta= new PropostaB2B(0,idOrcamento,idTipoChao,valor, descricao,reducao);
		boolean criado=propostaB2BDAO.criarPropostaB2B(proposta);
		
		if(criado) {
			System.out.println("Proposta criada com sucesso.");
		}else {
			System.out.println("Erro ao criar proposta.");
		}
		
	}

	private void removerEstadios(Scanner scanner) {
		System.out.println("\n=== Remover estádios ===");
		List <Estadio> estadio= estadioDAO.listarTodos();
		if(estadio.isEmpty()){
			System.out.println("Nenhum estádio cadastrado.");
			return;
		}
		estadio.forEach(System.out::println);
		System.out.print("Digite o id do estádio a ser removido: ");
		int idEstadio=scanner.nextInt();
		boolean removido= estadioDAO.removerEstadio(idEstadio);
		if(removido) {
			System.out.println("Estádio removido com sucesso!");
		}else {
			System.out.println("Erro ao remover o estádio.");
		}
		}

	private void checarRelatorios() {
		System.out.println("\n=== Checagem de Relatórios ===");
		try {
			List<Relatorio> relatorio= relatorioDAO.listarTodos();
			if(relatorio.isEmpty()) {
				System.out.println("Não há relatórios cadastrado no sistema.");
			}
			relatorio.forEach(System.out::println);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private void validarOrcamento(Scanner scanner) {
		System.out.println("\n=== Validar orçamentos ===");
		try {
			List<Orcamento> orcamento= orcamentoDAO.listarTodosPendentes();
			if(orcamento.isEmpty()) {
				System.out.println("Não há orçamentos pendentes no sistema.");
			}
			orcamento.forEach(System.out::println);
			System.out.println("Digite o id do orçamento a ser validado: ");
			int idOrcamento=scanner.nextInt();
			System.out.println("Deseja aprovar orçamento? 1-Sim / 2-Não");
			int aprovado=scanner.nextInt();
			Orcamento orcamentoEncontrado= orcamentoDAO.buscarOrcamentoPorId(idOrcamento);
			if(orcamentoEncontrado!=null) {
				orcamentoEncontrado.setAprovado(aprovado==1);
				boolean atualizado=orcamentoDAO.atualizarOrcamento(orcamentoEncontrado);
				if(atualizado) {
					System.out.println("Atualizado com sucesso.");
				}else {
					System.out.println("Falha ao atualizar orçamento. Tente novamente.");
				}
			}else {
				System.out.println("Orçamento não encontrado. Tentar novamente.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private void checarSolicitacoesOrcamento() {
		System.out.println("\n=== Checando todos os orçamentos ===");
		List<Orcamento> orcamento= orcamentoDAO.listarTodos();
		if(orcamento.isEmpty()) {
			System.out.println("Não há orçamentos no sistema.");
		}
		orcamento.forEach(System.out::println);
		
	}

	private boolean isAdmin(Usuario usuario) {
		return usuarioDAO.isUsuarioAdmin(usuario.getId());
		
	}
}
