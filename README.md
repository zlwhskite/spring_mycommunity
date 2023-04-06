# spring_mycommunity
스프링으로 만든 커뮤니티사이트


## 디렉토리구조
![image](https://user-images.githubusercontent.com/80504740/230248059-d99c68df-08a0-419b-bf31-15dc59bd6978.png)


## Process Flowchart


## ERD
![image](https://user-images.githubusercontent.com/80504740/230013920-6d85e0f0-d531-48f0-b366-5c45039f6a96.png)


## 기술 스택
- Java 11
- Springboot 2.7.7
- Thymeleaf
- 부트스트랩5
- 제이쿼리
- MyBatis(H2)
- chart.js
- Gradle


## 구현 기능
- 게시글CRUD(summernote)
- 댓글, 대댓글
- 페이징
- 검색
- 정렬
- 출석체크
- Admin(공지사항, AdminPage)
- 즐겨찾기
- 회원가입(sha256)
- 조회 수

### Home
![image](https://user-images.githubusercontent.com/80504740/229420084-ddd8ed53-f6d3-4978-bada-eb822bc03949.png)


### Boards
![image](https://user-images.githubusercontent.com/80504740/229420135-cbb033bb-46ee-4641-b9aa-84ede1d3e670.png)
![image](https://user-images.githubusercontent.com/80504740/230247254-91b1c9b1-d31a-4324-9422-373f0ac52cdf.png)


### Sign up
![image](https://user-images.githubusercontent.com/80504740/229420174-4bf3f904-9494-4ba8-8b38-05f13262ec83.png)


### Search
![image](https://user-images.githubusercontent.com/80504740/229420234-6b7cb8c7-7209-4a05-9468-db43d0869d20.png)


### Comments
![image](https://user-images.githubusercontent.com/80504740/229420427-3faa9ada-5fe3-4b22-85be-b9589a8149f0.png)


### Admin Page
![image](https://user-images.githubusercontent.com/80504740/229420481-76e6b448-0d2c-4c72-9b86-65f6a12e5e86.png)


### User Info
![image](https://user-images.githubusercontent.com/80504740/229693389-8324661a-e4ec-43b4-9415-0426f16b7c32.png)


## 세부기능

#### 회원
- 닉네임, 비밀번호, 이메일, 성별입력
- 닉네임 중복 체크(Ajax)
- 비밀번호, 비밀번호확인칸 일치 여부 확인
- 비밀번호 저장할 때 SHA256으로 변환하여 DB저장
- 이메일로 닉네임 찾기, 이메일과 닉네임으로 비밀번호 찾기


#### 로그인
- 닉네임, 비밀번호 로그인
- 세션사용


#### 회원탈퇴
- 회원 탈퇴 시 회원이 생성한 게시글, 댓글, 즐겨찾기 삭제


#### 게시글
- 작성한 회원만 수정, 삭제 가능
- 로그인 회원만 글 작성 가능
- 다중 이미지 첨부 가능(Summernote)
- 썸네일 표시(이미지가 없을 때 대체 이미지로 표시), 댓글 수 확인
- 조회수가 일정 횟수 넘으면 HOT뱃지가 생김
- 로그인 시 즐겨찾기 버튼이 보임
- TOP버튼
- 정렬(최신순, 인기순(조회))
- 페이징


#### 댓글
- 로그인 한 회원만 댓글 작성 가능
- 댓글에 댓글작성 가능(깊이 1까지 허용)
- 댓글 작성, 수정 삭제 시 모달창안내 후 본인이 작성된 댓글로 이동
- 댓글 삭제 시 사라지는게 아니라 '삭제된 댓글입니다'라고 표시


#### 즐겨찾기
- 로그인 한 회원만 즐겨찾기 가능
- 공지사항은 즐겨찾기 
- Ajax, 모달창으로 즐겨찾기 확인 안내
- 유저정보화면에서 즐겨찾기 목록 확인 가능


#### 검색
- 주제별 검색 가능
- 닉네임 클릭 시 작성자가 쓴 글 검색가능
- 검색내용이 없을 시 모달창 안내


#### 관리자
- 공지사항만 작성 가능
- 주제별 게시글 현황 차트로 확인 가능(chart.js)
- 총 회원 수, 게시글 확인 가능
- 오늘 가입한 회원목록 확인 가능
- 오늘 작성한 게시글 확인 가능
- 관리자는 모든 게시글 수정, 삭제 가능


#### 출석체크
- 로그인 시 모달창으로 출석체크 일수 안내
- 유저정보에서 출석일 수 확인 가능 
