package ru.netology.transfer_card_spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.transfer_card_spring.exception.InputException;
import ru.netology.transfer_card_spring.exception.TransferException;
import ru.netology.transfer_card_spring.TestDatabase;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TransferServiceTest {

    @Autowired
    TransferService service;

    @Test
    void successTransferMoney() {
        var expected1 = service.transferMoney(TestDatabase.getSuccessTransferRequest());
        assertEquals(expected1.getOperationId(), 1);
        var expected2 = service.confirmOperation(TestDatabase.getConfirmRequest());
        assertEquals(expected2.getOperationId(), 1);
    }

    @Test
    void failedTransferMoneyIncorrectCard() {

        assertThrows(InputException.class,
                () -> service.transferMoney(TestDatabase.getFailedCardFromTransferRequest()),
                "Номер карты отправителя не верный.");
    }

    @Test
    void failedTransferMoneyIncorrectCVV() {
        assertThrows(InputException.class,
                () -> service.transferMoney(TestDatabase.getFailedCVVTransferRequest()),
                "CVV код карты отправителя введен неверно.");
    }

    @Test
    void failedTransferMoneyIncorrectAmount() {
        assertThrows(TransferException.class,
                () -> service.transferMoney(TestDatabase.getFailedAmountTransferRequest()),
                "Недостаточно средств для перевода.");
    }

    @Test
    void failedConfirmOperationIncorrect() {
        assertThrows(InputException.class,
                () -> service.confirmOperation(TestDatabase.getSuccessConfirmRequest()),
                "Операция не найдена.");
    }

}
