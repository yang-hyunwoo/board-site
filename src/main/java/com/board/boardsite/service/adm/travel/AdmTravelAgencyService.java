package com.board.boardsite.service.adm.travel;

import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.travel.TravelAgencyDto;
import com.board.boardsite.dto.travel.TravelAgencyOnlyListDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.adm.travel.AdmTravelAgencyListRepository;
import com.board.boardsite.repository.adm.travel.AdmTravelAgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdmTravelAgencyService {

    private final AdmTravelAgencyRepository admTravelAgencyRepository;

    private final AdmTravelAgencyListRepository admTravelAgencyListRepository;


    @Transactional(readOnly = true)
    public Page<TravelAgencyOnlyListDto> admTravelAgencyList(String travelAgencyName , String role , Long traveAgencyId, Pageable pageable) {
        if(role.equals("SUPER")) {
            if (travelAgencyName == null || travelAgencyName.isBlank()) {
                return admTravelAgencyRepository.findCustomAll(pageable);
            }
            return admTravelAgencyRepository.findCustomByNameContaining(travelAgencyName, pageable);
        } else {
            if (travelAgencyName == null || travelAgencyName.isBlank()) {
                return admTravelAgencyRepository.findCustomAllDivide(traveAgencyId , pageable);
            }
            return admTravelAgencyRepository.findCustomByNameContainingDivide(traveAgencyId,travelAgencyName, pageable);

        }
    }

    @Transactional
    public void deleteAgency(Long travelAgencyId , String role) {
        Long count = admTravelAgencyListRepository.countByTravelAgencyIdAndDeleted(travelAgencyId,false);
        if(count >0) {
            throw new BoardSiteException(ErrorCode.NOT_DELETE_TRAVEL_AGENCY);
        }
        TravelAgency travelAgency = admTravelAgencyRepository.findByIdAndDeleted(travelAgencyId,false).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_NOT_FOUND));
        if (role.equals("SUPER")) {
            travelAgency.setDeleted(true);
        } else{
            throw new BoardSiteException(ErrorCode.ARTICLE_UNAUTHORIZED);
        }
    }

    @Transactional
    public void reDeleteArticleComment(Long travelAgencyId , String role) {
        TravelAgency travelAgency = admTravelAgencyRepository.findByIdAndDeleted(travelAgencyId,true).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_NOT_FOUND));
        if (role.equals("SUPER")) {
            travelAgency.setDeleted(false);
        } else{
            throw new BoardSiteException(ErrorCode.ARTICLE_UNAUTHORIZED);
        }
    }

    @Transactional(readOnly = true)
    public TravelAgencyOnlyListDto travelAgencyDetail(Long travelAgencyId, TripUserPrincipal tripUserPrincipal) {
        if(tripUserPrincipal.role().equals("SUPER")){
            return admTravelAgencyRepository.findCustomDetail(travelAgencyId).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_NOT_FOUND));
        } else {
            if(tripUserPrincipal.travelAgencyId()!=travelAgencyId){
                throw new BoardSiteException(ErrorCode.NOT_PERMITTION);
            }
            return admTravelAgencyRepository.findCustomDetail(travelAgencyId).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_NOT_FOUND));

        }
    }

    @Transactional
    public void saveAgency(TravelAgencyDto dto){
        admTravelAgencyRepository.findByName(dto.name().trim()).ifPresent(it -> {
            throw new BoardSiteException(ErrorCode.DUPLICATED_NAME, String.format("%s is duplicated", dto.name()));
        });
        admTravelAgencyRepository.save(dto.toEntity());
    }


    @Transactional
    public void updateAgency(Long agencyId ,String role , TravelAgencyDto dto){

        TravelAgency travelAgency = admTravelAgencyRepository.findById(agencyId).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_NOT_FOUND));
       if(role.equals("SUPER")){
           travelAgency.setName(dto.name());
       }
        travelAgency.setAddress(dto.address());
        travelAgency.setComment(dto.comment());
        travelAgency.setTel(dto.tel());
        travelAgency.setFileId(dto.fileId());
        travelAgency.setDetail(dto.detail());

    }
}
