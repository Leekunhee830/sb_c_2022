#DB생성
DROP DATABASE IF EXISTS sb_c_2021_2nd;
CREATE DATABASE sb_c_2021_2nd;
USE sb_c_2021_2nd;

#게시물 테이블 생성
CREATE TABLE article(
   id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
   regDate DATETIME NOT NULL,
   updateDate DATETIME NOT NULL,
   title CHAR(100) NOT NULL,
   `body` TEXT NOT NULL
);

#회원 테이블 생성
CREATE TABLE `member`(
   id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
   regDate DATETIME NOT NULL,
   updateDate DATETIME NOT NULL,
   loginId CHAR(20) NOT NULL,
   loginPw CHAR(60) NOT NULL,
   authLevel SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '(3=일반,7=관리자)',
   `name` CHAR(20) NOT NULL,
   nickname CHAR(20) NOT NULL,
   cellphoneNo CHAR(20) NOT NULL,
   email CHAR(50) NOT NULL,
   delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부(0=탈퇴전,1=탈퇴)',
   delDate DATETIME COMMENT '탈퇴날짜'
);

#게시물, 테스트 데이터 생성
INSERT INTO article
SET regDate=NOW(),
updateDate=NOW(),
title='제목1',
`body`='내용1';

INSERT INTO article
SET regDate=NOW(),
updateDate=NOW(),
title='제목2',
`body`='내용2';

INSERT INTO article
SET regDate=NOW(),
updateDate=NOW(),
title='제목3',
`body`='내용3';

#회원 테스트 데이터
INSERT INTO `member`
SET regDate=NOW(),
updateDate=NOW(),
loginId='admin',
loginPw='admin',
authLevel=7,
`name`='관리자',
nickname ='관리자',
cellphoneNo ='01011111111',
email='email@naver.com';

INSERT INTO `member`
SET regDate=NOW(),
updateDate=NOW(),
loginId='user1',
loginPw='user1',
`name`='사용자1',
nickname ='사용자1',
cellphoneNo ='01011111111',
email='email@naver.com';

INSERT INTO `member`
SET regDate=NOW(),
updateDate=NOW(),
loginId='user2',
loginPw='user2',
`name`='사용자2',
nickname ='사용자2',
cellphoneNo ='01011111111',
email='email@naver.com';

#게시물 테이블에 회원정보 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER `updateDate`; 

#기존 게시물의 작성자를 2번으로 지정
UPDATE article 
SET memberId=2
WHERE memberId = 0;
