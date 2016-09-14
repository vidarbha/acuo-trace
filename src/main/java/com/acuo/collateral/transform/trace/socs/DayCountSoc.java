package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.basics.date.DayCount;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

public class DayCountSoc extends StringBOT<DayCount> {
    @Override
    public Object transformerFromExternalObject(DayCount dayCount) throws DataException {
        return dayCount.getName();
    }

    @Override
    public DayCount externalObjectFromTransformer(String name) throws DataException {
        return DayCount.of(name);
    }

    @Override
    public Class<DayCount> getExternalObjectClass() {
        return DayCount.class;
    }

    @Override
    public boolean canProvideSOCForClass(Class clazz) {
        return DayCount.class.isAssignableFrom(clazz);
    }
}
