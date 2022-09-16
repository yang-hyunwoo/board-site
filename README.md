# board-site
# 2022/08/10
여행 관련 프로젝트 도메인 설계 완료

도메인 관련 (추후 추가 ) (어노테이션에 대한 내용을 이해한 상태로 적었기 때문에 사용한 이유가 틀릴 수도 있따.)
@annotation 사용 

1. @Getter : lombok 의 라이브러리를 추가 하여 Getter를 자동으로 생성 해준다.
2. @Setter : lombok 의 라이브러리를 추가 하여 Setter를 자동으로 생성 해준다.
3. @ToString :lombok 의 라이브러리를 추가 하여 toString을 string 형식으로 출력 해준다.
4. @Table : 맵핑할 테이블을 지정해준다. 
   여기서 사용한 이유는 table에 index를 걸기 위해서 사용 하였다.
5. @Entity : JPA가 관리해준다. 
6. @Id : 객체의 PK를 의미 한다.
7. @GeneratedValue : JPA에서 Entity의 Primary Key를 생성해 준다.
  여기서 사용한 전략은 strategy = GenerationType.IDENTITY 이며 DB에서 AUTO_INCREAMENT 하여 PK를 생성을 해준다.
8. @Column :DB 테이블 컬럼을 맵핑한다.
9. @OneToMany : 1:N 관계를 맺기 위해서 사용하였다.
10. @ManyToOne : N:1 관계를 맺기 위해서 사용하였다.
11. @ToString.Exclude :@oneToMany 나 ManyToOne을 사용할때 @ToString을 클래스에 걸어 두게 되면 순환참조가 걸려서 시스템이 죽어 버리기 때문에 이 순환 참조를 제거 하기 위해서 사용하는 것으로 알고 있다.
12. @OrderBy : 정렬을 하기 위해서 사용 하였다.
13. @DateTimeFormat : datetimeformat 을 하기 위해서 사용 하였다.

# 2022/08/11
# 도메인 CRUD 각각 테스트 완료
현재 도메인 CRUD 테스트를 작성하여 정상적으로 된것을 확인 하였슴

# 2022/08/21
프론트 화면 앞 여행사 리스트 중 3개를 랜덤으로 보여 주기 위하여 
random이 되는 줄 알았지만 mysql에서 지원을 해주지 않는다고 하여
MySQLJPATemplates 파일을 생성 하여 querydsl로 생성 하였다.
현재 아임포트를 사용하여 결제 api를 사용해보기 위해 아임포트를 사용하기 위한 작업을 진행중이다.
결제 까지는 괜찮지만 결제 검증이 이해가 안되어서 여기에 나의 플로우를 적어본다.

2022/08/22 할 예정.<br>
1.아임포트 진행 완료 후 로직에 import_uid 와 merchant_uid가 넘어온다.<br>
2.(1)의 값과 진행한 사용자의 정보를 저장하기 위해 서버로 보낸다.<br>
3.저장을 하기 전에 디비에 있는 결제 금액과 비교를 한다.<br>
4.값이 맞다면 디비에 정상적으로 저장을 해준다.<br>
5.결제 값이 다르다면 프론트에서 금액을 수정한것으로 판단 하여 디비에 저장은 하나 error를 내려준다.<br>
6.프론트 단에서는 결제 값이 다르면 alert 창을 띄어 결제는 되었으나 사용자의 부주의로 인한 에러 라고 내려주면 될것이다.<br>


# 2022 08 30
현재 진행 사항<br/>
메인 화면 작업중<br/>
로그인 (완료 [인증 확인])<br/>
회원가입 (회원가입 / 이메일 인증 발송 완료)<br/>
여행사 (여행사 목록 / 상세 조회 완료)<br/>
여행 리스트 (목록 /상세 조회 /아임포트 사용 결제 완료)<br/>
게시판 (목록 / 상세 조회 / 등록 / 수정 / 삭제 / 댓글 등록 /댓글 수정 / 댓글 삭제 ) 완료<br/>
구매 내역( 목록 / 아임포트 결제 취소 완료)<br/>
채팅방 ( stomp 사용 목록 / 입장 / 퇴장 / 채팅방 생성 / 채팅 상세 완료) <br/>
* 처음 접해보는 stomp 소켓 이라 조금 많이 헷갈리고 어려워서 <br/>
  조만간 정리해서 깃에 올릴 예정<br/>
* 추가 예정[이미지 업로드] <br/>
마이 페이지 (조회 / 수정 / 이미지 업로드 ) 완료<br>
내 채팅방 (구현 예정) - 차주 진행 예정<br/>

사용자 페이지 완료일 9월 말<br/>
관리자 페이지 10월 초 진행 예정<br/>

# 2022/08/31
마이페이지 기능 추가 완료<br>
1.회원테이블 이미지 추가 위한 컬럼 생성 profile_id<br>
2.마이페이지에서 비밀번호 수정<br>
3.마이페이지에서 닉네임,휴대폰번호,성별 수정 되게 완료<br>
-TODO: 현재 이메일만 중복을 체크 하는데 나중에 시간 되면 닉네임 체크도 해야 헷갈리지 않을거 같아서 이 부분은 시간 날때 붙여야 겠다.<br>

# 2022/09/16
현재 생각한 기능은 모두 완료가 되었다.<br>
이제 관리자 페이지 작업을 10월 1일부터 작업을 할 예정이다<br>
남은 시간 동안 자잘한 기능 수정 및 개인 공부를 할 예정이다.

