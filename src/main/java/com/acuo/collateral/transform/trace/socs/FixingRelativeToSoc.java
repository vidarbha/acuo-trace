package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.product.swap.FixingRelativeTo;
import com.tracegroup.transformer.externalobjects.socs.StringBOT;
import com.tracegroup.transformer.mom.DataException;

public class FixingRelativeToSoc extends StringBOT<FixingRelativeTo> {
    @Override
    public Object transformerFromExternalObject(FixingRelativeTo fixingRelativeTo) throws DataException {
        return fixingRelativeTo.toString();
    }

    @Override
    public FixingRelativeTo externalObjectFromTransformer(String name) throws DataException {
        return FixingRelativeTo.of(name);
    }

    @Override
    public Class<FixingRelativeTo> getExternalObjectClass() {
        return FixingRelativeTo.class;
    }
}