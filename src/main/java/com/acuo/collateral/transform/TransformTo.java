package com.acuo.collateral.transform;

import java.util.List;

public interface TransformTo<INPUT> {

    String serialise(INPUT value, TransformerContext context);

    String serialise(List<INPUT> value, TransformerContext context);
}
