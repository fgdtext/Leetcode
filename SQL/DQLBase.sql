#去重：DISTINCT
SELECT DISTINCT department_id FROM employees;

# + 符号  只能左数字的求值。 一方如果是字符则尝试转为数字，否则转为0.

SELECT last_name+first_name AS 姓名 FROM employees; 

# concat函数  字符串拼接
SELECT CONCAT(last_name,first_name) AS 姓名 FROM employees;

# ifnull函数:判断某字段或表达式是否为null，如果为null 返回指定的值，否则返回原本的值
SELECT IFNULL(commission_pct,123) FROM employees;


#where 筛选条件 *******************************************************

#   条件运算符: > < = != <> >= <= <=>安全等于
#   逻辑运算符:&& || !   and or not  
#   && 和 and:两个条件都为true，结果为true，反之为false
#	|| 和 or:只要有一个条件为true，结果为true，反之为false
#	! 或 not:如果连接的条件本身为false，结果为true，反之为false	
#  模糊查询
#	like:一般搭配通配符使用，可以判断字符型或数值型
#	通配符：%任意多个字符，_任意单个字符
#	like、 between and    、 in  、   is null ， is not null


#  like

#案例1:查询员工名中包含字符a的员工信息
SELECT * FROM employees WHERE last_name LIKE '%a%';

# between and  

# 查询员工编号在100到120之间的员工信息
SELECT * FROM employees WHERE employee_id BETWEEN 100 AND 120;

# in 

# 查询员工的工种编号是IT_PROG、AD_VP、AD_PRES中的一个员工名和工种编号
SELECT last_name,job_id FROM employees WHERE job_id IN('IT_PROG','AD_PRES','AD_VP');
SELECT last_name,job_id FROM employees WHERE employee_id IN(100,200,'AD_VP');
# 括号内类型可以不同。


#排序 *****************************************************************************

# select 查询列表 from 表 【where 筛选条件】 order by
#   1.asc代表的是升序，desc代表降序，不写默认为升序
#   2.order by子句中可以支持单个字段、多个字段、表达式、函数、别名
#   3.order by子句一般是放在查询语句的最后面,limit子句除外

#案例3:按年薪的高低显示员工的信息和年薪【按表达式排序】
SELECT *,salary*12*(1+IFNULL(commission_pct,0)) 年薪 FROM employees 
ORDER BY salary*12*(1+IFNULL(commission_pct,0)) DESC; 

# LENGTH(str) : 字符串长度
#案例5:按姓名的长度显示员工的姓名和工资【按函数排序】
SELECT LENGTH(last_name) 字节长度,last_name,salary
FROM employees
ORDER BY LENGTH(last_name) DESC;


#常见函数  ************************************************************************

#1.length 获取参数值的字节值
SELECT LENGTH('subei');

#2.concat 拼接字符串
SELECT CONCAT(last_name,'_',first_name) 姓名 FROM employees;

#3.upper:变大写、lower：变小写
SELECT UPPER('ton');
SELECT LOWER('ton');

#4. substr(str,起始索引，长度)
#截取从指定所有处后面的所以字符
SELECT SUBSTR('吴刚伐桂在天上',4) out_put;    # 桂在天上
#截取从指定索引处指定字符长度的字符
SELECT SUBSTR('吴刚伐桂在天上',1,2) out_put;  #吴刚

#5.instr:获取子串第一次出现的索引,找不到返回0
SELECT INSTR('MySQL技术进阶','技术') AS out_put;

#6.trim:去前后空格  trim(str)  or  trim('char' FROM 'str')
# 默认是去除空格， char是指去除 前后的 字符 char
SELECT LENGTH(TRIM('	霍山	')) AS out_put;
SELECT TRIM('+' FROM '++++李刚+++刘邦+++') AS out_put;

#9.replace:替换
SELECT REPLACE('莉莉伊万斯的青梅竹马是詹姆','詹姆','斯内普') AS out_put;


# 数学函数  ****************************************************************

#1.round:四舍五入
SELECT ROUND(1.45);
SELECT ROUND(1.567,2);  #保留两位

#2.ceil:向上取整,返回>=该参数的最小整数
SELECT CEIL(1.005);
SELECT CEIL(-1.002);

#3.floor:向下取整,返回<=该参数的最大整数
SELECT FLOOR(-9.99);

#4.truncate:截断
SELECT TRUNCATE(1.65,1); #小数后一位。

#5.mod:取余
SELECT MOD(10,3);

#6.rand:获取随机数，返回0-1之间的小数
SELECT RAND();



#日期函数*******************************************************************

#1.now:返回当前系统时间+日期
SELECT NOW();   # 2021-03-20 20:18:03

#2.year:返回年
SELECT YEAR(NOW());
SELECT YEAR(hiredate) 年 FROM employees;  #对hiredate提取年份

#3.month:返回月
#MONTHNAME:以英文形式返回月
SELECT MONTH(NOW());
SELECT MONTHNAME(NOW());

#4.day:返回日
#DATEDIFF:返回两个日期相差的天数
SELECT DAY(NOW());
SELECT DATEDIFF('2020/06/30','2020/06/21');

#5.str_to_date:将字符通过指定格式转换成日期
SELECT STR_TO_DATE('2020-5-13','%Y-%c-%d') AS out_put;

#6.date_format:将日期转换成字符
SELECT DATE_FORMAT('2020/6/6','%Y年%m月%d日') AS out_put;
SELECT DATE_FORMAT(NOW(),'%Y年%m月%d日') AS out_put;

#7.curdate:返回当前日期
SELECT CURDATE();

#8.curtime:返回当前时间
SELECT CURTIME();

# MD5加密***************************************************************
#md5('字符'):返回该字符的md5加密形式
SELECT MD5('a');



#分组函数

# sum 求和、avg 平均值、max 最大值、min最小值count 计算个数

#1.sum和avg一般用于处理数值型  max、min、count可以处理任何数据类型
#2.以上分组函数都忽略null
#3.都可以搭配distinct使用，实现去重的统计  select sum(distinct 字段) from 表;
#4. count(字段)：统计该字段非空值的个数  count(*):统计结果集的行数
#
#5.和分组函数一同查询的字段，要求是group by后出现的字段
#


