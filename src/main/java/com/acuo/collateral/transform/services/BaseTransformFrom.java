package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformFrom;
import com.acuo.collateral.transform.TransformerOutput;

public class BaseTransformFrom<OUTPUT> implements TransformFrom<OUTPUT> {

    @Override
    public OUTPUT deserialise(String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OUTPUT deserialise(byte[] value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OUTPUT deserialiseToList(String values) {
        throw new UnsupportedOperationException();
    }

}
