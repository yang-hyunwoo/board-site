package com.board.boardsite.listener;


import com.board.boardsite.domain.TravelAgency;
import com.board.boardsite.domain.TripUser;
import com.board.boardsite.domain.TripUserHistory;
import com.board.boardsite.repository.TripUserHistoryRepository;
import com.board.boardsite.support.BeanUtils;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

@Component
public class TripUserEntityListener {

    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o){
        TripUserHistoryRepository tripUserHistoryRepository = BeanUtils.getBean(TripUserHistoryRepository.class);
        TripUser user = (TripUser) o;
        TripUserHistory tripUserHistory = TripUserHistory.of(user.getId(),
                                                            user.getName(),
                                                            user.getNickName(),
                                                            user.getEmail(),
                                                            user.getPassword(),
                                                            user.getPoint(),
                                                            user.getGender()
                                                            );

        tripUserHistoryRepository.save(tripUserHistory);
    }
}
