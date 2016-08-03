package com.acuo.collateral.trace.beans;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class IrSwap {

    private String tradeId;
    private LocalDate tradeDate;
    private String book;
    private Set<IrSwapLeg> legs;

}