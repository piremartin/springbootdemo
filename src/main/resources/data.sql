insert into SYS_USER(id,username,password) value (1, 'chj', '123');
insert into SYS_USER(id,username,password) value (2, 'wyf', '123');

insert into SYS_ROLE(id,`name`) value (1, 'ROLE_ADMIN');
insert into SYS_ROLE(id,`name`) value (2, 'ROLE_USER');

insert into SYS_USER_ROLES(SYS_USER_ID,ROLES_ID) value (1, 1);
insert into SYS_USER_ROLES(SYS_USER_ID,ROLES_ID) value (2, 2);