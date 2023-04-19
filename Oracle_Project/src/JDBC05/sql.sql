--sql.sql

create or replace view rentDetail as
select c.numseq,to_char(c.rentdate,'YYYY-MM-DD') as rentdateStr, 
		c.bnum, a.subject, c.mnum, b.name,a.rentprice, c.discount, a.rentprice-c.discount as amount
from booklist a, memberlist b, rentlist c
where a.booknum = c.bnum and b.membernum=c.mnum;

select*from rentDetail;
select*from rentlist
select*from booklist
select*from memberlist
delete from rentlist where bnum=12
delete from rentlist where mnum in (2,5)

update rentlist set discount=200 where numseq=10;


--외래키 다시 설정하기
--추가 : ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명 제약조건식
alter table rentlist add constraint fk1 foreign key(bnum) references booklist(booknum);
alter table rentlist add constraint fk2 foreign key(mnum) references memberlist(membernum);
alter table booklist add constraint booknum_pk primary key (booknum);

constraint booknum_pk primary key (booknum)
constraint fk1 foreign key(bnum) references booklist(booknum),
--제약조건 : 현재 테이블의 bnum 필드는 booklist 테이블의 booknum필드값을 참조함
constraint fk2 foreign key(mnum) references memberlist(membernum)
--제약조건 : 현재 테이블의 mnum 필드는 memberlist 테이블의 membernum필드값을 참조함