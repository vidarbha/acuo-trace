package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.services.BaseTransformTo;
import com.acuo.collateral.transform.trace.transformer_margin.MSMapper;
import com.acuo.common.model.margin.MarginCall;

import javax.inject.Inject;

public abstract class MarginCallTransformTo extends BaseTransformTo<MarginCall> {

    @Inject
    protected MSMapper mapper = null;

}
