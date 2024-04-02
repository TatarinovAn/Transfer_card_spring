package ru.netology.transfer_card_spring.repository;

import ru.netology.transfer_card_spring.model.AmountCard;
import ru.netology.transfer_card_spring.model.Card;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CardRepositoryImp implements CardRepository {
    Map<String, Card> cardMap = new ConcurrentHashMap<>() {
        {
            this.put("123456780123456", Card.builder()
                    .cardNumber("123456780123456")
                    .validTill("03/30")
                    .cvv("123")
                    .amountCard(AmountCard.builder()
                            .value(10000)
                            .currency("RUR")
                            .build())
                    .build());
            this.put("0987654321098765", Card.builder()
                    .cardNumber("0987654321098765")
                    .validTill("03/30")
                    .cvv("321")
                    .amountCard(AmountCard.builder()
                            .value(20000)
                            .currency("RUR")
                            .build())
                    .build());
        }
    };

    @Override
    public Card getCardByNumber(String cardNumber) {
        return cardMap.get(cardNumber);
    }

    @Override
    public void saveCard(Card card) {
        cardMap.put(card.getCardNumber(), card);
    }
}
