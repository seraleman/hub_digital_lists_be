insert into reasons(name, description, event_date) values('Taller HTML', 'dictada por Sergio Manrique', '2022-04-08 10:30:00');
insert into reasons(name, description, event_date) values('Taller de CSS', 'dictada por Giovannie García', '2022-04-10 11:00:00');

insert into users(document_type, email, document_number, full_name, reason_id, created) values('Cédula de ciudadanía', 'mariano@gmail.com', 1032658923, 'Mariano Hernández', 4, '2022-04-07 16:25:00');
insert into users(document_type, email, document_number, full_name, reason_id, created) values('Cédula de ciudadanía', 'tulio@gmail.com', 1035654987, 'Tulio Ospina', 14, '2022-04-09 23:09:00');

insert into records(created, user_id) values('2022-04-08 10:25:00', 4);
insert into records(created, user_id) values('2022-04-10 11:15:00', 14);