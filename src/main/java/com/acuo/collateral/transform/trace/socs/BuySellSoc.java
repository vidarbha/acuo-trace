package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.product.common.BuySell;
import com.opengamma.strata.product.common.PayReceive;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

public class BuySellSoc extends StringBOT<BuySell> {
    @Override
    public Object transformerFromExternalObject(BuySell buySell) throws DataException {
        return buySell.toString();
    }

    @Override
    public BuySell externalObjectFromTransformer(String name) throws DataException {
        return BuySell.of(name);
    }

    @Override
    public Class<BuySell> getExternalObjectClass() {
        return BuySell.class;
    }
}