insert into reasons(name, description, event_date) values('Taller HTML', 'dictada por Sergio Manrique', '2022-04-08 10:30:00');
insert into reasons(name, description, event_date) values('Taller de CSS', 'dictada por Giovannie García', '2022-04-10 11:00:00');

insert into users(document_type, email, document_number, full_name, reason_id, created) values('Cédula de ciudadanía', 'daniel@gmail.com', '1032658923', 'Daniel Correa', 4, '2021-04-08 10:30:00');
insert into users(document_type, email, document_number, full_name, reason_id, created) values('Cédula de ciudadanía', 'sergio@gmail.com', '1035654987', 'Sergio Manrique', 14, '2021-05-13 10:30:00');

insert into records(created, user_id) values('2022-04-10 11:00:00', 4);
insert into records(created, user_id) values('2022-04-10 11:30:00', 14);