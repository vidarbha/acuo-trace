package com.acuo.collateral.transform.utils;

import com.acuo.collateral.transform.inputs.Envelop;
import com.acuo.common.model.trade.FRATrade;
import com.acuo.common.model.trade.FxSwapTrade;
import com.acuo.common.model.trade.ProductTrade;
import com.acuo.common.model.trade.SwapTrade;

import java.util.List;
import java.util.stream.Collectors;

public class InputBuilder {

    public static <INPUT  extends ProductTrade> List<Envelop> envelops(List<INPUT> values) {
        return values.stream()
                .map(trade -> {
                    Envelop envelop = new Envelop();
                    envelop.setType(trade.getType());
                    switch (trade.getType()) {
                        case FRA:
                            envelop.setFRATrade((FRATrade) trade);
                            break;
                        case SWAP:
                            envelop.setSwapTrade((SwapTrade) trade);
                            break;
                        case FXSWAP:
                            envelop.setFXSwapTrade((FxSwapTrade) trade);
                            break;
                        default:
                            throw new UnsupportedOperationException();
                    }
                    return envelop;
                })
                .collect(Collectors.toList());
    }
}
