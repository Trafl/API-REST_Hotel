
insert into hotel (nome, descricao, cep, numero, complemento, bairro, cidade) 
    values ('HotelA', 'muito bom', '22.574-040', '245', 'casa', 'Sulivan', 'Arquival');

insert into hotel (nome, descricao, cep, numero, complemento, bairro, cidade) 
    values ('HotelB', 'Exelente', '22.555-333', '271', 'Bar', 'Niton', 'igrejinha');


insert into room (code, diaria, descricao, status, hotel_id) 
    values (1, 152.00, 'exemplo1', 'RESERVADO',  1);

insert into room (code, diaria, descricao, status, hotel_id) 
    values (2, 332.00, 'exemplo2', 'DISPONIVEL',  1); 

insert into room (code, diaria, descricao, status, hotel_id) 
    values (3, 552.00, 'exemplo3', 'DISPONIVEL',  2); 

insert into room (code, diaria, descricao, status, hotel_id) 
    values (3, 333.00, 'exemplo4', 'DISPONIVEL',  1); 

insert into room (code, diaria, descricao, status, hotel_id) 
    values (5, 552.00, 'exemplo5', 'DISPONIVEL',  2);    

insert into client (nome, email, celular, cpf, rg)
    values ('Alisson', 'alisson@gf.com', '(00)000000000', '000.000.000-00', '00.000.000-0');

insert into client (nome, email, celular, cpf, rg)
    values ('Melissa', 'melissa@gf.com', '(00)000000000', '000.000.000-00', '00.000.000-0');

insert into client (nome, email, celular, cpf, rg)
    values ('Mongo', 'mong@gf.com', '(00)000000000', '000.000.000-00', '00.000.000-0');

insert into rent_room (check_in, check_out,  observacoes, status, valor, cliente_id, quarto_id)
    values ('2023-07-02 11:30:00', '2023-07-04 11:34:00',  'Quarto para não fumantes', 'FECHADO', 111.00, 1, 1 );
