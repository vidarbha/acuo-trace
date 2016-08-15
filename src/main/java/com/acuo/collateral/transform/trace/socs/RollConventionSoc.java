package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.basics.schedule.RollConvention;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

public class RollConventionSoc extends StringBOT<RollConvention> {
    @Override
    public Object transformerFromExternalObject(RollConvention rollConvention) throws DataException {
        return rollConvention.getName();
    }

    @Override
    public RollConvention externalObjectFromTransformer(String uniqueName) throws DataException {
        return RollConvention.of(uniqueName);
    }

    @Override
    public Class<RollConvention> getExternalObjectClass() {
        return RollConvention.class;
    }
}
