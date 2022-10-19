package com.board.boardsite.service.travel;

import com.board.boardsite.domain.travel.TravelAgencyLike;
import com.board.boardsite.domain.travel.TravelAgencyList;
import com.board.boardsite.dto.travel.TravelAgencyListDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.travel.TravelAgencyListLikeRepository;
import com.board.boardsite.repository.travel.TravelAgencyListRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelAgencyListService {

    private final TravelAgencyListRepository travelAgencyListRepository;

    private final TravelAgencyListLikeRepository travelAgencyListLikeRepository;

    private final TripUserRepository tripUserRepository;

    @Transactional(readOnly = true)
    public Page<TravelAgencyListDto> travelAgencyTripList(String travelAgencyTitleName , Pageable pageable) {

        if(travelAgencyTitleName == null || travelAgencyTitleName.isBlank()) {
            return travelAgencyListRepository.findAllByDeleted(pageable,false).map(TravelAgencyListDto::from);
        }

        return travelAgencyListRepository.findByTitleContainingAndDeleted(travelAgencyTitleName,pageable,false).map(TravelAgencyListDto::from);
    }

    @Transactional(readOnly = true)
    public Page<TravelAgencyListDto> searchTravelAgencyPage(Long travelAgencyId , Pageable pageable) {
        return travelAgencyListRepository.findByTravelAgency_IdAndDeleted(travelAgencyId,pageable,false).map(TravelAgencyListDto::from);
    }

    @Transactional
    public TravelAgencyListDto travelAgencyListDetail(Long travelAgencyListId){
        var travelAgencyListDetail =  travelAgencyListRepository.findByIdAndDeleted(travelAgencyListId,false).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_DETAIL_NOT_FOUND));

        travelAgencyListDetail.readCountPlus(travelAgencyListDetail.getReadCount());
        Optional<TravelAgencyList> optionalTravelAgencyList = Optional.of(travelAgencyListDetail);

        return optionalTravelAgencyList.map(TravelAgencyListDto::from).orElseThrow();
    }
    @Transactional
    public void travelAgencyOperland(Long travelAgencyId , String operland , int count){
        var travelAgencyListDetail = travelAgencyListRepository.findByIdAndDeleted(travelAgencyId,false).orElseThrow(()->new BoardSiteException(ErrorCode.TRAVEL_AGENCY_DETAIL_NOT_FOUND));
        if(operland.equals("plus")) {
            travelAgencyListDetail.personPlusCount(travelAgencyListDetail.getPersonCount(),count);
        } else {
            travelAgencyListDetail.personMinusCount(travelAgencyListDetail.getPersonCount(),count);
        }
    }

    @Transactional(readOnly = true)
    public List<TravelAgencyListDto> travelMainCarousel() {
        return travelAgencyListRepository.findByDeletedAndSortIsNotNullOrderBySort(false).stream().map(TravelAgencyListDto::from).collect(Collectors.toList());
    }

    @Transactional
    public void searchEndDate() {
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(nowDate).toString();
       var todayTravel =  travelAgencyListRepository.findByTravelEndAtLessThanEqualAndDeleted(today , false);
        todayTravel.forEach(deletedChk->deletedChk.setDeleted(true));
    }

    @Transactional
    public void travelAgencyListLike(Long travelAgencyListLikeId , Long userId) {
        var chkLike = travelAgencyListLikeRepository.findByTravelAgencyList_IdAndTripUser_Id(travelAgencyListLikeId, userId).isEmpty();
        System.out.println("::::::::::"+chkLike);
        //신규 좋아요 클릭 시
        if(chkLike) {
            var tripUser = tripUserRepository.findById(userId).orElseThrow(() -> new BoardSiteException(ErrorCode.USER_NOT_FOUND));
            var travelAgencyList = travelAgencyListRepository.findById(travelAgencyListLikeId).orElseThrow(() -> new BoardSiteException(ErrorCode.TRAVEL_AGENCY_LIST_NOT_FOUND));
            travelAgencyListLikeRepository.save(TravelAgencyLike.of(tripUser, travelAgencyList,false));

        //좋아요 가 있을 때
        } else {
            var likeDto = travelAgencyListLikeRepository.findByTravelAgencyList_IdAndTripUser_Id(travelAgencyListLikeId, userId).orElseThrow(()-> new BoardSiteException(ErrorCode.LIKE_NOT));
            travelAgencyListLikeRepository.delete(likeDto);
        }
    }


}
