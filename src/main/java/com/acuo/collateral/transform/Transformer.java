package com.acuo.collateral.transform;

import java.util.List;

public interface Transformer<INPUT, OUTPUT> {

    String serialise(INPUT value, TransformerContext context);

    String serialise(List<INPUT> value, TransformerContext context);

    OUTPUT deserialise(String value);

    List<OUTPUT> deserialiseToList(String values);

    List<OUTPUT> deserialise(byte[] value);
}
