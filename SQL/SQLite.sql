drop TABLE if EXISTS endereco;

CREATE TABLE endereco(
id_endereco  PRIMARY KEY NOT NULL,
numero integer NOT NULL,
rua VARCHAR (30) NOT NULL,
bairro VARCHAR  (30),
cidade VARCHAR (30) NOT NULL,
uf VARCHAR(2) NOT NULL); 

drop TABLE if EXISTS pessoa;
CREATE TABLE pessoa(
id_pessoas integer PRIMARY KEY NOT NULL,
nome_pessoa VARCHAR (100) NOT NULL,
cpf VARCHAR(14) NOT NULL,
id_endereco INT NOT NULL,
FOREIGN KEY (id_endereco) REFERENCES endereco(id_endereco));

drop TABLE if EXISTS franqueadoras;
CREATE TABLE franqueadoras(
id_franqueadora integer PRIMARY KEY  NOT NULL,
nome VARCHAR (30) NOT NULL,
qtd_franquia integer NOT NULL ,
id_endereco integer NOT NULL,
FOREIGN KEY(id_endereco) REFERENCES endereco(id_endereco));

drop TABLE if EXISTS cinema;
CREATE TABLE cinema (
id_cinema integer PRIMARY KEY NOT NULL,
nome_cinema VARCHAR (30) NOT NULL ,
id_franqueadora INT NOT NULL ,
id_endereco INT NOT NULL ,
FOREIGN KEY(id_endereco) REFERENCES endereco(id_endereco),
FOREIGN KEY(id_franqueadora) REFERENCES franqueadoras(id_franqueadora));

drop TABLE if EXISTS filme;
CREATE TABLE filme (
id_filme INT PRIMARY KEY NOT NULL, 
nome_filme VARCHAR (30) NOT NULL, 
preco NUMERIC (5,2) NOT NULL);

drop TABLE if EXISTS secao;
CREATE TABLE secao(
id_secao INT PRIMARY KEY NOT NULL ,
horario TIMESTAMP NOT NULL ,
id_cinema INT NOT NULL,
id_filme INT NOT NULL ,
qtd_assentos INT NOT NULL,
FOREIGN KEY(id_cinema) REFERENCES cinema(id_cinema),
FOREIGN KEY(id_filme) REFERENCES filme(id_filme));

drop TABLE if EXISTS cliente;
CREATE TABLE cliente (
id_cliente INT PRIMARY KEY NOT NULL , 
id_pessoa INT NOT NULL , 
FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa));

drop TABLE if EXISTS vendedor;
CREATE TABLE vendedor (
id_vendedor INT PRIMARY KEY NOT NULL ,
id_pessoa INT NOT NULL ,
qtd_vendas INT NOT NULL,
FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa));

drop TABLE if EXISTS venda;
CREATE TABLE venda(
id_secao INT NOT NULL , 
id_vendedor INT NOT NULL ,
id_cliente INT NOT NULL, 
PRIMARY KEY (id_secao,id_vendedor,id_cliente),
CONSTRAINT  fk_secao FOREIGN KEY (id_secao) REFERENCEs secao(id_secao),
CONSTRAINT  fk_vendedor FOREIGN KEY (id_vendedor) REFERENCEs vendedor (id_vendedor),
CONSTRAINT  fk_cliente FOREIGN KEY (id_cliente) REFERENCEs cliente (id_cliente));

drop TABLE if EXISTS metas;
CREATE TABLE metas(
id_meta INT PRIMARY KEY NOT NULL ,
descricao VARCHAR (100) NOT NULL,
remuneracao NUMERIC(3,2) NOT NULL );

drop TABLE if EXISTS meta_vendedor;
CREATE TABLE meta_vendedor(
id_meta INT NOT NULL, 
id_vendedor INT NOT NULL ,
meta_batida BOOLEAN NOT NULL,
PRIMARY KEY (id_meta,id_vendedor),
CONSTRAINT fk_meta FOREIGN KEY (id_meta) REFERENCES metas(id_meta),
CONSTRAINT fk_vendedor FOREIGN KEY(id_vendedor) REFERENCES vendedor (id_vendedor));

drop TABLE if EXISTS contato;
CREATE TABLE contato (
id_contato INT PRIMARY KEY NOT NULL , 
tipo_contato VARCHAR (15) NOT NULL, 
contato VARCHAR (40) NOT NULL);

drop TABLE if EXISTS pessoa_contato;
CREATE TABLE pessoa_contato(
id_pessoa INT NOT NULL , 
id_contato INT NOT NULL, 
PRIMARY KEY (id_pessoa,id_contato),
CONSTRAINT fk_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
CONSTRAINT fk_contato FOREIGN KEY (id_contato) REFERENCES contato(id_contato));




