package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.basics.currency.Currency;
import com.tracegroup.transformer.externalobjects.socs.SOCImplementingTBean;
import com.tracegroup.transformer.mom.DataException;

public class CurrencySoc  extends SOCImplementingTBean<String, Currency> {
    @Override
    public Object transformerFromExternalObject(com.opengamma.strata.basics.currency.Currency currency) throws DataException {
        return currency.getCode();
    }

    @Override
    public Currency externalObjectFromTransformer(String currency) throws DataException {
        return Currency.of(currency);
    }

    @Override
    public Class<String> getBotClass() {
        return String.class;
    }

    @Override
    public Class<Currency> getExternalObjectClass() {
        return Currency.class;
    }

    @Override
    public String getConverterName() {
        return "CurrencyConverter";
    }
}
