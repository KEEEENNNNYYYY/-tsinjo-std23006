package com.hei.tsinjo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Payment {
    private int amount;
    private String currency;
    private String receiver;

}
