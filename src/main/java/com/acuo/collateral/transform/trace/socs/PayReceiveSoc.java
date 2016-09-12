package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.product.common.PayReceive;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

public class PayReceiveSoc extends StringBOT<PayReceive> {
    @Override
    public Object transformerFromExternalObject(PayReceive payReceive) throws DataException {
        return payReceive.toString();
    }

    @Override
    public PayReceive externalObjectFromTransformer(String name) throws DataException {
        return PayReceive.of(name);
    }

    @Override
    public Class<PayReceive> getExternalObjectClass() {
        return PayReceive.class;
    }
}