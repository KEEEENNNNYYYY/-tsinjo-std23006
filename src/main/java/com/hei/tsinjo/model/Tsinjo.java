package com.hei.tsinjo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Tsinjo {
    private Long balance;

    public Long help(Long amount) {
        this.balance -= amount;
        return this.balance;
    }
}
