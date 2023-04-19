-- 10_Join.sql

-- JOIN
-- 두 개이상의 테이블에 나눠져 있는 관련 데이터들을 하나의 테이블로 모아서 조회하고자 할 때 사용하는 명령

-- [1] 이름 Douglas Grant 인 사원이 근무하는 부서명, 부서의 지역명 출력하고자 한다면
select department_id from employees where emp_name='Douglas Grant';
-- 위 명령을 실행 후 얻어진 부서번호로 아래와 같이 부서번호 검색하여 부서명 결과 도출
select department_name, parent_id from departments where department_id=50;

-- 위의 두개의 명령을 하나의 명령으로 합해주는 역할을 join 명령이 실행

-- [2] join : 두 개이상의 테이블에 나뉘어져 있는 데이터를
-- 한 번의 sql문으로 원하는 결과를 얻을 수 있음


-- cross join : 두 개이상의 테이블이 join될 때 where절에 의해
-- 공통되는 컬럼에 의한 결합이 발생하지 않는 경우

-- * 가장 최악의 결과를 얻는 조인 방식 *

-- A테이블과 B테이블의 cross join 된다면
-- A테이블의 1번 레코드와 B테이블의 모든 레코드와 하나하나 모두 조합
-- A테이블의 2번 레코드와 B테이블의 모든 레코드와 하나하나 모두 조합
-- A테이블의 3번 레코드와 B테이블의 모든 레코드와 하나하나 모두 조합
-- ....


create table A(
a1 varchar2(5),
a2 varchar2(5),
a3 varchar2(5)
);

create table B(
b1 varchar2(5),
b2 varchar2(5),
b3 varchar2(5)
);

insert into A values('ar1','ar1','ar1');
insert into A values('ar2','ar2','ar2');
insert into A values('ar3','ar3','ar3');
delete from B;
insert into B values('br1','br1','ar1');
insert into B values('br2','br2','ar2');
insert into B values('br3','br3','ar3');
insert into B values('br4','br4','ar3');


select*from A;
select*from B;

--cross join : 별도의 조건이나 키워드 없이 from 뒤에 테이블이름을 컴마로 구분해서 두 개 이상 쓰면 cross join이 됨
select*from A, B;


-- A테이블의 레코드가 8개, B테이블의 레코드가 7개라면 총 크로스조인의 결과 레코드 수는 8x7=56
-- A테이블의 필드가 8개, B테이블의 필드가 7개라면 총 크로스조인의 결과 레코드 수는 8+3=11
select*from dept; --레코드 4, 필드 3
select*from emp; --레코드 14, 필드 8

--크로스 join
select*from dept, emp;--레코드 56, 필드 11

--equi join : 조인 대상이 되는 두 테이블에서 공통적으로 존재하는 컬럼의 값이 일치하는 행을 연결하여 결과를 생성
select*from dept, emp where emp.deptno = dept.deptno;


--각 사원의 이름, 부서번호, 부서명, 지역을 출력 (emp, dept)
select ename, emp.deptno, dname, loc from emp,dept where emp.deptno = dept.deptno;

--이름이 SCOTT인 사원의 이름, 부서번호, 부서명, 지역을 출력 (emp, dept) --문자는 대문자로 입력해야 똑같은거 찾아줌
select ename, emp.deptno, dname, loc 
from emp,dept 
where emp.deptno = dept.deptno and ename='SCOTT';
--컬럼 명 앞에 테이블 명을 기술하여 컬럼의 소속을 명확히 해줘야 오류가 없음 ex) emp.deptno


--테이블 명에 별칭을 부여한 후 컬럼 앞에 소속 테이블을 지정
--테이블 명으로 소속을 기술 할 때는 한 쪽에만 있는 필드에 생략이 가능하지만 별칭 부여 시에는 모든 필드 앞에 반드시 별칭을 기술해야함
select a.ename,b.dname,b.loc,a.deptno
from emp a, dept b
where a. deptno = b.deptno and a.ename='SCOTT';


