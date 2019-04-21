insert into SYS_USER(id,username,password) value (1, 'wyf', 'wyf123');
insert into SYS_USER(id,username,password) value (2, 'chj', 'chj123');

insert into SYS_ROLE(id,role_name) value (1, 'ROLE_ADMIN');
insert into SYS_ROLE(id,role_name) value (2, 'ROLE_USER');

insert into SYS_USER_ROLES(SYS_USER_ID,ROLES_ID) value (1, 1);
insert into SYS_USER_ROLES(SYS_USER_ID,ROLES_ID) value (2, 2);