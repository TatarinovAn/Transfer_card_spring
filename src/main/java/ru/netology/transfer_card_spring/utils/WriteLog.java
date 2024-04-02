package ru.netology.transfer_card_spring.utils;

import ru.netology.transfer_card_spring.model.Card;
import ru.netology.transfer_card_spring.model.Amount;


import static ru.netology.transfer_card_spring.utils.Commission.COMMISSION_VALUE;

public class WriteLog {
    public static final Logger log = Logger.getInstance();
    public static void writeFailToLog(Card cardFrom, Card cardTo, Amount amount, String error) {
        log.writeLog(String.format("Карта отправителя %s >>>> Карта отправителя %s  -- Сумма: %d -- Комиссия: %d -- ОТКЛОНЕН-- %s",
                cardFrom.getCardNumber(),
                cardTo.getCardNumber(),
                amount.getValue(),
                (int) (amount.getValue() * COMMISSION_VALUE),
                error));
    }

    public static void writeFailToLog(String error) {
        log.writeLog(String.format("-- ОТКЛОНЕН -- %s",
                error));
    }

    public static void writeSuccessToLog(Card cardFrom, Card cardTo, Amount amount) {
        log.writeLog(String.format("Карта отправителя %s >>>> Карта отправителя %s -- Сумма: %d --  Комиссия: %d -- УСПЕШНО",
                cardFrom.getCardNumber(),
                cardTo.getCardNumber(),
                amount.getValue(),
                (int) (amount.getValue() * COMMISSION_VALUE)));
        System.out.println(cardFrom.getAmountCard().getValue() + " - Баланс отправителя   " + cardTo.getAmountCard().getValue() +
                " Баланс получателя");
    }
}
