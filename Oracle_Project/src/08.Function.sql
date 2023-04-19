--08.Function.sql

-- 라이브러리 함수 요약

--[1] 샘플 테이블인 dual 테이블
-- dual : 테이블이 대상이 아닌 연산을 하려고 할 때 from 다음에 형식적으로 붙이는 없는 테이블의 이름
select*from tab;
select*from dual;

--[2] 임시 데이터 출력
select 1234*1234 from dual;


--***문자열 처리 관련 함수***
--[3] lower() : 모든 문자를 소문자로 변환
select lower('Hong Kill Dong') as "소문자" from dual;

--[4] upper() : 모든 문자를 대문자로 변환
select upper('Hong Kill Dong') as "대문자" from dual;

--[5] initcap() : 첫 문자만 대문자로 변환
select initcap('hong kill dong') as "첫글자만 대문자" from dual;

select ename from emp;

-- 아래와 같이 테이블 내의 특정 필드를 대상으로 사용할 수 있음 
select lower(ename) from emp;
select initcap(ename) from emp;

--[6] concat( ) : 문자열 연결
select concat('이젠IT','아카데미'), '이젠 IT' || '아카데미' from dual;

--[7] length( ) : 문자열의 길이
select length('이젠 아이티 아카데미'), length('The ezen IT') from dual;

--[8] substr( ) :  문자열 추출(데이터, 인덱스(1), 카운트)
select substr('홍길동 만세',2,4) from dual;
--substr 경우 자바의 substring 처럼 시작번째부터 끝번째 +1이 아니라 시작번째부터 글자수를 나타냄. 위의 경우 2번째 글자부터 네 글자 표시

--[9] instr( ) : 문자열 시작 위치
select instr('홍길동 만세 동그라미', '동') from dual;

--[10] lpad( ), rpad( ) : 자리채우기
select lpad('Oracle',20,'#')from dual; -- ###################Oracle
--출력양식으로 20칸의 자리를 마련 후 출력내용을 채우고 나머지 남는자리의 왼쪽에 #으로 채움
select rpad('Oracle',20,'*')from dual; -- Oracle*******************

--[11] trim( ) : 컬럼이나 대상 문자열에서 특정문자가 첫 번째 글자이거나 마지막 글자이면 잘라내고 남은 문자열만 반환
select 'String'||trim('a'from'aaaOracleaaaaaaaaaa')||'String' as result from dual; 
-- StringOracleString

select 'String'|| '    Oracle    ' || 'String' as result from dual;
--String  Oracle  String

select 'String'||trim(' 'from'    Oracle    ')||'String' as result from dual; 
-- StringOracleString





--***수식 처리 관련 함수***

--[12] round( ) : 반올림(음수 : 소숫점 이상 자리)
select round(12.3456,3) from dual;
--12.3456 : 반올림하려는 대상 숫자    3: 반올림하여 표시하고자하는 마지막 자릿수

-- 3 : 소수점 넷 째 자리에서 반올림하여 셋 째자리까지 남김
-- 2 : 소수점 셋 째 자리에서 반올림하여 둘 째자리까지 남김
-- 1 : 소수점 둘 째 자리에서 반올림하여 첫 째자리까지 남김
-- 0 : 소수점 첫 째 자리에서 반올림하여 소수점 자릿수 삭제
-- -1 : 1의 자리에서 반올림하여 10의 자리까지 남김
-- -2 : 10의 자리에서 반올림하여 100의 자리까지 남김
-- -3 : 100의 자리에서 반올림하여 1000의 자리까지 남김

select round(1728.9382, 3)from dual; --1728.938
select round(1728.9382, 2)from dual; --1728.94
select round(1728.9382, 1)from dual; --1728.9
select round(1728.9382, 0)from dual; --1729
select round(1728.9382, -1)from dual; --1730
select round(1728.9382, -2)from dual; --1700
select round(1728.9382, -3)from dual; --2000


--[13] abs( ) :  절댓값
select abs(-10) from dual; -- 10

--[14] floor( ) : 소숫점 아래 절사 - 반올림없음
select floor(12.94567) from dual; -- 12

--[15] trunc( ) : 특정 자리 자르기 - 반올림 없음, 3은 남기고 싶은 소수점 아래 자릿수
select trunc(12.34567 , 3 ) from dual; -- 12.345

--[16] mod( ) : 나머지 --8을 5로 나눈 나머지
 select mod(8,5) from dual; -- 3

 
 
 --***날짜 처리 관련 함수***
--[17] sysdate : 날짜
select sysdate from dual; -- 오늘 날짜와 현재 시간 

--[18]months_between( ) : 개월 수 구하기
select floor(months_between('2021-12-31','2020-07-10'))from dual; --17.677419
 
 --[19] add_months( ) : 개월 수 더하기
 select add_months(sysdate,200) from dual; --2039-12-13 14:23:38.0
 
 --[20] next_day( ) : 다가올 요일에 해당하는 날짜 - 오늘 날짜(sysdate)에서 가장 가까운 일요일
 select next_day(sysdate,'일요일')from dual;
 
 --[21] last_day() : 해당 달의 마지막 일 수
 select last_day(sysdate)from dual;
 select last_day('2020-12-15') from dual;
 
 --[22] to_char( ) : 문자형(String)로 반환   Date  -> varchar
 select to_char(sysdate,'yyyy-mm-dd')from dual;
 --select 와 from 사이에서 많이 사용되는 함수
 
 --[23] to_date( ) : 날짜형(Date)로 반환
 select to_date('2019/12/31','yyyy-mm-dd')from dual;
