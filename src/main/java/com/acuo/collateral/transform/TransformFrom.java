package com.acuo.collateral.transform;

public interface TransformFrom<OUTPUT> {

    TransformerOutput<OUTPUT> deserialise(String value);

    TransformerOutput<OUTPUT> deserialiseToList(String values);

    TransformerOutput<OUTPUT> deserialise(byte[] value);

}
