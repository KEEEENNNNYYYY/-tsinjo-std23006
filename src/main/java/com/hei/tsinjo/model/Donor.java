package com.hei.tsinjo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
public class Donor {
    private String id;
    private Long balance;
    private String name;
    private String email;


    public Long doDonation(Long amount) {
        this.balance -= amount;
        return this.balance;
    }

}