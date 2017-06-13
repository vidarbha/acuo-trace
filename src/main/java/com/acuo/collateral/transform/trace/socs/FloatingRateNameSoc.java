package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.basics.index.FloatingRateName;
import com.opengamma.strata.basics.index.FloatingRateType;
import com.opengamma.strata.basics.index.ImmutableFloatingRateName;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FloatingRateNameSoc extends StringBOT<FloatingRateName> {
    @Override
    public Object transformerFromExternalObject(FloatingRateName floationgRateName) throws DataException {
        return floationgRateName.getName();
    }

    @Override
    public FloatingRateName externalObjectFromTransformer(String name) throws DataException {
        try
        {
            return FloatingRateName.of(name);
        }catch (Exception e)
        {
            log.warn("can not find name :" + name);
            return ImmutableFloatingRateName.of(name,name, FloatingRateType.IBOR);
        }

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
