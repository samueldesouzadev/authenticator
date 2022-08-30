package br.com.auth.controller;

import br.com.auth.entity.Card;
import br.com.auth.entity.dto.TransactionDto;
import br.com.auth.exception.CardNotFoundException;
import br.com.auth.service.AuthorizationService;
import br.com.auth.service.CardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransactionsController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping
    @ApiOperation(value = "Processa autorização")
    public ResponseEntity<?> authorization(@RequestBody TransactionDto transactionDto) {
        try {
            authorizationService.processAuthorization(transactionDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (CardNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
