create table pessoa(
	id bigint not null auto_increment,
	nome varchar(100) not null,
	email varchar(255) not null,
	telefone varchar(20) not null,
	cpf varchar(30) not null,
	idade int not null,
	primary key (id)
);