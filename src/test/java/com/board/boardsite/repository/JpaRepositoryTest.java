package com.board.boardsite.repository;

import com.board.boardsite.config.JpaConfig;
import com.board.boardsite.domain.*;
import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.repository.user.TripUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final TripUserRepository tripUserRepository;
    private final TravelAgencyRepository travelAgencyRepository;
    private final TravelAgencyListRepository travelAgencyListRepository;

    private final TravelAgencyReservationListRepository travelAgencyReservationListRepository;


    public JpaRepositoryTest(@Autowired  ArticleRepository articleRepository,
                             @Autowired  ArticleCommentRepository articleCommentRepository,
                             @Autowired  TripUserRepository tripUserRepository,
                             @Autowired  TravelAgencyRepository travelAgencyRepository,
                             @Autowired  TravelAgencyListRepository travelAgencyListRepository,
                             @Autowired TravelAgencyReservationListRepository travelAgencyReservationListRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
        this.tripUserRepository = tripUserRepository;
        this.travelAgencyRepository = travelAgencyRepository;
        this.travelAgencyListRepository = travelAgencyListRepository;
        this.travelAgencyReservationListRepository = travelAgencyReservationListRepository;
    }

    @DisplayName("1. select Article 테스트")
    @Test
    void givenTestData_whenArticleSelecting_thenWorksFine() {
        // Given

        // When
        List<Article> articles = articleRepository.findAll();

        // Then
        assertThat(articles)
                .isNotNull()
                .hasSize(50);
    }

    @DisplayName("2. insert Article 테스트")
    @Test
    void givenTestData_whenArticleInsert_thenWorksFine() {
        // Given
        long perviousCount = articleRepository.count();
        var tripUser = tripUserRepository.findById(1L);      //DB 바로 액세스해서 데이터 가져옴
        var tripUser2 = tripUserRepository.getReferenceById(5L);    //실제 테이블 조회하는 대신 프록시 객체만 가져옴
        // When
        articleRepository.save(Article.of("asdfsadf","vvvvvvvvvv","Y",tripUser2));


        // Then
        assertThat(articleRepository.count()).isEqualTo(perviousCount+1);
    }

    @DisplayName("3. update Article 테스트")
    @Test
    void givenTestData_whenArticleUpdate_thenWorksFine() {
        // Given

        // When
        var article = articleRepository.findById(1L).orElseThrow();
        String updTitle = "ccccccc";
        article.setTitle(updTitle);
        Article updArticle = articleRepository.save(article);
        System.out.println(updArticle.getUseYn());
        // Then
        assertThat(updArticle).hasFieldOrPropertyWithValue("title",updTitle);
    }

    @DisplayName("4. select ArticleComment 테스트")
    @Test
    void givenTestData_whenArticleCommentSelecting_thenWorksFine() {
        // Given

        // When
        List<ArticleComment> articleComments = articleCommentRepository.findAll();

        // Then
        assertThat(articleComments)
                .isNotNull()
                .hasSize(1);
    }

    @DisplayName("5. insert ArticleComment 테스트")
    @Test
    void givenTestData_whenArticleCommentInsert_thenWorksFine() {
        // Given
        long perviousCount = articleCommentRepository.count();
        var tripUser  = tripUserRepository.findById(1L);
        var article = articleRepository.findById(1L);
        // When
        articleCommentRepository.save(ArticleComment.of(article.get(),"아이밀","Y",tripUser.get()));

        // Then
        assertThat(articleCommentRepository.count()).isEqualTo(perviousCount+1);
    }
        //TODO: 조인이 여러개 되는거 같음 확인 요망
    @DisplayName("6. update ArticleComment 테스트")
    @Test
    void givenTestData_whenArticleCommentUpdate_thenWorksFine() {
        // Given
        // When
        var articleComment = articleCommentRepository.findById(1L).orElseThrow();
        String Updcontent = "ccccccc";
        articleComment.setContent(Updcontent);
        ArticleComment updArticleComment = articleCommentRepository.saveAndFlush(articleComment);
        // Then
        assertThat(updArticleComment).hasFieldOrPropertyWithValue("content",Updcontent);
    }


    @DisplayName("7. select TravelAgency 테스트")
    @Test
    void givenTestData_whenTravelAgencySelecting_thenWorksFine() {
        // Given

        // When
        List<TravelAgency> travelAgencyList = travelAgencyRepository.findAll();

        // Then
        assertThat(travelAgencyList)
                .isNotNull()
                .hasSize(1);
    }

    @DisplayName("8. insert TravelAgency 테스트")
    @Test
    void givenTestData_whenTravelAgencyInserting_thenWorksFine() {
        // Given
        long perviousCount = travelAgencyRepository.count();
        // When
        travelAgencyRepository.save(TravelAgency.of("gus","1234","1234","12354","Y"));

        // Then
        assertThat(travelAgencyRepository.count()).isEqualTo(perviousCount+1);
    }

    @DisplayName("9. update TravelAgency 테스트")
    @Test
    void givenTestData_whenTravelAgencyUpdate_thenWorksFine() {
        // Given
        // When
        var travelAgency = travelAgencyRepository.findById(1L).orElseThrow();
        String UpdName = "dn";
        travelAgency.setName(UpdName);
        TravelAgency updTravelAgency = travelAgencyRepository.saveAndFlush(travelAgency);
        // Then
        assertThat(updTravelAgency).hasFieldOrPropertyWithValue("name",UpdName);
    }


    @DisplayName("10. select TravelAgencyList 테스트")
    @Test
    void givenTestData_whenTravelAgencyListSelecting_thenWorksFine() {
        // Given

        // When
        List<TravelAgencyList> travelAgencyLists = travelAgencyListRepository.findAll();

        // Then
        assertThat(travelAgencyLists)
                .isNotNull()
                .hasSize(1);
    }

    @DisplayName("11. insert TravelAgencyList 테스트")
    @Test
    void givenTestData_whenTravelAgencyListInserting_thenWorksFine() {
        // Given
        long perviousCount = travelAgencyListRepository.count();
        var travelAgency = travelAgencyRepository.findById(1L).orElseThrow();
        // When
        travelAgencyListRepository.save(TravelAgencyList.of("seoul",
                "20210901",
                "20210901",
                "tet",
                "aaaa",
                0,
                1,
                2,
                3,
                4,
                5,
                6,
                travelAgency));
    // Then
        assertThat(travelAgencyListRepository.count()).isEqualTo(perviousCount+1);
    }

    @DisplayName("12. update TravelAgencyList 테스트")
    @Test
    void givenTestData_whenTravelAgencyListUpdate_thenWorksFine() {
        // Given
        // When
        var travelAgencyList = travelAgencyListRepository.findById(1L).orElseThrow();
        String UpdTitle = "dn";
        travelAgencyList.setTitle(UpdTitle);
        TravelAgencyList updTravelAgencyList = travelAgencyListRepository.saveAndFlush(travelAgencyList);
        // Then
        assertThat(updTravelAgencyList).hasFieldOrPropertyWithValue("title",UpdTitle);
    }

    @DisplayName("13. select TravelAgencyReservation 테스트")
    @Test
    void givenTestData_whenTravelAgencyReservationSelecting_thenWorksFine() {
        // Given

        // When
        List<TravelAgencyReservationList>  travelAgencyReservationLists = travelAgencyReservationListRepository.findAll();

        // Then
        assertThat(travelAgencyReservationLists)
                .isNotNull()
                .hasSize(1);
    }

    @DisplayName("14. insert TravelAgencyReservation 테스트")
    @Test
    void givenTestData_whenTravelAgencyReservationInserting_thenWorksFine() {
        // Given
        long perviousCount = travelAgencyReservationListRepository.count();
        var travelAgency = travelAgencyRepository.findById(1L).orElseThrow();
        var travelAgencyList = travelAgencyListRepository.findById(1L).orElseThrow();
        var tripUser  = tripUserRepository.findById(1L).orElseThrow();
        // When
        travelAgencyReservationListRepository.save(TravelAgencyReservationList.of(1,
                2,
                "Y",
                tripUser,
                travelAgency,
                travelAgencyList));
        // Then
        assertThat(travelAgencyReservationListRepository.count()).isEqualTo(perviousCount+1);
    }

    @DisplayName("15. update TravelAgencyReservation 테스트")
    @Test
    void givenTestData_whenTravelAgencyReservationUpdate_thenWorksFine() {
        // Given
        // When
        var travelAgencyReservationList = travelAgencyReservationListRepository.findById(1L).orElseThrow();
        String UpdUseYn = "Y";
        travelAgencyReservationList.setUseYn(UpdUseYn);
        TravelAgencyReservationList updTravelAgencyReservationList = travelAgencyReservationListRepository.saveAndFlush(travelAgencyReservationList);
        // Then
//        assertThat(updTravelAgencyReservationList).hasFieldOrPropertyWithValue("use_yn",UpdUseYn);
    }

    @DisplayName("16. select TripUser 테스트")
    @Test
    void givenTestData_whenTripUserSelecting_thenWorksFine() {
        // Given

        // When
        List<TripUser>  tripUsers = tripUserRepository.findAll();

        // Then
        assertThat(tripUsers)
                .isNotNull()
                .hasSize(50);
    }

    @DisplayName("17. insert TripUser 테스트")
    @Test
    void givenTestData_whenTripUserInserting_thenWorksFine() {
        // Given
        long perviousCount = tripUserRepository.count();
        // When
        tripUserRepository.save(TripUser.of("gus","123","asdf","1214",0,Gender.M,"Y",false));
        // Then
        assertThat(tripUserRepository.count()).isEqualTo(perviousCount+1);
    }

    @DisplayName("18. update TripUser 테스트")
    @Test
    void givenTestData_whenTripUserUpdate_thenWorksFine() {
        // Given
        // When
        var tripUser = tripUserRepository.findById(1L).orElseThrow();
        String UpdUseYn = "Y";
        tripUser.setUseYn(UpdUseYn);
        TripUser updTravelAgencyReservationList = tripUserRepository.saveAndFlush(tripUser);
        // Then
//        assertThat(updTravelAgencyReservationList).hasFieldOrPropertyWithValue("use_yn",UpdUseYn);
    }


}