--insert 명령에서 values() 괄호 안에 많이 사용되는 함수

 
 --그 외 활용 가능한 함수들
 
 --[24] nvl( ) : NULL인 데이터를 다른 데이터로 변경
 select comm/100 as comm_pct from emp;
 
 --comm 필드값에 따라서 일부 레코드는 계산 결과가 null이 나올 수 있음
 select nvl(comm,1)/100 as comm_pct from emp;
 --해당 필드 값이 null이면 1로 바꿔서 계산에 참여
 
 --power 함수
 select power(3,2),power(3,3),power(3,3.0001)from dual;
 --첫 번째 요소값을 두 번 째 요소만큼 거듭 제곱
 
 --제곱근 SQRT
 select sqrt(2),sqrt(5) from dual;
 
 
 --[25] decode( ) : switch문 비슷한 기능
 -- join 명령을 쓸 수 없는 상황에서 사용하는 함수
 select*from employees;
 select*from departments;
 
--  select employee_id, emp_name,department_id, decode() as "부서명" from employees;
 
 select employee_id, emp_name,department_id, 
  	decode(department_id, 
  	10, 'ACCOUNT',
  	20,'RESEARCH',
  	30,'SALES',
  	40,'OPERATIONS',
  	50,'SH_CLECK',
  	60,'IT_PROG',
  	70, 'PR_REP',
  	80,'SA_REP',
  	90, 'AD_PRES',
  	100, 'FI_ACCOUNT',
  	110, 'AC_ACCOUNT'
  	) as "부서명"
  	from employees;

 
 --[26] case( ) :  if ~else if ~와 비슷한 구조
 select employee_id, emp_name, department_id,
 			case when department_id = 10 then 'ACCOUNT'
 					when department_id = 20 then 'RESEARCH'
 					when department_id = 30 then 'SALES'
 					when department_id = 40 then 'OPERATIONS'
 					when department_id = 50 then 'SH_CLECK'
 					when department_id = 60 then 'IT_PROG'
 					when department_id = 70 then 'PR_REP'
 					when department_id = 80 then 'SA_REP'
 					when department_id = 90 then 'AD_PRES'
 					when department_id = 100 then 'FI_ACCOUNT'
 					when department_id = 110 then 'AC_ACCOUNT'
 			end as "부서명"
 from employees;
 
 
 -- mod 와 remainder
 -- 둘 다 첫 번째 요소를 두 번쨰 요소로 나눈 나머지를 계산하지만 내부적 계산 방법에서 차이가 있음
 select moc(19,4), mod(19.123, 4.2) from dual;
 select remainder(19,4), remainder(19.123, 4.2) from dual;
 -- mod : 19.4 * floor (19/4)
 -- reminder : 19 - 4 * round(19/4)
 -- 10.5를 4.2로 나눈 나머지 : 10.5 - (4.2* 2) =2.1
 
 --문자함수 replace
 select replace('나는 너를 모르는데 너는 나를 알겠는가? ','나','너') from dual;
 --replace(문자열1, 문자열2, 문자열 3)
 -- 문자열1 내에 있는 글자 중에 문자열2를 찾아서 문자열 3으로 대체
 
 select replace('abc def',' ','')from dual;
 
 			
 --변환함수
 --숫자 -> 문자   세 자리마다 컴마를 넣어서 변환
 select to_char(123456789,'999,999,999')from dual;
 
 --오늘 날짜를 10글자의 양식으로 변환
 select to_char(sysdate,'yyyy-mm-dd')from dual;
 
 --6자리 문자를 숫자로 변환
 select to_number('123456')from dual;
 
 -- 8글자 중 4글자를 년도, 2글자를 월 2글자를 나머지 2글자를 일로 인식해서 날짜로 변환
 select to_date('20140101','yyyy-mm-dd')from dual;
 
 --날짜와 시간에 해당하는 문자들을 제공된 양식대로 날짜시간으로 변환
 select to_date('20140101 13:44:50','yyyy-mm-dd hh24:mi:ss')from dual;
 
 --'am' or 'pm 오전 오후 표시 -> to_char(sysdate, 'am')
 select to_char(sysdate,'am')from dual;
  
 --yyyy-> 년도 표시
 select to_char(sysdate,'yy"년"')from dual; -- ->21
 select to_char(sysdate,'y"년"')from dual; -- ->1

 
 --month, mon -> 월 표시
 select to_char(sysdate, 'month') from dual;  --> 4월
 select to_char(sysdate, 'mon') from dual;  --> 4월
 select to_char(sysdate, 'mm') from dual;  --> 04
 select to_char(sysdate, 'mm"월"') from dual;  -->04월
 
 --D요일 1~7로 표시
 select to_char(sysdate, 'd') from dual;  --> 5(목요일에 해당하는 5) dd의 날짜와 다름
 select to_char(sysdate, 'day') from dual; --> 목요일
 
 --dd일자를 01~31로 표시
 select to_char(sysdate, 'dd') from dual; -->13
 
 --ddd일자를 001~365 형태로 표시
 select to_char(sysdate, 'ddd') from dual;  -->103
 
 --dl : 오늘날짜를 요일까지 표시
 select to_char(sysdate, 'dl') from dual;  -->2023년 4월 13일 목요일
 
 
 -- hh,hh12 : 시간을 01~12시로
 -- hh24 : 시간을 01~23시로 표시
 -- mi : 분을 01~59형태로 표시
 select to_char(sysdate,'mi"분"') from dual; --> 14
 -- ss : 초를 01~59 형태로 표시
 select to_char(sysdate, 'hh24"시"mi"분"ss"초"')from dual;
 
 
 
 
 
 
 
 
 
 
 
 
 

