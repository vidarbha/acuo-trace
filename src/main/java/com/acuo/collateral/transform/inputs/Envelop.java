package com.acuo.collateral.transform.inputs;

import com.acuo.common.model.trade.FRATrade;
import com.acuo.common.model.trade.FxSwapTrade;
import com.acuo.common.model.trade.ProductType;
import com.acuo.common.model.trade.SwapTrade;
import lombok.Data;

@Data
public class Envelop {
    private ProductType type;
    private FRATrade FRATrade;
    private SwapTrade SwapTrade;
    private FxSwapTrade FXSwapTrade;
}
