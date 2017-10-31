package com.acuo.collateral.transform;

public interface TransformFrom<OUTPUT> {

    OUTPUT deserialise(String value);

    OUTPUT deserialiseToList(String values);

    OUTPUT deserialise(byte[] value);

}
