package com.acuo.collateral.trace.beans;

import lombok.Data;

@Data
public class IrSwapLeg {

    private int id;
    private String currency;
    private IrSwapLegFixing fixing;
    private Double spread;
    private Double rate;
    private String type;
    private String daycount;
    private Double notional;
    private String notionalxg;
    private IrSwapLegPayDates paydates;

}