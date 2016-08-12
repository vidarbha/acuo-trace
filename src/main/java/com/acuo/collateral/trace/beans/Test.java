package com.acuo.collateral.trace.beans;

import com.opengamma.strata.basics.date.DayCount;
import com.opengamma.strata.basics.schedule.Frequency;
import com.opengamma.strata.basics.schedule.RollConvention;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Test extends BaseTrade {

    private DayCount dayCount;
    private Frequency frequency;
    private RollConvention rollCode;

}
