package com.board.boardsite.repository.querydsl.tour;

import com.board.boardsite.domain.tour.Tour;
import java.util.List;

public interface TourCustomRepository {

    List<Tour> findTourRandomCount(int count);
}
