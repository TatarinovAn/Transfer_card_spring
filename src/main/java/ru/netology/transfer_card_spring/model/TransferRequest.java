package ru.netology.transfer_card_spring.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferRequest {
    private String cardFromNumber;

    private String cardFromValidTill;

    private String cardFromCVV;

    private String cardToNumber;

    private Amount amount;
}
