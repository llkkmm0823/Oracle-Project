-- SQL파일에서 주석문은 "--" 표시하고 해당 내용을 기술함

--Table : 데이터베이스에서 사용되는 데이터 집합의 단위
-- 열 : *Field* , 속성, 애트리뷰트
-- 행 : *Record*, 튜플

-- 위와 같이 표 형식의 데이터베이스를 관계형 데이터베이스 라 지칭

-- 현재 출시되어 데이터 베이스로 활용되는 제품들 : 오라클, MySQL , MSSQL, MariaDB, MongoDB, Access등
-- 데이터베이스의 조작운영을 가능하게 하는 프로그램 : DBMS(DataBase Management System)
-- 각 데이터베이스 제품들에는 자신의 데이터베이스를 관리할 수 있는 DBMS가 존재
-- 오라클데이터 베이스에서 사용가능한 DBMS : SQL Developer, SQL PLUS, 이클립스 등

-- 데이터베이스의 활용 분야 : 일반웹사이트 게시판의 게시물 저장용, 포털검색사이트 검색 대상 정보 저장용, 
-- RPG 게임의 게임정보 또는 캐릭터 정보, 회원들의 회원정보 등

-- 데이터베이스에 활용되는 언어(Language)
-- SQL(Structured Query Language) : 관계형 데이터베이스 관리 시스템(RDBMS)의 데이터를 관리하기 위해 설계된 특수 목적의 프로그래밍 언어

-- SQL의 세 가지 종류
-- 1. DDL(Database Definition Language) - create user ~
-- 2. DML(Database Management Language)
-- 3. DCL (Database Control Language) grant dba to ~


-- DDL(Database Definition Language) _ 데이터정의어
-- 명령의 예 : Create , Alter, Drop
-- Create : 테이블, 뷰 , 사용자 등을 생성할 때 사용하는 명령
-- Alter : 이미 생성되어 있는 테이블 또는 뷰, 사용자 등의 구조를 수정하기 위한 명령
-- Drop : 이미 생성되어 있는 테이블 또는 뷰, 사용자 등을 삭제하기 위한 명령

-- DML(Database Management Language)_ 데이터조작어(레코드 단위의 처리)
-- 명령의 예 : Insert, Update, Delete, Select
-- Insert : 테이블에 레코드를 추가하기 위한 명령
-- Update : 테이블에 있는 레코드 중 일부 또는 전부를 수정하기 위한 명령
-- Delete : 테이블에 있는 레코드 중 일부 또는 전부를 삭제하기 위한 명령
-- Select : 테이블에 있는 레코드 중 일부 또는 전부를 조회(검색)하여 열람하기 위한 명령

-- DCL (Database Control Language)
-- 명령의 예 : Grant, Revoke
-- Grant : 특정 사용자에게 권한을 설정
-- Revoke : 특정 사용자에게 권한을 해제

-- 기본키(Primary Key) : 테이블을 구성하는 필드들 중에서 갖는 값들이 빈칸(null)이 없고 서로 다른 값들을 갖고 있어 레코드들을 유일하게 구분해 낼 수 있는 필드
-- 								테이블을 구성하는 필드들 중 자격이 되는 필드 중 하나에 부여하는 테이블의 대표값
-- 기본키를 지정함으로써 기본키가 지정하지 않았을 때 발생할 수 있는 오류들을 미연에 방지할 수 있음
-- 개체 무결성 : 기본키로 결함을 없애는 것 그래서 결함없이 유지되는 것

-- 외래키(Foreign Key) : 테이블 간의 필수 포함관계에 있어 상대 테이블의 특정 필드값을 참조하면서 없는 값을 사용하지 않는 필드
-- 참조무결성 : 외래키로 유지되는 무결성
