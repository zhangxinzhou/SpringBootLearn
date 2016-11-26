insert into users(name,sex,age,occupation) values('zx','男',12,'厨师');
insert into users(name,sex,age,occupation) values('zx2','女',10,'学生');
insert into users(name,sex,age,occupation) values('zx3','女',100,'老师');
insert into users(name,sex,age,occupation) values('zx5','女',30,'职员');
insert into users(name,sex,age,occupation) values('zx7','女',10,'工头');
insert into users(name,sex,age,occupation) values('zx8','女',80,'搬砖工');
insert into users(name,sex,age,occupation) values('z9x','男',10,'程序员');
insert into users(name,sex,age,occupation) values('zx71','女',10,'工头');
insert into users(name,sex,age,occupation) values('zx72','女',10,'工头');
insert into users(name,sex,age,occupation) values('z9x3','男',10,'程序员');
insert into users(name,sex,age,occupation) values('z9x5','男',10,'程序员');
insert into users(name,sex,age,occupation) values('zx1','男',12,'厨师');
insert into users(name,sex,age,occupation) values('zx21','女',10,'学生');
insert into users(name,sex,age,occupation) values('zx31','女',100,'老师');
insert into users(name,sex,age,occupation) values('zx51','女',30,'职员');
insert into users(name,sex,age,occupation) values('zx71','女',10,'工头');
insert into users(name,sex,age,occupation) values('zx81','女',80,'搬砖工');
insert into users(name,sex,age,occupation) values('z9x1','男',10,'程序员');
insert into users(name,sex,age,occupation) values('zx711','女',10,'工头');
insert into users(name,sex,age,occupation) values('zx721','女',10,'工头');
insert into users(name,sex,age,occupation) values('z9x31','男',10,'程序员');
insert into users(name,sex,age,occupation) values('z9x51','男',10,'程序员');

--设置了spring.jpa.properties.hibernate.hbm2ddl.auto=create-drop
--所以每次项目重启会删除users表并重建,项目关闭时会删除users表,不用担心多次增加数据的问题