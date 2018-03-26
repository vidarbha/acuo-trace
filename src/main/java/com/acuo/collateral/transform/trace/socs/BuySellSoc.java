package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.product.common.BuySell;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;

public class BuySellSoc extends StringBOT<BuySell> {
    @Override
    public Object transformerFromExternalObject(BuySell buySell) {
        return buySell.toString();
    }

    @Override
    public BuySell externalObjectFromTransformer(String name) {
        return BuySell.of(name);
    }

    @Override
    public Class<BuySell> getExternalObjectClass() {
        return BuySell.class;
    }
}