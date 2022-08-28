package com.board.boardsite.dto.request.travel;

public record TravelAgencyRerservationRefundRequest(
        String impUid,
        int money,
        int personCount,
        Long id,
        Long travelAgencyListId
) {
    public static TravelAgencyRerservationRefundRequest of(String impUid,
                                                           int money,
                                                           int personCount,
                                                           Long id,
                                                           Long travelAgencyListId)
    {
        return new TravelAgencyRerservationRefundRequest(impUid,
                money,
                personCount,
                id,
                travelAgencyListId);
    }

}
