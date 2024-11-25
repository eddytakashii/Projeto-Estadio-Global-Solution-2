use  projetotest;

-- Tabela de usuario (Pai de estádio e admin)
CREATE TABLE USUARIO(
	id int auto_increment primary key,
    nome varchar(100) not null,
    email varchar(100) unique not null,
    senha varchar(100) not null,
    numero varchar(15) not null,
    is_admin tinyint(1) default 0
);

-- Tabela de admin (Relação com usuario)
CREATE TABLE ADMIN(
	id_admin int auto_increment primary key,
    id_usuario int not null,
    constraint fk_usuario_admin foreign key (id_usuario) references usuario(id)
);

-- Tabela de estadio (Relaçao com usuario)
CREATE TABLE ESTADIO(
	id_estadio int auto_increment primary key,
    id_usuario int not null,
    tamanho decimal(10,2),
    area_campo decimal(10,2),
    gastos_mensais_kw decimal(10,2),
    gastos_mensais_reais decimal (10,2),
    constraint fk_usuario_estadio foreign key (id_usuario) references usuario(id)
);

CREATE TABLE RELATORIO(
	id_relatorio int auto_increment primary key,
    id_estadio int not null,
    descricao text,
    conclusao text,
    constraint fk_relatorio_estadio foreign key (id_estadio) references estadio(id_estadio) ON DELETE CASCADE
);

CREATE TABLE TIPO_CHAO(
	id_tipo_chao int auto_increment primary key,
    nome_chao varchar(50) not null,
    geracao_estimativa_kw decimal(10,2) not null,
    custo_por_metro decimal (6,2)
);

CREATE TABLE ORCAMENTO(
	id_orcamento int auto_increment primary key,
    id_estadio int not null,
    tamanho decimal(10,2),
    area_campo decimal(10,2),
    gasto_mensal_kw decimal (10,2),
    teto_gasto decimal (10,2),
    aprovado tinyint(1) default 0,
    constraint fk_estadio_orcamento foreign key(id_estadio) references estadio(id_estadio) ON DELETE CASCADE
);

CREATE TABLE PROPOSTA_B2B(
	id_proposta int auto_increment primary key,
    id_orcamento int not null,
    id_tipo_chao int not null,
    valor decimal (10,2) not null,
    descricao text,
    reducao_kw decimal(10,2),
    constraint fk_orcamento_proposta foreign key (id_orcamento) references orcamento(id_orcamento) ON DELETE CASCADE,
    constraint fk_tipo_chao_proposta foreign key (id_tipo_chao) references tipo_chao(id_tipo_chao)
);

CREATE TABLE TRANSACAO_B2B(
	id_transacao int auto_increment primary key,
    id_proposta int not null,
    id_admin int not null,
    valor decimal(10,2) not null,
    descricao text,
    constraint fk_proposta_transacao foreign key (id_proposta) references proposta_b2b(id_proposta) ON DELETE CASCADE ,
    constraint fk_admin_transacao foreign key (id_admin) references admin(id_admin) 
);

INSERT INTO USUARIO (nome, email, senha, numero, is_admin) VALUES ('João Silva', 'joao.silva@hotmail.com', 'senha123', '11912345678', 1);
INSERT INTO USUARIO (nome, email, senha, numero) VALUES ('Corinthians FC', 'Corinthians@gmail.com', 'senha456', '21987654321'),
('Inter', 'inter@gmail.com', 'senha789', '31956473829');


INSERT INTO ADMIN (id_usuario) VALUES (1); -- único admin é joão

INSERT INTO ESTADIO (id_usuario, tamanho, area_campo, gastos_mensais_kw, gastos_mensais_reais) VALUES 
(2, 5000.00, 3000.00, 1500.50, 20000.00), 
(3, 7500.00, 4500.00, 2000.00, 30000.00);


INSERT INTO RELATORIO (id_estadio, descricao, conclusao) VALUES 
(1, 'Análise do consumo mensal de energia.', 'Necessidade de reduzir consumo para otimização.'), 
(2, 'Estudo de viabilidade para implementação de energia solar.', 'Investimento inicial viável para médio prazo.');


INSERT INTO TIPO_CHAO (nome_chao, geracao_estimativa_kw, custo_por_metro) VALUES 
('Gramado Inteligente 1.0', 50.00, 20.00), 
('Gramado Inteligente 2.0', 70.00, 30.00), 
('Gramado Inteligente 3.0', 500.00, 200.00);

INSERT INTO ORCAMENTO (id_estadio, tamanho, area_campo, gasto_mensal_kw, teto_gasto, aprovado) VALUES 
(1, 5000.00, 3000.00, 1500.50, 25000.00, 0), 
(2, 7500.00, 4500.00, 2000.00, 40000.00, 1);


INSERT INTO PROPOSTA_B2B (id_orcamento, id_tipo_chao, valor, descricao, reducao_kw) VALUES 
(1, 1, 15000.00, 'Substituição por grama natural de alta eficiência.', 200.00), 
(2, 3, 35000.00, 'Instalação de painéis solares na área disponível.', 1500.00);

INSERT INTO TRANSACAO_B2B (id_proposta, id_admin, valor, descricao) VALUES 
(1, 1, 15000.00, 'Compra de grama natural para Estádio A.'), 
(2, 1, 35000.00, 'Contrato de instalação de painéis solares para Estádio B.');
