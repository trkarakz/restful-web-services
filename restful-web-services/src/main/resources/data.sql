-- User table
insert into user_details (id, birth_date, name) values (1001, current_date(), 'Test Name1');
insert into user_details (id, birth_date, name) values (1002, current_date(), 'Test Name2');
insert into user_details (id, birth_date, name) values (1003, current_date(), 'Test Name3');

-- Post table
insert into post (id, description, user_id) values (2001, 'I am willing to learn mobile development', 1001);
insert into post (id, description, user_id) values (2002, 'I am willing to learn kotlin', 1001);
insert into post (id, description, user_id) values (2003, 'I am willing to learn microservices', 1002);
insert into post (id, description, user_id) values (2004, 'I am willing to learn spring cloud', 1002);
