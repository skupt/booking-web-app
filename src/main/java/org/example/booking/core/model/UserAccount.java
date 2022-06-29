package org.example.booking.core.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class UserAccount {
    @Id
    @Column(name = "user_id")
    private long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserImpl userImpl;
    @Column(name = "money_amount")
    private BigDecimal moneyAmount;
}
