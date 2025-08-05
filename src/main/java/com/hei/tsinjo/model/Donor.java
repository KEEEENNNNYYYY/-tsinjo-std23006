package com.hei.tsinjo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
public class Donor {
    private Long balance;

    public Long doDonation(Long amount) {
        this.balance -= amount;
        return this.balance;
    }

}