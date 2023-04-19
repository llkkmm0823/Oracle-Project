--12_View.sql

--*오라클 - 뷰(View)

	-- 물리적인 테이블에 근거한 논리적인 "가상 테이블" -> 저장되어 있는 select 문 (변수처럼 저장해놨다가 호출하는 것 처럼 저장되어 있는 select문을 호출)
	-- 	가상이란 단어는 실질적으로 데이터를 저장하고 있지 않기 때문에 붙인 것이고,
	-- 		테이블이란 단어는 실질적으로 데이터를 저장하고 있지 않더라도 사용계정자는 마치 테이블을 사용하는 것과 동일하게 뷰를 사용할 수 있음
	-- 		뷰는 기본 테이블에서 파생된 객체로서 기본테이블에 대한 하나의 쿼리문
	
	-- 실제 테이블에 저장된 데이터를 뷰를 통해서 볼 수 있도록 함
	-- 사용자에 주어진 뷰를 통해서 기본 테이블을 제한적으로 사용하게 됨
	-- 뷰는 이미 존재하고 있는 테이블에 제한적으로 접근하도록 함
	
	-- 기본테이블 : 뷰를 생성하기 위해서 실질적으로 데이터를 저장하고 있는 물리적인 테이블



-- 뷰 생성방법
--create or replace view 뷰이름 as select 조회 명령
-- > 결과는 select의 결과를 테이블로 내어 놓는 가상 테이블 제작 명령이 생기는 셈
-- 뷰 이름으로 조회 명령을 저장하고 있다가 뷰 이름으로 조회할 때마다 조회 명령이 실행되어 결과를 도출

create or replace view result_inner_join as
select a.empno, a.ename, a.job , a.hiredate, a.deptno, b.dname, b.loc 
from emp a, dept b
where a.deptno = b.deptno;

--테이블 저장이 아니라 select문 저장으로서 만들어진 뷰를 또 다른 select로 조회할 때마다 저장된 select가 실행되며 결과를 도출
select*from result_inner_join where job='MANAGER';
--where을 붙여 결과 내에서 또 다른 조건을 사용할 수 있음

--create or replace view result_inner_join 을 사용하여 다른 select 문을 적용하고 실행하면
--replace 명령이 동작하여 result_inner_join이라는 이름으로 뷰 내용이 대체됨
-- result_inner_join의 select문이 고정적이진 않다는의미

--최초 실행은 create 실행 (생성)
-- 같은 이름의 두 번째 실행부터는 replace로 실행
