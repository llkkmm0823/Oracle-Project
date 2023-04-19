--11_SubQuery.sql

-- 서브쿼리
-- SubQuery : 하나의 select문장의 절 안에 포함된 또 하나의 select 쿼리문

--SCOTT 이 근무하는 곳의 부서명과 지역 출력
select deptno from emp where ename = ' SCOTT' ; -- 결과 : 30
select dname, loc from dept where deptno = 30;

--위 select 명령의 결과를 다른 select 명령에 사용 ( 서브쿼리 사용)
select dname, loc from dept
where deptno = ( select deptno from emp where ename = 'SCOTT');



--[연습문제] SCOTT과 동일 직업(job)을 가진 사원의 모든 정보를 출력
select job from emp where ename='SCOTT';
select*from emp where job='ANALYST';
--이 둘을 합하면
select*from emp where job=(select job from emp where ename='SCOTT');


--[연습문제] SCOTT과 급여가 동일하거나 더 많이 받는 사원의 이름과 급여 출력
select ename,sal from emp where sal>=(select sal from emp where ename='SCOTT');
-- ename과 sal을 보여줘, emp 테이블에서, sal이 동일하거나 더 많이 받는 (어떤 것보다?)(sal, emp에서, ename이 SCOTT인 사람의)
-- 갑자기 길어진 명령이 헷갈릴 땐 내 식으로 해석하기

--[서브쿼리 & 그룹함수]
--전체 사원 평균 평균급여보다 더 많은 급여를 받는 사원의 이름, 급여, job
select ename, sal, job from emp where sal>=(select avg(sal)from emp)




--[ in , some, any 함수와 서브 쿼리]
-- 급여를 3000이상 받는 사원이 소속된 부서에 근무 사원들의 이름, 부서번호, job
-- 급여 3000이상 사원의 부서번호(중복제거);
select distinct deptno from emp where sal>=3000; --> 급여를 3000이상 받는 사원들의 부서번호들이 결과로 도출됨
select ename, deptno, job from emp where deptno in (select distinct deptno from emp where sal>=3000;);
--이름과 부서번호, job을 가져와 emp테이블에서 어디서? 부서번호 in ( 급여를 3000이상 받는 사원들에서)



--[연습문제]
-- 30번 부서 소속 사원들 중에서 급여를 가장 많이 받는 사원보다... 급여가 더 많은 사원의 이름과 job, 급여 출력
-- 첫 번째 방법
select max(sal) from emp where deptno=30
select ename,sal,job from emp where sal>=(select max(sal) from emp where deptno=30); 
-- JONES 3272.5 Manager
-- SCOTT 3300 Analyst
-- FORD 3300 Analyst

-- 두 번째 방법
select sal from emp where deptno=30 --결과 : 30번 부서 사원들의 급여들
--30번 부서 사원들의 급여들 모두보다 큰 급여의 사원 조회
select ename,sal,job from emp where sal>=all(select sal from emp where deptno=30);


--30번 부서 소속 사원들 중에서 급여를 가장 많이 받는 사원
select ename from emp where sal = (select max(sal) from emp where deptno='30'); 
--BLAKE




-- 부서번호가 30번인 사원들의 급여 중에서 가장 낮은 급여보다 높은 급여를 받는 사원의 이름과 job, 급여 출력
--첫 번째 방법
select min(sal) from emp where deptno=30; --30번 부서 사원들중에 제일 낮은 급여
select ename,sal,job from emp where sal>(select min(sal) from emp where deptno=30); 

--두 번째 방법
select sal from emp where deptno=30;  --  결과 30번 부서 사원들의 급여들
-- 30번 부서사원의 급여들 중 하나보다 큰 급여의 사원
select ename, job, sal where sal > any (select sal from emp where deptno=30);




-- 영업사원(job='SALESMAN')들의 최소 급여보다 많이 받는 사원들의 이름과 급여와 직급, 급여를 출력하되 
--첫번째 방법
select min(sal) from emp where job='SALESMAN';
select ename,job,sal from emp where sal>(select min(sal) from emp where job='SALESMAN');
select ename,job,sal  from emp where sal>(select min(sal) from emp where job='SALESMAN') and job<>'SALESMAN';
--영업사원(SALESMAN)은 출력하지 않음


--두번째 방법
select ename,job,sal from emp where sal>any (select sal from emp where job='SALESMAN')and job<>'SALESMAN';


