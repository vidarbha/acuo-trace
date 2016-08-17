package com.acuo.collateral.transform.trace.socs;


import com.acuo.common.model.proxy.RollConventionProxy;
import com.opengamma.strata.basics.schedule.RollConvention;
import com.opengamma.strata.basics.schedule.RollConventions;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;
import org.apache.commons.lang.StringUtils;

public class RollConventionSoc extends StringBOT<RollConventionProxy> {
    @Override
    public Object transformerFromExternalObject(RollConventionProxy rollConvention) throws DataException {
        return rollConvention.getName();
    }

    @Override
    public RollConventionProxy externalObjectFromTransformer(String uniqueName) throws DataException {
        if (StringUtils.isEmpty(uniqueName)) return new RollConventionProxy(RollConventions.NONE);
        if ("IMM".equals(uniqueName)) return new RollConventionProxy(RollConventions.IMM);
        if ("EOM".equals(uniqueName)) return new RollConventionProxy(RollConventions.EOM);
        try {
            Integer dom = Integer.valueOf(uniqueName);
            return new RollConventionProxy(RollConvention.ofDayOfMonth(dom));
        } catch (NumberFormatException nfe) {
            return new RollConventionProxy(RollConvention.of(uniqueName));
        }
    }

    @Override
    public Class<RollConventionProxy> getExternalObjectClass() {
        return RollConventionProxy.class;
    }
}
