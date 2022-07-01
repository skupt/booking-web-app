package org.example.booking.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_account")
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
