package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.basics.currency.CurrencyAmount;
import com.tracegroup.transformer.externalobjects.socs.SOCImplementingTBean;
import com.tracegroup.transformer.mom.DataException;

public class CurrencyAmountSoc extends SOCImplementingTBean<String, CurrencyAmount> {
    @Override
    public Object transformerFromExternalObject(CurrencyAmount currencyAmount) throws DataException {
        return currencyAmount.toString();
    }

    @Override
    public CurrencyAmount externalObjectFromTransformer(String s) throws DataException {
        return CurrencyAmount.parse(s);
    }

    @Override
    public Class<String> getBotClass() {
        return String.class;
    }

    @Override
    public Class<CurrencyAmount> getExternalObjectClass() {
        return CurrencyAmount.class;
    }

    /*@Override
    public String getConverterName() {
        return "CurrencyAmountConverter";
    }*/
}
