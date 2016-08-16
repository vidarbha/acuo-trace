package com.acuo.collateral.transform.trace.socs;

import com.acuo.common.model.DayCountProxy;
import com.opengamma.strata.basics.date.DayCount;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

public class DayCountSoc extends StringBOT<DayCountProxy> {
    @Override
    public Object transformerFromExternalObject(DayCountProxy dayCount) throws DataException {
        return dayCount.getName();
    }

    @Override
    public DayCountProxy externalObjectFromTransformer(String name) throws DataException {
        return new DayCountProxy(DayCount.of(name));
    }

    @Override
    public Class<DayCountProxy> getExternalObjectClass() {
        return DayCountProxy.class;
    }
}
