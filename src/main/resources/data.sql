
insert into hotel (nome, descricao, cep, numero, complemento, bairro, cidade) 
    values ('HotelA', 'muito bom', '22.574-040', '245', 'casa', 'Sulivan', 'Arquival');

insert into hotel (nome, descricao, cep, numero, complemento, bairro, cidade) 
    values ('HotelB', 'Exelente', '22.555-333', '271', 'Bar', 'Niton', 'igrejinha');


insert into room (code, diaria, descricao, disponivel, hotel_id) 
    values (1, 152.00, 'Bem arejado de frente ao mar', true, 1);

insert into room (code, diaria, descricao, disponivel, hotel_id) 
    values (2, 332.00, 'Bem arejado de frente ao mar', true, 1); 

insert into room (code, diaria, descricao, disponivel, hotel_id) 
    values (3, 552.00, 'suite presidencial', true, 2); 

insert into room (code, diaria, descricao, disponivel, hotel_id) 
    values (3, 552.00, 'suite presidencial', false, 1); 

insert into room (code, diaria, descricao, disponivel, hotel_id) 
    values (3, 552.00, 'suite presidencial', false, 2);    

insert into client (nome, email, celular, cpf, rg)
    values ('Alisson', 'alisson@gf.com', '(00)000000000', '000.000.000-00', '00.000.000-0');

insert into client (nome, email, celular, cpf, rg)
    values ('Melissa', 'melissa@gf.com', '(00)000000000', '000.000.000-00', '00.000.000-0');

insert into client (nome, email, celular, cpf, rg)
    values ('Mongo', 'mong@gf.com', '(00)000000000', '000.000.000-00', '00.000.000-0');

insert into rent_room (check_in, check_out, observacoes, valor, cliente_id, quarto_id)
    values ('2019-11-02 21:10:00', '2019-11-04 20:00:00', 'Quarto para não fumantes', 111.00, 1, 1 );