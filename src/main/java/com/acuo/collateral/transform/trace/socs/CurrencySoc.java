package com.acuo.collateral.transform.trace.socs;

import com.tracegroup.transformer.externalobjects.socs.SOCImplementingTBean;
import com.tracegroup.transformer.mom.DataException;
import com.tracegroup.transformer.mom.bots.Currency;

public class CurrencySoc  extends SOCImplementingTBean<Currency, com.opengamma.strata.basics.currency.Currency> {
    @Override
    public Object transformerFromExternalObject(com.opengamma.strata.basics.currency.Currency currency) throws DataException {
        return new Currency(currency.getCode());
    }

    @Override
    public com.opengamma.strata.basics.currency.Currency externalObjectFromTransformer(Currency currency) throws DataException {
        return com.opengamma.strata.basics.currency.Currency.of(currency.toString());
    }

    @Override
    public Class<Currency> getBotClass() {
        return Currency.class;
    }

    @Override
    public Class<com.opengamma.strata.basics.currency.Currency> getExternalObjectClass() {
        return com.opengamma.strata.basics.currency.Currency.class;
    }
}
