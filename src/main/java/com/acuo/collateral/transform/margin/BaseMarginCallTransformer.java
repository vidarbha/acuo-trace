package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.TransformerOutput;
import com.acuo.collateral.transform.services.BaseTransformer;
import com.acuo.collateral.transform.trace.transformer_margin.MarginCall;

import javax.inject.Inject;

public abstract class BaseMarginCallTransformer<INOUT> extends BaseTransformer<INOUT, INOUT> {

    @Inject
    protected MarginCall marginCall = null;

    @Override
    public TransformerOutput<INOUT> deserialise(String value) {
        return deserialiseToList(value);
    }

}
