package com.board.boardsite.service.adm.user;

import com.board.boardsite.domain.constant.SearchAdmUserType;
import com.board.boardsite.dto.adm.travel.AdmTravelAgencyReservationDto;
import com.board.boardsite.dto.response.adm.auth.AdmUserDto;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.travel.TravelAgencyReservationDto;
import com.board.boardsite.repository.adm.travel.AdmTravelAgencyReservationRepository;
import com.board.boardsite.repository.adm.user.AdmUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdmUserService {

    private final AdmUserRepository admUserRepository;

    private final AdmTravelAgencyReservationRepository admTravelAgencyReservationRepository;

    @Transactional(readOnly = true)
    public PageImpl<AdmUserDto> userList(SearchAdmUserType searchAdmUserType , String input , TripUserPrincipal tripUserPrincipal , Pageable pageable) {
        if(tripUserPrincipal.role().equals("SUPER")) {
            if (input == null || input.isBlank()) {
                return (PageImpl<AdmUserDto>)admUserRepository.findByRole("USER", pageable).map(AdmUserDto::from);
            }
            return switch (searchAdmUserType) {
                case NAME -> (PageImpl<AdmUserDto>)admUserRepository.findByNameContainingAndRole(input, "USER", pageable).map(AdmUserDto::from);
                case EMAIL -> (PageImpl<AdmUserDto>)admUserRepository.findByEmailContainingAndRole(input, "USER", pageable).map(AdmUserDto::from);
                case NICKNAME -> (PageImpl<AdmUserDto>)admUserRepository.findByNickNameContainingAndRole(input, "USER", pageable).map(AdmUserDto::from);
            };
        } else {
            if (input == null || input.isBlank()) {
                return admUserRepository.findTravelAgencyIdAndUser(tripUserPrincipal.travelAgencyId(), pageable);
            }
            return switch (searchAdmUserType) {
                case NAME -> admUserRepository.findTravelAgencyIdAndUserAndName(tripUserPrincipal.travelAgencyId(),input,  pageable);
                case EMAIL -> admUserRepository.findTravelAgencyIdAndUserAndEmail(tripUserPrincipal.travelAgencyId(),input,  pageable);
                case NICKNAME -> admUserRepository.findTravelAgencyIdAndUserAndNickName(tripUserPrincipal.travelAgencyId(),input,  pageable);
            };
        }
   }


    @Transactional(readOnly = true)
    public Page<AdmTravelAgencyReservationDto> userPayList(Long id, TripUserPrincipal tripUserPrincipal , Pageable pageable) {
        return admTravelAgencyReservationRepository.findByTripUser_IdAndTravelAgency_Id(id, tripUserPrincipal.travelAgencyId(), pageable).map(AdmTravelAgencyReservationDto::from);

    }




}
