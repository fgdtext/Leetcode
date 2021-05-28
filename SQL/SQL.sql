
SQL12 获取每个部门中当前员工薪水最高的相关信息，给出dept_no, emp_no以及其对应的salary，按照部门编号升序排列
    分析1：from后子查询
    1.	查出每个部门的最高薪资，和部门编号。使用分组查询。Max正好取最高薪资。
    2.	通过关联查询，查出人员信息和薪资。
    3.	两个子表按照部门id和薪资进行关联，则可取出每部门最高薪资的信息。 

select uni.dept_no, uni.emp_no, max_salary.salary
from
(select d.dept_no, s.emp_no, s.salary
    from dept_emp d join salaries s 
    on d.emp_no = s.emp_no 
    and d.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'   
) as uni join /* 部门编号,员工编号,当前薪水 */
(select d.dept_no, max(s.salary) as salary
    from dept_emp d join salaries s 
    on d.emp_no = s.emp_no
    and d.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'   
    group by d.dept_no
) as max_salary  on /* 部门编号,当前最高薪水 */
uni.salary = max_salary.salary
and uni.dept_no = max_salary.dept_no
order by uni.dept_no;
    
        分析2：关联子查询(大圈套小圈，大圈放钩子到小圈)

SELECT d1.dept_no, d1.emp_no, s1.salary
FROM dept_emp as d1
INNER JOIN salaries as s1
ON d1.emp_no=s1.emp_no
AND d1.to_date='9999-01-01'
AND s1.to_date='9999-01-01'
WHERE s1.salary in (SELECT MAX(s2.salary)
FROM dept_emp as d2
INNER JOIN salaries as s2
ON d2.emp_no=s2.emp_no
AND d2.to_date='9999-01-01'
AND s2.to_date='9999-01-01'
AND d2.dept_no = d1.dept_no
)
ORDER BY d1.dept_no;


SQL18 查找薪水排名第二的员工编号，薪水， name等
请你查找薪水排名第二多的员工编号emp_no、薪水salary、last_name以及first_name，不能使用order by完成，
以上例子输出为:（温馨提示:sqlite通过的代码不一定能通过mysql，因为SQL语法规定，使用聚合函数时，
select子句中一般只能存在以下三种元素：常数、聚合函数，group by 指定的列名。如果使用非group by的列名，
sqlite的结果和mysql 可能不一样)    注意：薪水，name在两张表中。聚合函数不能用在where中

分析：
不能使用order by  那么如何选出第二大。我们可以找到最高薪水，然后通过子查询，排除掉最高薪水的行。
保留低于最高薪水的所有行。构成一个独立的子查询。
然后对子查询的结果，选出最高薪水。这个最高，其实就是次高了。
然后查出拥有该薪水的所有人即可。

select s.emp_no, s.salary as salary, e.last_name, e.first_name
from employees e join salaries as s  on e.emp_no = s.emp_no
where s.salary = (select max(salary) from salaries ss 
where  ss.salary < (select max(salary) from salaries))



SQL19 查找所有员工的last_name和first_name以及对应的dept_name，也包括暂时没有分配部门的员工
三张表，员工表，部门表，员工部门关系表
分析
三张表做连续的左连接。员工表和关系表左连接，这样保留未分配部门的员工
，然后还要做左连接关联部门表，才能继续保留未分配部门的员工

select last_name, first_name, dept_name
from employees e left join  dept_emp de on e.emp_no = de.emp_no left join departments d 
on de.dept_no = d.dept_no


SQL21  : 请你查找在职员工自入职以来的薪水涨幅情况，给出在职员工编号emp_no以及其对应的薪水涨幅growth，
并按照growth进行升序，以上例子输出为
（注: to_date为薪资调整某个结束日期，或者为离职日期，to_date='9999-01-01'时，表示依然在职，无后续调整记录）

分析1 : 查出两个虚表， 第一张为入职时工资表。 第二张为 现在工资表。 这样进行连接，就是左右一对一。

select b.emp_no,(b.salary-a.salary) as growth
from 
(select e.emp_no,s.salary
from employees e left join salaries  s on e.emp_no=s.emp_no
and e.hire_date=s.from_date)a -- 入职工资表
inner join 
(select e.emp_no,s.salary
from employees e left join salaries  s on e.emp_no=s.emp_no
where s.to_date='9999-01-01')b -- 现在工资表
on a.emp_no=b.emp_no
order by growth

分析2： 进行分组查询  having筛选出 目前还在职的分组。 使用where 筛选出时间为最早或者最晚的条目。
然后使用 max - min 即可。 有漏洞。 因为可能降薪。 所以正确写法应该是第一种。

select e.emp_no, max(s.salary) - min(s.salary) as growth
from employees e join salaries s on e.emp_no = s.emp_no
where s.from_date in (select max(ss.from_date) from salaries ss group by ss.emp_no) 
        or s.from_date in (select min(ss.from_date) from salaries ss group by ss.emp_no)
group by e.emp_no
having '9999-01-01' in (select ss.to_date from salaries ss where ss.emp_no = e.emp_no)
order by growth

分析3 ： 3表连接  employees 和 salaries 先按照emp_no 和 hire_date = from_date连接。
        这样 就只保留下来 入职时间的那一条 salaries记录。然后再和 salaries 连接to_date='9999-01-01'
        这样，入职时间记录就和 当先薪资记录连接在一起了。

select e.emp_no, (s2.salary-s1.salary) growth
from employees e
join salaries s1 on e.emp_no=s1.emp_no and e.hire_date=s1.from_date
join salaries s2 on e.emp_no=s2.emp_no and s2.to_date='9999-01-01'
order by growth

