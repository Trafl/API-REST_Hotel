
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