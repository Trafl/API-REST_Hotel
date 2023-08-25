set foreign_key_checks = 0;

lock tables hotel write, room write, client write, rent write;

truncate table hotel;
truncate table room;
truncate table client;
truncate table rent;

set foreign_key_checks = 1;

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

 insert into rent (check_in, check_out, pagamento, file_name, observacoes, cliente_id, quarto_id, status, valor )
 	values ('2023-07-08 10:00', '2023-07-12 10:00', 'CARTAO', '26c468d2-62f9-401f-a0ea-baab484dd8d6', "Toalhas extras", 1,3, 'RESERVADO', 2208.00);
 	
 unlock tables;