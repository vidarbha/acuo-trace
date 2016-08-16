package com.acuo.collateral.transform.trace.socs;

import com.acuo.common.model.RollConventionProxy;
import com.opengamma.strata.basics.schedule.RollConvention;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

public class RollConventionSoc extends StringBOT<RollConventionProxy> {
    @Override
    public Object transformerFromExternalObject(RollConventionProxy rollConvention) throws DataException {
        return rollConvention.getName();
    }

    @Override
    public RollConventionProxy externalObjectFromTransformer(String uniqueName) throws DataException {
        return new RollConventionProxy(RollConvention.of(uniqueName));
    }

    @Override
    public Class<RollConventionProxy> getExternalObjectClass() {
        return RollConventionProxy.class;
    }
}
