package ru.netology.transfer_card_spring.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferOperation {
    private int id;

    private Card cardFrom;

    private Card cardTo;

    private Amount amount;

    private String code;
}
