# spring_mycommunity
스프링으로 만든 커뮤니티사이트


## 디렉토리구조
![image](https://user-images.githubusercontent.com/80504740/230248059-d99c68df-08a0-419b-bf31-15dc59bd6978.png)


## Process Flowchart


## ERD
![image](https://user-images.githubusercontent.com/80504740/231321324-277324bd-de2c-4ed4-9c6e-00cf66711c99.png)


## 기술 스택
- Java 11
- Springboot 2.7.7
- Thymeleaf
- bootstrap5
- jquery
- MyBatis(H2)
- chart.js
- Gradle


## 구현 기능
- 게시글CRUD(summernote)
- 댓글, 대댓글(markdown적용)
- 페이징
- 검색
- 정렬(최신순, 인기순)
- 출석체크
- Admin(공지사항작성가능, AdminPage)
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
![image](https://github.com/zlwhskite/spring_mycommunity/assets/80504740/16ab341a-bdbf-4ae0-861b-f19e9b7e8c6e)



### User Info
![image](https://user-images.githubusercontent.com/80504740/229693389-8324661a-e4ec-43b4-9415-0426f16b7c32.png)


## 세부기능

#### 회원
- 닉네임, 비밀번호, 이메일, 성별입력
- 닉네임 중복 체크(Ajax)
- 비밀번호, 비밀번호확인칸 일치 여부 확인
- 비밀번호 저장할 때 해시화(SHA256)으로 변환하여 DB저장
- 이메일로 닉네임 찾기, 이메일과 닉네임으로 비밀번호 찾기


#### 로그인
- 닉네임, 비밀번호 로그인
- 세션사용


#### 회원탈퇴
- 회원 탈퇴 시 회원이 생성한 게시글, 댓글, 즐겨찾기 삭제


#### 게시글
- 게시글 하단에 게시글 리스트 표지(hot카테고리는 표시안함) 
- 작성한 회원만 수정, 삭제 가능
- 로그인 회원만 글 작성 가능
- 다중 이미지 첨부 가능(Summernote)
- 썸네일 표시(이미지가 없을 때 대체 이미지로 표시)
- 댓글 수 확인
- 조회수가 일정 횟수 넘으면 HOT뱃지가 생김
- 로그인 시 즐겨찾기 버튼이 보임
- TOP버튼
- 정렬(최신순, 인기순(조회))
- 페이징


#### 댓글
- 로그인 한 회원만 댓글 작성 가능
- markdown문법으로 댓글 작성 가능
- 댓글에 댓글작성 가능(깊이 1까지 허용)
- 댓글 작성, 수정 삭제 시 모달창안내 후 본인이 작성된 댓글로 이동
- 댓글 삭제 시 사라지는게 아니라 '삭제된 댓글입니다'라고 표시


#### 즐겨찾기
- 로그인 한 회원만 즐겨찾기 가능
- 공지사항은 즐겨찾기 불가능 
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


## 후기
  - 코드가 뒤죽박죽이라서 효율적으로 함수등 사용했으면 좋았을꺼같다.(클린코드)
  - 관리자가 게시판들의 이름, 순서등을 바꿀 수 있는 기능을 추가했으면 좋았을꺼같다. 테이블도 확장성있게 설계했으면 이 기능들을 추가하기 쉬웠을꺼같다.
  - Ajax, 레이아웃등 프론트쪽관련이 어려웠다. 특히 Ajax개념은 어렵지 않았는데 핸들링 어려워 많이 연습을 해야할꺼같다.
  - 로그인기능구현시 OAuth로 구현했으면 더 깔끔하고 편하게 만들수있었을꺼같다. 직접만들면 보안, 비밀번호찾기, 회원정보변경등 구현해야할 것들이 많아져
    퀄리티가 떨어지게구현한거같다.
  - 페이징기능을 처음 만들어봐서 작동원리가 이해가 안되서 2~3일정도 고생했다. 정렬과 같이 사용 시 어령 
  - 스프링시큐리티를 적용해서 만들어보기
  - 댓글의 답글 구현시 깊이 설정을 해서 댓글의 댓글, 댓글의 댓글의 댓글등 무한히 할 수 있는데 깊이설정을 깊게하면 이미지가 안좋기 떄문에 적절한 설정이 중요하다
  - chart.js 시각적 차트표시에 유용하게 사용이 가능해 더 공부를 하기.
  - 이미지파일들을 프로젝트내부에 저장하지않고 로컬(외부)에 저장했는데 처음에 찾치를 못해 알아보니 외부 리소스 허용을 따로 해줘야하는걸 알았다.(addResourceHandlers)
