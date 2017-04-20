insert into SYS_USER(id,username,password,enable) values(1,'admin','admin',1);
insert into SYS_USER(id,username,password,enable) values(2,'user','user',1);

insert into SYS_ROLE(id,rolename) values(1,'ROLE_ADMIN');
insert into SYS_ROLE(id,rolename) values(2,'ROLE_USER');

insert into SYS_USER_ROLES(SYS_USER_ID,ROLES_ID) values(1,1);
insert into SYS_USER_ROLES(SYS_USER_ID,ROLES_ID) values(2,2);
--设置了spring.jpa.properties.hibernate.hbm2ddl.auto=create-drop
--所以每次项目重启会删除users表并重建,项目关闭时会删除users表,不用担心多次增加数据的问题

