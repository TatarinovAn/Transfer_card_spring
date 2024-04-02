package ru.netology.transfer_card_spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    private String cardNumber;
    private String validTill;
    private String cvv;
    private AmountCard amountCard;
}
