package com.acuo.collateral.transform.inputs;

import com.acuo.common.model.trade.*;
import lombok.Data;

@Data
public class Envelop {
    private ProductType type;
    private FRATrade FRATrade;
    private SwapTrade SwapTrade;
    private FXSwapTrade FXSwapTrade;
}
