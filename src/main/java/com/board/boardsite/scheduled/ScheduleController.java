package com.board.boardsite.scheduled;

import com.board.boardsite.service.travel.TravelAgencyListService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@RequiredArgsConstructor
public class ScheduleController {

    private final TravelAgencyListService travelAgencyListService;


    @Scheduled(cron="0/10 * * * * *")
    public void test2() {
        travelAgencyListService.searchEndDate();
    }
}
