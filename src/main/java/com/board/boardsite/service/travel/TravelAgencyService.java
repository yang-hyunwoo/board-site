package com.board.boardsite.service.travel;

import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.travel.TravelAgencyDto;
import com.board.boardsite.repository.travel.TravelAgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TravelAgencyService {

    private final TravelAgencyRepository travelAgencyRepository;

    //travelAgencyRepository.findByNameContaining(travelAgencyName , pageable) : travelAgency (entity) 여서 .map을 통해 dto에 담아준다.
    @Transactional(readOnly = true)
    public Page<TravelAgencyDto> travelAgencyList(String travelAgencyName , Pageable pageable) {

        if (travelAgencyName == null || travelAgencyName.isBlank()) {
            return travelAgencyRepository.findAll(pageable).map(TravelAgencyDto::from);
        }
        return travelAgencyRepository.findByNameContaining(travelAgencyName , pageable).map(TravelAgencyDto::from);
    }


}