--★★★★★★★★★세개의 테이블을 조인하는 문제 출제★★★★★★★★★★★★★★
--rentlist의 대여건수의 대여일자, 대여도서번호, 대여회원번호, 할인금액을 출력하되, 도서의 제목, 회원의 이름을 도서번호와 회원번호 옆에 출력
select*from rentlist;
select*from booklist;
select*from memberlist;

select a.rentdate,a.bnum,b.subject,a.mnum,c.name,a.discount
from rentlist a, booklist b,memberlist c
where b.subject=a.bnum and a.mnum=c.membernum;

select a.rentdate,a.bnum,b.subject,a.mnum,c.name, b.rentprice-a.discount as "매출금액"
from rentlist a, booklist b,memberlist c
where b.subject=a.bnum and a.mnum=c.membernum;



-- non-equi join
-- 동일 컬럼이 없어서 다른 조건을 사용하여 조인
-- 조인 조건에 특정 범위 내에 있는지를 조사하기 위해 조건절에 조인 조건을 '=' 연산자 이외의 비교
-- 연산자를 이용
select*from emp;
select*from salgrade;


select a.ename, a.sal, b.grade from emp a, salgrade b
where a.sal > b.losal and a.sal <= b.hisal;

select a.ename, a.sal, b.grade from emp a, salgrade b
where a.sal between b.losal and b.hisal;

--두 개의 문장은 같은 결과 도출

--★★★★★★★★시험문제 출제 ★★★★★★★★
--outer join
--join 조건에 만족하지 못해서 해당 결과를 출력 시에 누락이 되는 문제점이 발생할 때 해당 레코드를 출력하는 조인
select a.subject, a.booknum,b.rentdate from booklist a,rentlist b
where a.booknum=b.bnum(+);
--join 조건에 만족하지는 못하지만 만족하지 못하는 것들도 보고 싶을 때, 더보기의 느낌으로 +를 붙여주는 것
-- 없는 데이터에 null값으로 보여주세요~ 할 때 이젠 +만 붙이면 알아서 null로 들어가 나옴

--outer join으로 emp 테이블의 인원에 대한 부서명을 출력하되 부서명이 없는 필드는 NULL값으로 표시
select*from emp a, dept  b
where a.deptno(+)=b.deptno;
--사원이 없는 곳은 null값으로 넣어주세요!


--[3]ANSI Join
-- (1) Ansi Cross Join
select*from emp, dept -- 일반크로스 조인 표현
select*from emp cross join dept -- ansi Cross join ->일반 크로스 조인과 동일한 효과
--cross join이라는 것을 좀 더 명확히 쓰자! 라는 의미로 , 컴마 대신 cross join으로 정확하게 써줬다는 점

-- (2) Ansi inner join -- 일반 equi 조인과 같은 효과
-- 일반 equi 조인 표현 방식
select ename, dname from emp a, dept b where a.deptno = b.deptno

--Ansi 이너 조인의 표현 방식
select ename, dname from emp inner join dept on emp.deptno = dept.deptno;-- using 대신 on을 쓰는 것
select ename, dname from emp inner join dept on emp.deptno = dept.deptno where ename = 'SCOTT';

--Ansi 이너 조인의 다른 표현 방식 _ 표현방식만 달라진 것이다
select ename, dname from emp inner join dept using (deptno); -- on 대신 using을 쓰는 것
-- 두 테이블의 조인 기준이 되는 필드명이 똑같을 때만 사용 가능


-- (3) Ansi Outer Join --기존 Outer join의 표현방식
select*from emp, dept where emp.deptno = dept.deptno(+);
select*from emp, dept where emp.deptno(+)= dept.deptno;

--Ansi Outer Join 표현방식
select*from emp left outer join dept on emp.deptno = dept.deptno;
select*from emp right outer join dept on emp.deptno = dept.deptno;
--기준이 되는 필드명 중 A테이블의 필드에는 있으나 B테이블 필드에는 해당 값이 없는 경우에 대한 표현여부결정







