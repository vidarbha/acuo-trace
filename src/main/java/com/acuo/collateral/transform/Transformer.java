package com.acuo.collateral.transform;

import java.util.List;

public interface Transformer<T> {

    String serialise(T value, TransformerContext context);

    String serialise(List<T> value, TransformerContext context);

    T deserialise(String value);

    List<T> deserialiseToList(String values);

}
