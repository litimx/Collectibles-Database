

-- -- passwords are set to "P@ssw0rd!"
-- 	insert into app_user (username, password_hash, enabled)
-- 		values
-- 		('john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
-- 		('sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1);

-- insert into app_user_role
-- 		values
-- 		(1, 2),
-- 		(2, 1);

use collections;
select * from app_user;
select * from app_role;
select * from app_user_role;
select * from collection;
select * from item;
select * from collection_item;