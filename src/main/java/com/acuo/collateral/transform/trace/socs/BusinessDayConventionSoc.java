package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.basics.date.BusinessDayConvention;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

public class BusinessDayConventionSoc extends StringBOT<BusinessDayConvention> {
    @Override
    public Object transformerFromExternalObject(BusinessDayConvention businessDayConvention) throws DataException {
        return businessDayConvention.getName();
    }

    @Override
    public BusinessDayConvention externalObjectFromTransformer(String name) throws DataException {
        return BusinessDayConvention.of(name);
    }

    @Override
    public Class<BusinessDayConvention> getExternalObjectClass() {
        return BusinessDayConvention.class;
    }

    @Override
    public boolean canProvideSOCForClass(Class clazz) {
        return BusinessDayConvention.class.isAssignableFrom(clazz);
    }
}
