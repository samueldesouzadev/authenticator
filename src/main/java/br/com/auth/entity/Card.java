package br.com.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_CARD")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CARD_NUMBER", unique = true)
    private String cardNumber;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;
}
