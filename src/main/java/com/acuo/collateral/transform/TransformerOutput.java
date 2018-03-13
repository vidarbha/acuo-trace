package com.acuo.collateral.transform;

import com.acuo.common.model.results.Error;

import java.util.List;

public interface TransformerOutput<OUTPUT> {

    List<OUTPUT> results();

    List<Error> errors();
}
