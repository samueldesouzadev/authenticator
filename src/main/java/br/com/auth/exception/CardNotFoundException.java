package br.com.auth.exception;


import lombok.extern.java.Log;

@Log
public class CardNotFoundException extends Exception{

    public CardNotFoundException(){
        log.info("NUMERO DO CARTÃO NÃO ENCONTRADO");
    }
}
