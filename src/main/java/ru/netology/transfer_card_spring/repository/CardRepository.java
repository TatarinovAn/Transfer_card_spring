package ru.netology.transfer_card_spring.repository;

import ru.netology.transfer_card_spring.model.Card;

public interface CardRepository {
    Card getCardByNumber(String cardNumber);

    void saveCard(Card card);
}
