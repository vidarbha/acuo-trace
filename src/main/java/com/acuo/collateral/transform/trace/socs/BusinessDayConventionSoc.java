package com.acuo.collateral.transform.trace.socs;

import com.acuo.common.model.BusinessDayConventionProxy;
import com.opengamma.strata.basics.date.BusinessDayConvention;
import com.opengamma.strata.basics.date.DayCount;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

public class BusinessDayConventionSoc extends StringBOT<BusinessDayConventionProxy> {
    @Override
    public Object transformerFromExternalObject(BusinessDayConventionProxy businessDayConvention) throws DataException {
        return businessDayConvention.getName();
    }

    @Override
    public BusinessDayConventionProxy externalObjectFromTransformer(String name) throws DataException {
        return new BusinessDayConventionProxy(BusinessDayConvention.of(name));
    }

    @Override
    public Class<BusinessDayConventionProxy> getExternalObjectClass() {
        return BusinessDayConventionProxy.class;
    }
}
