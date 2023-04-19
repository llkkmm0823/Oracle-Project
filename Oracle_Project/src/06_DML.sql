--06_DML.sql

--DML (Data Management Language) 데이터 조작언어

--테이블에 레코드를 조작(추가, 수정, 삭제, 조회)하기 위한 명령어들
--INSERT (추가)
--UPDATE (수정)
--DELETE(삭제)
--SELECT(조회 및 선택)


--[1] 샘플테이블 생성
drop table exam01 purge;

create table exam01(
deptno number(2),--부서번호
dname varchar2(14), --부서명
loc varchar2(14) -- 위치
);

--[2] 레코드 추가
--레코드 추가 명령 사용1
-- inset into 테이블이름(필드명1, 필드명2, ...)values (값1,값2,...)
-- 값은 문자('123')와 숫자(123)를 구분하여 입력

--레코드 추가 명령 사용2
--insert into 테이블 이름 values (전체 column(필드, 열)에 넣을 값들);

--첫번째 방식은 필드명과 입력되어야 하는 값을 1:1로 매핑하여 입력
-- null값이 있어도 되는 필드는 필드명, 또는 기본값이 있는 필드명은 필드명과 값을 생략하고 입력가능
-- 두번째 방식은 모든 필드에 해당하는 데이터를 모두 입력하는 경우로서 필드명들을 명령어 속에 나열하지 않아도 되지만 
-- 필드의 순서대로 빠짐없이 데이터가 나열되어야 하는 불편함이 존재

--첫번째 방식의 레코드 추가
insert into exam01(deptno,dname,loc)values (10, 'ACCOUNT','NEWYORK');
--두번째 방식의 레코드 추가
insert into exam01 values (30, 'SALES','CHICAGO');

-- 두가지 방법 모두 NULL값을 입력할 수 있음
insert into exam01(deptno,dname)values (20, 'MARKETING');--첫번째 방법
insert into exam01 values (40, 'OPERATION',null);--두번째 방법

select*from exam01;

select*from booklist;
-- 두가지 방법 중 자유롭게 선택하여서 booklist 테이블에 7개의 레코드를 추가
-- booknum은 시퀀스 이용
--grade는 'all' '13' '18' 세가지 골라 입력 
insert into booklist(booknum,subject,makeyear,inprice,rentprice,grade)
values(book_seq.nextVal,'좀비아이',2020,12000,2500,'all');
insert into booklist values(book_seq.nextVal,'가면산장 살인사건',2018,133200,1500,'13');
insert into booklist values(book_seq.nextVal,'나미야잡화점의 기적',2017,13320,2000,'18');
insert into booklist values(book_seq.nextVal,'유튜브 영상편집',2020,20700,2500,'all');
insert into booklist values(book_seq.nextVal,'이것이 자바다',2017,30000,3000,'18');
insert into booklist values(book_seq.nextVal,'JSP웹프로그래밍',2016,25000,2500,'13');
insert into booklist values(book_seq.nextVal,'오라클데이터베이스',2020,30000,3000,'all');

--memberlist에 10명의 데이터 추가 member_seq이용
select*from memberlist;
insert into memberlist(membernum,name,phone)
values(member_seq.nextVal,'홍길동','010-1111-2222');
insert into memberlist(membernum,name,phone)
values(member_seq.nextVal,'홍길서','010-2222-3333');
insert into memberlist(membernum,name,phone)
values(member_seq.nextVal,'홍길남','010-3333-4444');
insert into memberlist(membernum,name,phone)
values(member_seq.nextVal,'홍길북','010-4444-5555');
insert into memberlist values(member_seq.nextVal,'추신수','010-6666-7777','84/07/07',240,'M',28);
insert into memberlist values(member_seq.nextVal,'류현진','010-8888-9999','83/08/08',142,'F',27);
insert into memberlist values(member_seq.nextVal,'손흥민','010-0000-1111','84/06/14',330,'M',31);
insert into memberlist values(member_seq.nextVal,'이청용','010-1212-1212','84/12/04',250,'F',25);
insert into memberlist values(member_seq.nextVal,'이영표','010-2323-2323','84/07/28',100,'M',33);
insert into memberlist values(member_seq.nextVal,'최지만','010-3434-3434','84/04/12',20,'F',29);

