CREATE SCHEMA API AUTHORIZATION sa;
/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/

create table API.CLIENTE (
   ID_CLIENTE BIGINT               default 0  not null,
   NOME VARCHAR2(200) not null,
   ENDERECO   VARCHAR2(200),
   EMAIL   VARCHAR2(100),
   TELEFONE   VARCHAR2(100)
);

create table API.USUARIO (
   ID_USUARIO BIGINT               default 0  not null,
   NOME VARCHAR2(200) not null,
   LOGIN VARCHAR2(200) not null,
   SENHA VARCHAR2(200) not null,
   EMAIL   VARCHAR2(100),
   PERMISSOES  VARCHAR2(2000)
);

create sequence API.SQ_ID_CLIENTE minvalue 1 start with 1 increment by 1 nocache;
create sequence API.SQ_ID_USUARIO minvalue 1 start with 1 increment by 1 nocache;

alter table API.CLIENTE
   add constraint PK_CLIENTE primary key (ID_CLIENTE);

alter table API.USUARIO
   add constraint PK_USUARIO primary key (ID_USUARIO);

INSERT INTO API.CLIENTE (ID_CLIENTE, NOME, ENDERECO, EMAIL, TELEFONE) VALUES (API.SQ_ID_CLIENTE.nextval,'José','Rua J, 345','jose14@outlook.com','53 988667788');
INSERT INTO API.CLIENTE (ID_CLIENTE, NOME, ENDERECO, EMAIL, TELEFONE) VALUES (API.SQ_ID_CLIENTE.nextval,'Jão','Rua H, 343','jose14@outlook.com','53 988098432');
INSERT INTO API.CLIENTE (ID_CLIENTE, NOME, ENDERECO, EMAIL, TELEFONE) VALUES (API.SQ_ID_CLIENTE.nextval,'Manolo','Rua M, 342','jose14@outlook.com','53 98098234');
INSERT INTO API.CLIENTE (ID_CLIENTE, NOME, ENDERECO, EMAIL, TELEFONE) VALUES (API.SQ_ID_CLIENTE.nextval,'Wlisses','Rua N, 341','jose14@outlook.com','53 98098234');
INSERT INTO API.CLIENTE (ID_CLIENTE, NOME, ENDERECO, EMAIL, TELEFONE) VALUES (API.SQ_ID_CLIENTE.nextval,'Leonardo','Rua O, 320','jose14@outlook.com','53 980928347');

INSERT INTO API.USUARIO (ID_USUARIO, NOME, LOGIN, SENHA, EMAIL, PERMISSOES) VALUES (API.SQ_ID_USUARIO.nextval, 'root', 'root', '$2y$10$u5/.Ger5pEGXcZV1ByUX4uA9Fp/IbVMajvR2DLH.0UhG2MeKu5ZiG','jose14@outlook.com', 'ESCRITA_CLIENTE,LEITURA_CLIENTE,ESCRITA_USUARIO,LEITURA_USUARIO');
INSERT INTO API.USUARIO (ID_USUARIO, NOME, LOGIN, SENHA, EMAIL, PERMISSOES) VALUES (API.SQ_ID_USUARIO.nextval, 'thiago', 'thiago', '$2y$10$u5/.Ger5pEGXcZV1ByUX4uA9Fp/IbVMajvR2DLH.0UhG2MeKu5ZiG','thiago14@outlook.com', 'LEITURA_USUARIO,LEITURA_CLIENTE');
