package com.acuo.collateral.transform.modules;

import com.acuo.collateral.transform.trace.transformer_agreement.MarginAgreement;
import com.acuo.collateral.transform.trace.transformer_clarus.Clarus;
import com.acuo.collateral.transform.trace.transformer_cme.Cme;
import com.acuo.collateral.transform.trace.transformer_margin.MarginCall;
import com.acuo.collateral.transform.trace.transformer_markit.Markit;
import com.acuo.collateral.transform.trace.transformer_portfolio.Portfolio;
import com.acuo.collateral.transform.trace.transformer_reuters.Reuters;
import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransformerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MarginCall.class);
        bind(Clarus.class);
        bind(Markit.class);
        bind(Reuters.class);
        bind(MarginAgreement.class);
        bind(Cme.class);
        bind(Portfolio.class);
    }
}
