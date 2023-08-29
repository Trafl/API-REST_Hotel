create table user (

	id bigint not null auto_increment,
	email varchar(100) not null,
	senha varchar(255) not null,
	roles varchar(50) not null,

	primary key (id)
);