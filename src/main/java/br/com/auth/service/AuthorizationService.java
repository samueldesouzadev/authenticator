package br.com.auth.service;

import br.com.auth.entity.Card;
import br.com.auth.entity.dto.TransactionDto;
import br.com.auth.exception.CardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AuthorizationService {

    @Autowired
    CardService cardService;

    public void processAuthorization(TransactionDto transactionDto) throws CardNotFoundException {
        cardService.cardAsBalance(transactionDto.getNumeroCartao(), transactionDto.getValor());
    }
}