--rentlist table도 10명의 데이터를 추가
insert into rentlist values('2021/10/01',rent_seq.nextVal,3,2,100);
insert into rentlist values('2021/10/02',rent_seq.nextVal,3,3,100);
insert into rentlist values('2021/10/03',rent_seq.nextVal,8,4,200);
insert into rentlist values('2021/10/04',rent_seq.nextVal,9,2,100);
insert into rentlist values('2021/10/05',rent_seq.nextVal,6,1,300);
insert into rentlist values('2021/10/05',rent_seq.nextVal,1,9,400);
insert into rentlist values('2021/10/06',rent_seq.nextVal,4,5,300);
insert into rentlist values('2021/10/07',rent_seq.nextVal,5,2,100);
insert into rentlist values('2021/10/08',rent_seq.nextVal,6,8,200);
insert into rentlist values('2021/10/09',rent_seq.nextVal,3,6,100);

select*from rentlist;

--[3] 데이터 변경-수정(UPDATE)
--UPDATE 테이블명 SET 변경내용 WHERE  검색조건
--update memberlist set age=30 where membernum=3;

--명령문에 where 이후 구문은 생략 가능
-- 다만 이 부분을 생략하면 모든 레코드를 대상으로 해서 update명령이 실행되어 모든 레코드가 수정됨
-- 검색조건 : 필드명(비교-관계연산자) 조건값으로 이루어진 조건연산이며, 흔히 자바에서 if()괄호안에 사용했던 연산을 그대로 사용함
-- 나이가 29세이상 -> where age>=29

--dname이 'ACCOUNT' 레코드의 deptno 를 10으로 수정
update exam01 set deptno=40 where dname='ACCOUNT';

--exam01테이블에서 deptno이 30인 레코드의 dname을 'OPERATION'로 수정
update exam01 set deptno=30 where dname='OPERATION';

--exam01테이블에서 deptno이 20인 레코드의 dname을 'MARKETING'로 수정
update exam01 set deptno=20 where dname='MARKETING';

--exam01테이블에서 deptno이 30인 레코드의 loc를 'BOSTON'으로 수정
update exam01 set deptno=30 where loc='BOSTON';

--exam01테이블에서 deptno이 40인 레코드의 loc를 'LA'로 수정
update exam01 set deptno=40 where loc='LA';

select*from exam01;

select*from booklist;
--booklist 테이블의 도서 제목 '봉제인형 살인사건' 도서의 grade를 '18'로 수정
update booklist set subject='봉제인형 살인사건' where grade='18';
--이거 거꾸로 해야되는데 지금 내가 셋 서브젝트를 이렇게 해서 다 바뀌었음,,,원복해야됨
update booklist set subject='나미야잡화점의 기적' where booknum='6';
update booklist set subject='이것이 자바다' where booknum='8';
--원복완료
--다시 booklist 테이블의 도서 제목 '봉제인형 살인사건' 도서의 grade를 '18'로 수정
update booklist set grade='18' where subject='봉제인형 살인사건';
select*from booklist;



select*from emp;
select*from dept;
select*from bonus;
select*from salgrade;

--emp 테이블의 모든 사원의 sal값을 10%씩 인상
update emp set sal=sal*1.1 ;
--emp테이블에서 sal값이 3000이상인 사원의 급여 10%삭감
update emp set sal=sal*1.1 where sal>=3000;
--emp테이블의 hiredate가 2002년 이전인 사원의 급여를 +2000 -> (2001-12-31보다 작거나 같은)
update emp set sal=sal+2000 where hiredate<='2002-01-01';
--emp 테이블의 ename이 j로 시작하는 사원의 job을 manager로 변경
update emp set job='manager' where ename like 'j%'; -- j로 시작하는
update emp set job='manager' where ename like '%j'; -- j로 끝나는
update emp set job='manager' where ename like '%j%'; -- j를 포함하는

