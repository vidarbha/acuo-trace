package com.acuo.collateral.transform.trace.socs;


import com.opengamma.strata.basics.schedule.RollConvention;
import com.opengamma.strata.basics.schedule.RollConventions;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;
import org.apache.commons.lang.StringUtils;

public class RollConventionSoc extends StringBOT<RollConvention> {
    @Override
    public Object transformerFromExternalObject(RollConvention rollConvention) throws DataException {
        return rollConvention.getName();
    }

    @Override
    public RollConvention externalObjectFromTransformer(String uniqueName) throws DataException {
        if (StringUtils.isEmpty(uniqueName)) return RollConventions.NONE;
        if ("IMM".equals(uniqueName)) return RollConventions.IMM;
        if ("EOM".equals(uniqueName)) return RollConventions.EOM;
        try {
            Integer dom = Integer.valueOf(uniqueName);
            return RollConvention.ofDayOfMonth(dom);
        } catch (NumberFormatException nfe) {
            return RollConvention.of(uniqueName);
        }
    }

    @Override
    public Class<RollConvention> getExternalObjectClass() {
        return RollConvention.class;
    }
}
