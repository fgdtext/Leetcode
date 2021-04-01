
# 分组查询********************************************************************************

# select 分组函数,分组后的字段
# from 表
#【where 筛选条件】
# group by 分组的字段
#【having 分组后的筛选】
#【order by 排序列表】
# limit 


#			使用关键字	   筛选的表	           位置
#  分组前筛选	where		原始表		    group by的前面
#  分组后筛选	having		分组后的结果	 group by的后面
#  1.分组函数做条件肯定是放在having子句中****** 比如平均值>20的分组被筛选出来。
#  2.能用分组前筛选的，就优先考虑使用分组前筛选
#  2.group by子句支持单个字段分组，多个字段分组(多个字段之间用逗号隔开没有顺序要求),表达式或函数(使用较少)
#  3.也可以添加排序(排序放在整个分组查询的最后)

#案例1:查询每个工种的最高工资
SELECT MAX(salary),job_id FROM employees 
GROUP BY job_id;
#  每个分组只有一条记录。就是显示最大值，后边跟着分组id.


#案例2:查询有奖金的每个领导手下员工的最高工资
SELECT MAX(salary),manager_id FROM employees
WHERE commission_pct IS NOT NULL
GROUP BY manager_id;

#2.根据1的结果进行筛选，查询哪个部门的员工个数大于2
SELECT COUNT(*),department_id FROM employees
GROUP BY department_id HAVING COUNT(*)>2;  # 每组的条目>2才选出
# HAVING : 针对分组进行筛选， 满足条件的分组 才会被选中。

# 后边可以用前边定义的 别名。
SELECT COUNT(*) c,LENGTH(last_name) len_name 
FROM employees GROUP BY len_name HAVING c>5;



#连接查询##################################################

#  sql92标准案例
# 通过  where 关键字来进行连接，并筛选。
SELECT salary,grade_level
FROM employees e,job_grades g
WHERE salary BETWEEN g.`lowest_sal` AND g.`highest_sal`
AND g.`grade_level`='A';


#  sql99标准案例
#  select 查询列表
#  from 表1 别名 【连接类型】
#  join 表2 别名 
#  on 连接条件
#  【where 筛选条件】
#  【group by 分组】
#  【having 筛选条件】
#  【order by 排序列表】

    # 分类

    #内连接（★）：inner
    #外连接
	    #左外(★):left 【outer】
	    #右外(★)：right 【outer】
	    #全外：full【outer】
    #交叉连接：cross 


# 内连接    INNER JOIN   INNER可以省略  选出交集部分。

#案例2.查询名字中包含e的员工名和工种名（添加筛选）
SELECT last_name,job_title FROM employees e
 JOIN jobs j ON e.job_id=  j.job_id
WHERE e.`last_name` LIKE '%e%';


#案例5.查询员工名、部门名、工种名，并按部门名降序（添加三表连接）

SELECT last_name,department_name,job_title
FROM employees e
INNER JOIN departments d ON e.department_id=d.department_id
INNER JOIN jobs j ON e.job_id = j.job_id
ORDER BY department_name DESC;


# 非等值连接 
SELECT salary,grade_level
FROM employees e
JOIN job_grades g
ON e.salary BETWEEN g.lowest_sal AND g.highest_sal;


#  自连接

#查询员工的名字、上级的名字
# 员工的上级在一行中只显示上级id, 而上级也在员工表。所以做自连接。
SELECT e.last_name,m.last_name
FROM employees e
JOIN employees m
ON e.`manager_id`= m.`employee_id`;

