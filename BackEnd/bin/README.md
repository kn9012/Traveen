# SSAFY 관통 Spring Boot : Enjoy Trip

SSAFY 10기 광주 관통프로젝트 5반 11팀 김유나, 김내림

## UseCase

![image.png](./image.png)
![image-1.png](./image-1.png)
![image-2.png](./image-2.png)
![image-3.png](./image-3.png)

## ERD

![image-4.png](./image-4.png)

## 기능 구현 완료

### 필수

1. 회원
- 회원가입
    - 비밀번호 해싱 처리
- 회원 정보 조회
- 회원 정보 수정
    - 회원 정보 및 비밀번호 변경
- 회원 정보 삭제 (탈퇴)
2. 로그인 / 로그아웃 기능
- 비밀번호 해싱 처리

### 추가

3. 여행지 페이지
- 촬영지 별 관광지 불러오기

### 심화

4. 공지사항
- 공지사항 글 등록
- 공지사항 글 목록
- 공지사항 글 조회
- 공지사항 글 수정
- 공지사항 글 삭제
- 공지사항 글 검색
 - 키워드 별 검색


## 목차

- 1. 팀원 소개
- 2. 기능 구현 - 필수 (실행 화면)
- 3. 기능 구현 - 추가 (실행 화면)
- 4. 기능 구현 - 심화 (실행 화면)

## 1. 팀원 소개

> 광주 5반 11팀 팀장 김유나

> 광주 5반 11팀 팀원 김내림

## 2. 기능 구현 - 필수 요구사항

(1) 회원 관리 페이지
- 회원가입
    - 중복된 아이디일 경우 회원가입 불가능
    - 입력한 비밀번호와 비밀번호 확인 일치하면 회원가입 가능

![KakaoTalk_20231105_164252993_02.gif](./KakaoTalk_20231105_164252993_02.gif)

- 회원 정보 조회 및 수정
    - 비밀번호 변경 시 현재 비밀번호 일치해야 변경 가능
- 회원 탈퇴

![KakaoTalk_20231105_164252993_01.gif](./KakaoTalk_20231105_164252993_01.gif)


(2) 로그인 페이지
- 아이디 저장하기
- 비밀번호 해싱처리

![KakaoTalk_20231105_164252993.gif](./KakaoTalk_20231105_164252993.gif)

## 3. 기능 구현 - 추가
(1) 여행지 페이지
- 촬영지별 관광지 불러오기
- 페이지네이션

![여행지.gif](./여행지.gif)

## 4. 기능 구현 - 심화
(1) 공지사항 페이지
- 공지사항 글 등록
- 공지사항 글 목록
- 공지사항 글 조회
- 공지사항 글 수정
- 공지사항 글 삭제
- 공지사항 글 검색
    - 키워드 별 검색

![공지사항__2_](/uploads/96644a527653a28474a853b3ac7ca87d/공지사항__2_.gif)