--05_DDL_Sequence.sql

-- *오라클 - 시퀀스(sequence)
--				: 테이블 내의 유일한 숫자를 자동으로 생성하는 자동 번호 발생기
--				: 테이블 생성 후 시퀀스(일련번호)를 따로 만들어야 함

-- 생성 방법
-- create sequence 시퀀스 이름 start with 시작 숫자 increment by 증가량 ;

--주로 number 형식에 기본키값으로 사용
--일련번호정도로 이해해도 무방
--number (자리수) : 자료형의 자리수가 몇자리냐에 따라 그 만큼 숫자가 증가할 수 있음

--[1] 시퀀스의 생성
create sequence book_seq start with 1 increment by 1;

--테이블에 레코드를 추가
insert into booklist values(book_seq.nextVal,'일곱해의 마지막',2020,12150,2000,'all');
insert into booklist values(book_seq.nextVal,'봉제인형 살인사건',2019,13150,2000,'18');
insert into booklist values(book_seq.nextVal,'쇼코의 미소',2023,13420,2000,'12');

select*from booklist;

--[3] 시퀀스 수정 : 최대 증가값을 14까지로 제한
alter sequence book_seq maxvalue 14;
--[4] 시퀀스 삭제 :
drop sequence book-seq;
--[5] 시퀀스 재생성 : 다음 숫자부터 시작하게 하여 기존 레코드와 중복되지 않게 함
create sequence book_seq start with 4 increment by 1;

--시퀀스 명령은 오류가 나더라도 이미 카운팅이 되어 지나감. 하지만 의미 있는 숫자, 
-- 즉 그저 부여해주는 숫자일 뿐 무의미하기 때문에 다른 것과 중복만 되지 않았다면 꼭 차례대로 기입될 필요 없으니 오류가 나서 빵꾸가 나도 강박가지지말자


--*현재 로그인한 사용자가 만들거나 보유하고 있는 테이블의 목록을 조회
select*from tabs;

--1부터 1씩 늘어나는 member_seq  rent_seq를 생성
create sequence member_seq start with 1 increment by 1;
create sequence rent_seq start with 1 increment by 1;