--memberlist 테이블에서 bpoint가 200이 넘는 사람만 bpoint*100으로 변경
update memberlist set bpoint=bpoint*100 where bpoint>=200;
--rentlist 테이블에서 할인금액이 100원이 넘으면 모두 할인 금액을 90으로 변경
update rentlist set discount=90 where discount>=100;




--[4]레코드의 삭제
--delete from 테이블명 where 조건식

--rentlist 테이블에서 discount가 100이하인 레코드를 삭제
delete from rentlist where discount<=10;

select*from MEMBERLIST;
delete from memberlist where membernum=2;
--where절이 없으면 테이블 내의 모든 레코드 삭제

--삭제의 제한 
delete from booklist where booknum=2;
--2번 도서가 rentlist 대여목록에 존재하므로
-- 만약 booklist에서 해당 도서가 삭제되면 외래키의  참조무결성에 위배되어 삭제되지않음 -에러
--즉 2번을 삭제하면 대여목록에서는 없는 책을 대여해준 것이 되기 때문에 참조무결성에 위배됨
select*from booklist;
select*from rentlist;
--★★★★★★★★★★★★★★★시험 출제★★★★★★★★★★★★★★★★★★★
--에러 문구 : integrity constraint (SCOTT.FK2) violated - child record found
--참조무결성에 위배되기 때문에 삭제되지 않는 에러문구
--★★★★★★★★★★★★★★★시험 출제★★★★★★★★★★★★★★★★★★★

--해결방법 #1
-- 우선 rentlist 테이블에 해당 도서 대여 목록 레코드를 모두 삭제한 후 booklist 테이블에서 해당 도서 삭제
--delete from rentlist where bnum=2;
--delete from booklist where booknum=2;


--해결방법#2
-- 외래키 제약조건을 삭제한 후 다시 생성
--생성시에 "옵션을 추가해서" 참조되는 값이 삭제되면 참좋는 값도 같이 삭제되게 구성


--외래키 삭제
alter table rentlist drop constraint fk1; 


--새로운 외래키 추가
alter table rentlist add constraint fk1 foreign key(bnum) references booklist(booknum) on delete cascade;
-- on delete cascade : booklist의 도서가 삭제되면 rentlist의 해당 도서 대여내역도 함께 삭제하는 옵션

--memberlist 테이블에서 회원한명을 삭제하면 rentlist 테이블에서도 
-- 해당회원이 대여한 기록을 같이 삭제하도록 외래키설정 변경(외래키 제약조건 삭제 후 재 생성)

alter table rentlist drop constraint fk2;
alter table rentlist add constraint fk2 foreign key(mnum)references memberlist(membernum)on delete cascade;


--참조되는 값의 삭제가 아니라 수정은 아직 적용되지 않음
--booklist와 memberlist 테이블의 booknum, membernum은 수정이 아직 불가함
--이를 해결하기 위해서 외래키 설정 시 on update cascade 옵션을 추가하면 될 듯 하나 오라클에서는 불가능
-- my sql에서만 사용이 가능하며 오라클에서는 뒷단원의 트리거를 공부하면서 외래키가 수정이 되도록 하겠음

-- trigger도 없고, on update cascade 명령도 없는 현재는 update 테이블명 set 수정내용 where 조건 으로 
-- 참조하는 테이블 내 해당 레코드를 먼저 수정하고 참조되는 테이블의  해당 레코드를 수정
-- update rentlist set bnum =2002 where bnum=2;
-- update booklist set booknum =2002 where booknum=2;





