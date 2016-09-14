package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.basics.index.FloatingRateName;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

public class FloatingRateNameSoc extends StringBOT<FloatingRateName> {
    @Override
    public Object transformerFromExternalObject(FloatingRateName floationgRateName) throws DataException {
        return floationgRateName.getName();
    }

    @Override
    public FloatingRateName externalObjectFromTransformer(String name) throws DataException {
        return FloatingRateName.of(name);
    }

    @Override
    public Class<FloatingRateName> getExternalObjectClass() {
        return FloatingRateName.class;
    }

    @Override
    public boolean canProvideSOCForClass(Class clazz) {
        return FloatingRateName.class.isAssignableFrom(clazz);
    }
}
