package com.acuo.collateral.transform;

import java.util.List;

public interface Transformer<INPUT, OUTPUT> {

    String serialise(INPUT value, TransformerContext context);

    String serialise(List<INPUT> value, TransformerContext context);

    TransformerOutput<OUTPUT> deserialise(String value);

    TransformerOutput<OUTPUT> deserialiseToList(String values);

    TransformerOutput<OUTPUT> deserialise(byte[] value);
}
