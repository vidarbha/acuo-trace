package com.acuo.collateral.trace.beans;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IrSwapLegPayDates {

    private LocalDate startDate;
    private String frequency;
    private LocalDate enddate;
    private String rollCode;
    private boolean adjust;
    private boolean eom;

}