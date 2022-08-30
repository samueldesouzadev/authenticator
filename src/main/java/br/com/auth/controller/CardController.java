package br.com.auth.controller;

import br.com.auth.entity.Card;
import br.com.auth.entity.dto.CardDto;
import br.com.auth.service.CardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
public class CardController {

    @Autowired
    private CardService cardService;

    @ApiOperation(value = "Cria novo cartão")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCard(@RequestBody CardDto cardDto) {
        try {
            Card card = cardService.saveNewCard(cardDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(card);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(cardDto);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    @ApiOperation(value = "Consulta saldo do Cartão")
    @RequestMapping("/{numeroCartao}")
    public ResponseEntity<?> findByCardNumber(@PathVariable("numeroCartao") String cardNumber) {
        try {
            Card card = cardService.findBalanceByCardNumber(cardNumber);
            if(card == null){
               return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("cardNumber:" + cardNumber);
            }
            return ResponseEntity.status(HttpStatus.OK).body(card.getBalance());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
