package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.services.BaseTransformTo;
import com.acuo.common.model.margin.MarginCall;

import javax.inject.Inject;

public abstract class MarginCallTransformTo extends BaseTransformTo<MarginCall> {

    @Inject
    protected com.acuo.collateral.transform.trace.transformer_margin.MarginCall marginCall = null;

}
