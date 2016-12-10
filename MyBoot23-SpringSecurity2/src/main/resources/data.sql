insert into SYS_USER(username,password) values('admin','admin')
insert into SYS_USER(username,password) values('user1','user1')

insert into SYS_ROLE(name) values('ROLE_ADMIN');
insert into SYS_ROLE(name) values('ROLE_USER');

insert into SYS_USER_ROLES(SYS_USER_ID,ROLES_ID) values(1,1);
insert into SYS_USER_ROLES(SYS_USER_ID,ROLES_ID) values(2,2);
--设置了spring.jpa.properties.hibernate.hbm2ddl.auto=create-drop
--所以每次项目重启会删除users表并重建,项目关闭时会删除users表,不用担心多次增加数据的问题