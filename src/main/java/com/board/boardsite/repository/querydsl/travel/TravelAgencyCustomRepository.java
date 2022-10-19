package com.board.boardsite.repository.querydsl.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import java.util.List;

public interface TravelAgencyCustomRepository {

    List<TravelAgency> findTravelAgencyRandomCount(int count);
}
