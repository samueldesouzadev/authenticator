package br.com.auth.repository;

import br.com.auth.entity.Card;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public interface CardRepository extends CrudRepository<Card, Long> {

    @Query(value = "SELECT " +
            "TB_CARD.ID," +
            "TB_CARD.CARD_NUMBER," +
            "TB_CARD.PASSWORD," +
            "TB_CARD.BALANCE," +
            "TB_CARD.IS_ACTIVE" +
            " FROM TB_CARD WHERE TB_CARD.CARD_NUMBER = :cardNumber", nativeQuery = true)
    Card findBalanceByCardNumber(@Param("cardNumber") String cardNumber);

    @Modifying
    @Query(value = "UPDATE TB_CARD TC SET TC.BALANCE = ?1 WHERE TC.CARD_NUMBER = ?2", nativeQuery = true)
    void balaceUpdate(BigDecimal balance, String cardNumber);
}
