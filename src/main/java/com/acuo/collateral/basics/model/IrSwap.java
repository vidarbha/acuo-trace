package com.acuo.collateral.basics.model;

import com.opengamma.strata.basics.currency.Currency;
import com.opengamma.strata.basics.date.BusinessDayConvention;
import com.opengamma.strata.basics.date.DayCount;
import com.opengamma.strata.basics.schedule.Frequency;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class IrSwap extends BaseTrade {

    private Set<IrSwapLeg> legs;

    @Data
    public static class IrSwapLeg {
        private int id;
        private Currency currency;
        private IrSwapLegFixing fixing;
        private Double spread;
        private Double rate;
        private String type;
        private DayCount daycount;
        private Double notional;
        private String notionalxg;
        private IrSwapLegPayDates paydates;
    }

    @Data
    public static class IrSwapLegFixing {
        private String name;
        private String term;
        private boolean arrears;
    }

    @Data
    public static class IrSwapLegPayDates {
        private LocalDate startDate;
        private Frequency frequency;
        private LocalDate enddate;
        private BusinessDayConvention businessDayConvention;
        private boolean adjust;
        private boolean eom;
    }
}