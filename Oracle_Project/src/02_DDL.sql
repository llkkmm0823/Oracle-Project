-- DDL(Data Definition Language)데이터 정의어

-- 테이블의 생성(Create)
-- Create Table 테이블 이름(
--			필드명1 DATATYPE [DEFAULT값 or 제약조건 및 형식],
-- 			필드명2 DATATYPE [DEFAULT값 or 제약조건 및 형식],
--			필드명3 DATATYPE [DEFAULT값 or 제약조건 및 형식],
--			...
--	);

--Create Table 명령의 세부 규칙
-- 테이블의 이름은 객체를 의미할 수 있는 적절한 이름을 사용함(자바변수 이름규칙과 거의 동일)
-- 다른 테이블과 중복되지 않게 테이블 이름을 지정함
-- 한 테이블 내에서 필드이름도 중복되지 않게 함
-- 각 필드들은 ","로 구분하여 생성
-- create를 비롯한 모든 sql명령은 ";"로 끝남
-- 필드명 뒤에 DATATYPE은 반드시 지정하고 []안에 내용은 해당내용이 있을 때 작성하며 생략가능
-- 테이블 명과 필드명은 반드시 문자로 시작해야하고, 예약어 명령어 등을 테이블명과 필드명으로 쓸 수 없음
-- 테이블 생성 시 대/소문자 구분은 하지 않음(기본적으로 테이블이란 컬럼명은 대문자로 만들어짐)
-- DATE데이터 형식은 유형의 별도 크기를 지정하지 않음
-- 문자데이터의 DataType->varchar(10),number(4) // 입력이 가능한 수를 나타냄
-- 문자데이터 유형은 반드시 가질 수 잇는 최대 길이를 표시
-- 컬럼과 컬럼의 구분은 콤마로 하되, 마지막 컬럼은 콤마를 찍지 않음

--도서대여점의 도서목록 테이블 생성
-- 필드 : booknum , subject , makeyear, inprice, outprice
-- 자료형 : booknum(문자 5자리),  subject(문자 30) , makeyear(숫자4), inprice(숫자6), outprice(숫자4)
-- 제약 조건 : booknum(Not null),subject(Not null), makeyear(), inprice(), outprice()
-- 기본키 : booknum

create table booklist(
	booknum varchar2(5) not null,
	subject varchar2(30) not null,
	makeyear number(4),
	inprice number(6),
	outprice number(4),
	
	--필드수준의 제약 : 필드명 옆에 현재 필드에만 적용하는 제약조건
	-- 아래는 테이블 수준의 제약조건임
	constraint booknum_pk primary key (booknum)
	--constraint : 테이블 수준의 제약조건을 지정하는 키워드
	--booklist_pk : 테이블 외부에서 현재 제약조건을 컨트롤하기 위한 제약조건의 고유명
	--primary key(booknum) : 기본키로 booknum을 지정하겠다는 뜻
);

select * from BOOKLIST; --테이블의 내용 전체를 조회하는 명령

-- 제약조건(CONSTRAINT)
-- PRIMARY KEY
-- 테이블에 저장된 레코드를 고유하게 싣별하기 위한 키, 하나의 테이블에 하나의 기본키만 정의할 수 있음
-- 여러 필드가 조합된 기본키 생성 가능
-- 기본키는 중복된 값을 갖을 수 없으며 빈칸도 있을 수 없음
-- PRIMARY KEY = UNIQUE KEY + NOT NULL

-- UNIQUE KEY
-- 테이블에 저장된 행 데이터를 고유하게 식별하기 위한 고유키를 정의함
-- 단 NULL은 고유키 제약의 대상이 아니므로, NULL값을 가진 행이 여러개가 UNIQUE KEY제약에 위반하지는 않음

-- NOT NULL
-- 비어있는 상태, 아무것도 없는 상태를 허용하지 않음 (입력필수)

-- CHECK
-- 입력할 수 잇는 값의 범위를 제한
-- CHECK제약으로는 TRUE OR FALSE로 평가할 수 있는 논리식을 지정함

-- FOREIGN KEY
-- 관계형 데이터 베이스에서 테이블간에 관계를 정의하기 위해 기본키를 다른 테이블의 외래키로 복사하는 경우 외래키가 생성됨 _참조 무결성 제약옵션 생성


--테이블 생성2
--테이블 이름 : MemberList(회원리스트)
--필드 : memberNum , memberName, Phone, Birth, Bpoint
--데이터 형식 : memberNum : VARCHAR2(5) , memberName : VARCHAR2(12), Phone : VARCHAR2(13), 
-- 					Birth: DATE, Bpoint : NUMBER(6)
--제약조건 : memberNum , memberName, Phone 세 개 필드 NOT NULL-필드레벨로 설정
--				memberNum : PRIMARY KEY - 테이블 레벨로 설정


drop table memberlist purge;--테이블 삭제 명령

create table memberlist(
	membernum varchar2(5) not null,
	membername varchar2(12) not null,
	phone varchar2(13) not null,
	birth date,
	bpoint number(6) default 100,
	--default : 나중에 따로 굳이 추가하지 않아도 디폴트 값으로 100이 추가됨

constraint membernum_pk primary key (membernum)
);

select * from memberlist;

--테이블 생성3
--테이블 이름 : rentlist
--필드 : rent_date(date), indexk(number(3)),booknum(varchar2(5)),memberNum(varchar2(5)),discount(number(4))
--제약조건 : membernum booknum : NOT NULL
--	기본 값 : rent_date

create table rentlist(
rent_date date default sysdate, --대여날짜
--default sysdate : 시스템에서 알아서 오늘날짜를 디폴트로 넣어주는 키워드
indexk number(3), -- 대여순번 : 1부터 늘어나는 숫자이고 하루가 지나면 reset
bnum varchar2(5) not null, --대여한 도서번호
mnum varchar2(5) not null, -- 대여한 회원의 회원번호
discount number(4)default 500, -- 할인금액

constraint rent_date_pk primary key (rent_date,indexk),
--제약조건 : 조합되었을 때 중복된 값이 나오지 않을만한 값을 조합하여 프라이머리키로 설정 
-- 슈퍼키 : 두개의 필드가 조합되어 기본키로 지정될 수 있음
constraint fk1 foreign key(bnum) references booklist(booknum),
--제약조건 : 현재 테이블의 bnum 필드는 booklist 테이블의 booknum필드값을 참조함
constraint fk2 foreign key(mnum) references memberlist(membernum)
--제약조건 : 현재 테이블의 mnum 필드는 memberlist 테이블의 membernum필드값을 참조함
);
select * from rentlist;
