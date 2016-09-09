package com.acuo.collateral.transform.experiment;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FloatConvention extends BaseConvention {
    private String index;
}
