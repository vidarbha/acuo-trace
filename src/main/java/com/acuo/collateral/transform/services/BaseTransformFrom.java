package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformFrom;
import com.acuo.collateral.transform.TransformerOutput;

public class BaseTransformFrom<OUTPUT> implements TransformFrom<OUTPUT> {

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
