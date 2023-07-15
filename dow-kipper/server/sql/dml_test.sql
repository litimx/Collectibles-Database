use collections_test;

delimiter //
create procedure set_known_good_state()
begin
    
	set foreign_key_checks = 0;
    delete from item;
    alter table item auto_increment = 1;
    delete from collection;
    alter table collection auto_increment = 1;
	delete from app_role;
    alter table app_role auto_increment = 1;
    delete from app_user;
    alter table app_user auto_increment = 1;
    delete from app_user_role;
  
	insert into item (`name`,`value`,grade)
	values 
	('test', 20.12,'UNGRADED'),
	('testTwo',44.10,'NINEFIVE'),
	('testThree',5.15,'SEVEN');
	
	insert into collection (app_user_id, `name`, `value`)
	values 
	(1, 'test', 200),
	(1,'testTwo', 303.56),
	(2, 'testThree', 2123.78);
	
	insert into app_role (`name`) values
	('USER'),
	('ADMIN');

	-- passwords are set to "P@ssw0rd!"
	insert into app_user (username, password_hash, enabled)
	values
	('john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
	('sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1);

	insert into app_user_role
	values
	(1, 2),
	(2, 1);
	
	set foreign_key_checks = 1;
	
end //
delimiter ;

