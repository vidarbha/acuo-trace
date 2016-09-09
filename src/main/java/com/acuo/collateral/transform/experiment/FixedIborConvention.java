package com.acuo.collateral.transform.experiment;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FixedIborConvention extends BaseConvention {
    private String name;
    private Convention fixedConvention;
    private Convention floatConvention;
}
