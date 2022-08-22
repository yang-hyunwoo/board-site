package com.board.boardsite.service.travel;


import com.board.boardsite.domain.travel.TravelAgency;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.domain.travel.TravelAgencyReservation;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.travel.TravelAgencyReservationDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.travel.TravelAgencyListRepository;
import com.board.boardsite.repository.travel.TravelAgencyRepository;
import com.board.boardsite.repository.travel.TravelAgencyReservationRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TravelAgencyReservationService {

    private final TravelAgencyReservationRepository travelAgencyReservationRepository;

    private final TravelAgencyListRepository travelAgencyListRepository;

    private final TravelAgencyRepository travelAgencyRepository;

    private final TripUserRepository tripUserRepository;
    @Transactional()
    public Boolean travelAgencyReserSave(TravelAgencyReservationDto dto) {

            TripUser tripUser = tripUserRepository.findById(dto.tripUser().id()).orElseThrow(()->new BoardSiteException(ErrorCode.USER_NOT_FOUND));
            TravelAgency travelAgency = travelAgencyRepository.findByIdAndDeleted(dto.travelAgencyId(),false).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_NOT_FOUND));
            TravelAgencyList travelAgencyList = travelAgencyListRepository.findByIdAndDeleted(dto.travelAgencyListId(),false).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_LIST_NOT_FOUND));
//            travelAgencyList.personCount(travelAgencyList.getReadCount(),dto.personCount());
            travelAgencyReservationRepository.save(dto.toEntity(travelAgency,travelAgencyList,tripUser));

            TravelAgencyReservation travelAgencyReservation =travelAgencyReservationRepository.findByImpUidAndMerchantUid(dto.impUid(),dto.merchantUid()).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_PAY_NOT_FOUND));
            TravelAgencyReservation updTravelAgencyReservation = travelAgencyReservationRepository.findById(travelAgencyReservation.getId()).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_PAY_NOT_FOUND));
                int userPaid   = updTravelAgencyReservation.getPaid();
                int userCount  = updTravelAgencyReservation.getPersonCount();
                int travelPaid = travelAgencyList.getSalePaid();
            if(userPaid == (userCount*travelPaid)){
                updTravelAgencyReservation.setDeleted(false);
            } else {
                updTravelAgencyReservation.setFailReason("스크립트 결제 금액 변조");
                updTravelAgencyReservation.setDeleted(true);
            }
            return updTravelAgencyReservation.isDeleted();

    }


}
