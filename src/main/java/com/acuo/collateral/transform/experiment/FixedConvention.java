package com.acuo.collateral.transform.experiment;

import com.opengamma.strata.basics.currency.Currency;
import com.opengamma.strata.basics.schedule.Frequency;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FixedConvention extends BaseConvention {
    private Currency currency;
    private String dayCount;
    private Frequency frequency;
    private String businessDayConvention;
}
