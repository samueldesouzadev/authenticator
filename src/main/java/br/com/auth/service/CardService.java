package br.com.auth.service;

import br.com.auth.entity.Card;
import br.com.auth.entity.dto.CardDto;
import br.com.auth.exception.CardNotFoundException;
import br.com.auth.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardService {

    private final static BigDecimal BALANCE_DEFAULT = BigDecimal.valueOf(500L);

    @Autowired
    private CardRepository cardRepository;


    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void cardAsBalance(String cardNumber, BigDecimal valueTransaction) throws CardNotFoundException {
        Card card = findBalanceByCardNumber(cardNumber);
        if(card != null){
            if(card.getBalance().compareTo(valueTransaction) >= 0){
                BigDecimal newBalance = card.getBalance().subtract(valueTransaction);
                updateBalanceByCard(newBalance, cardNumber);
            }
        }
        else {
            throw new CardNotFoundException();
        }
    }

    public void updateBalanceByCard(BigDecimal balance, String cardNumber){
       cardRepository.balaceUpdate(balance, cardNumber);
    }

    public Card saveNewCard(CardDto cardDto){
            Card card = new Card();
            card.setCardNumber(cardDto.getNumeroCartao());
            card.setBalance(BALANCE_DEFAULT);
            card.setPassword(cardDto.getSenha());
            card.setActive(true);
            return cardRepository.save(card);
    }

    public Card findBalanceByCardNumber(String cardNumber){
        return cardRepository.findBalanceByCardNumber(cardNumber);
    }
}
