package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.Transformer;
import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.TransformerOutput;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class BaseTransformer<INPUT, OUTPUT> implements Transformer<INPUT, OUTPUT> {

    @Override
    public String serialise(INPUT value, TransformerContext context) {
        return serialise(ImmutableList.of(value), context);
    }

    @Override
    public String serialise(List<INPUT> value, TransformerContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TransformerOutput<OUTPUT> deserialise(String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TransformerOutput<OUTPUT> deserialise(byte[] value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TransformerOutput<OUTPUT> deserialiseToList(String values) {
        throw new UnsupportedOperationException();
    }
}
