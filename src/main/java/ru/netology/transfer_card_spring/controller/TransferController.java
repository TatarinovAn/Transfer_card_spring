package ru.netology.transfer_card_spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.transfer_card_spring.model.ConfirmRequest;
import ru.netology.transfer_card_spring.model.TransferRequest;
import ru.netology.transfer_card_spring.model.TransferResponse;
import ru.netology.transfer_card_spring.service.TransferService;


@RestController
@AllArgsConstructor
@CrossOrigin
public class TransferController {

    private final TransferService service;


    @PostMapping("/transfer")
    public TransferResponse transfer(@RequestBody TransferRequest transferRequestDto) {
        return service.transferMoney(transferRequestDto);
    }

    @PostMapping("/confirmOperation")
    public TransferResponse confirmOperation(@RequestBody ConfirmRequest confirmRequest) {
        return service.confirmOperation(confirmRequest);
    }

}

