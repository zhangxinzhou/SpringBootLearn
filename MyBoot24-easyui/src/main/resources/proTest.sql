--使用的是微软的sql server数据库
--无输入,无返回值
create procedure pro_test
as
begin
insert into student(birthday,name,sex)values(GETDATE(),'pro_test','女')
end
--output
create procedure pro_output
@a int,
@b int,
@c varchar(50),
@sum int output,
@result varchar(50) output
as
begin
set @sum=@a+@b
set @result='hello,'+@c
end
--return
create procedure pro_return
@a int,
@b int
as
begin
return @a+@b
end
--select * from table
create procedure pro_selectfromtable
@sex varchar(50)
as
begin
select * from student where sex=@sex
end
--select pro_selectsingle
create procedure pro_selectsingle
@name varchar(50)
as
begin
set @name='hello,'+@name
select @name 'result'
end
--动态分页查询表
create procedure pro_tabledynamic
@tName varchar(50),
@pageNum int,
@pageSize int
as
begin
declare 
@sql1 nvarchar(200),
@sql2 nvarchar(200),
@order varchar(50),
@startRecord int=(@pageNum-1)*@pageNum+1,
@endRecord int=@pageNum*@pageSize
select top 1 @order=name from syscolumns where id=object_id(@tName)
set @sql1='select count(*) totals from '+@tName
set @sql2='select * from(select ROW_NUMBER() over (order by @order) as RowNum,* from '+@tName+
          ')as temp where RowNum between @startRecord and @endRecord'
exec sp_executesql @sql1
exec sp_executesql @sql2,N'@order varchar(50),@startRecord int,@endRecord int',@order,@startRecord,@endRecord
end 