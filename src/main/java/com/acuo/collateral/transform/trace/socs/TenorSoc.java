package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.basics.date.Tenor;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

public class TenorSoc extends StringBOT<Tenor> {
    @Override
    public Object transformerFromExternalObject(Tenor tenor) throws DataException {
        return tenor.toString();
    }

    @Override
    public Tenor externalObjectFromTransformer(String name) throws DataException {
        return Tenor.parse(name);
    }

    @Override
    public Class<Tenor> getExternalObjectClass() {
        return Tenor.class;
    }
}
