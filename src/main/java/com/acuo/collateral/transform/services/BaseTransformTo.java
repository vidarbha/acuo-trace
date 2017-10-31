package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformTo;
import com.acuo.collateral.transform.TransformerContext;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class BaseTransformTo<INPUT> implements TransformTo<INPUT> {

    @Override
    public String serialise(INPUT value, TransformerContext context) {
        return serialise(ImmutableList.of(value), context);
    }

    @Override
    public String serialise(List<INPUT> value, TransformerContext context) {
        throw new UnsupportedOperationException();
    }

}
