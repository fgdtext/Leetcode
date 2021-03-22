#启动 ： 
net start mysql
#停止
net stop mysql

#登录
mysql -h localhost -u root -proot

#退出
exit 

#列出所有数据库
show databases;

#新建一个数据库
CREATE DATABASE  kuiname #新建库名

#选择一个数据库
use kuiname

#查询数据表
show tables

#从指定库找所有表
show tables from kuiname

#查询当前所在数据库
select database();

#新建一个数据表
CREATE table math(
    id int,
    name varchar(20)
);


#查看表的结构
desc math;

#向表中插入记录
#注意：插入varchar或date 型的数据要用单引号引起来
insert into math(id,name) values(1,"ton");

#修改记录
update math set name = "wugang" where id =1;

#删除记录
delete form math where id = 1;

#删除数据表
drop table math





