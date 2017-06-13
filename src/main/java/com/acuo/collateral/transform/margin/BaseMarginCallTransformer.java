package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.Transformer;
import com.acuo.collateral.transform.trace.transformer_margin.MarginCall;

import java.util.List;

public abstract class BaseMarginCallTransformer<T> implements Transformer<T> {

    private final MarginCall marginCall;


    public BaseMarginCallTransformer(MarginCall marginCall) {
        this.marginCall = marginCall;
    }


    public MarginCall getMarginCall() {
        return marginCall;
    }


    @Override
    public List<T> deserialise(byte[] value) {
        return null;
    }
}