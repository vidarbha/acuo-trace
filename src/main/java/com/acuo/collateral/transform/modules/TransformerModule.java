package com.acuo.collateral.transform.modules;

import com.acuo.collateral.transform.trace.transformer_margin.MarginCall;
import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransformerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MarginCall.class);
        bind(com.acuo.collateral.transform.trace.transformer_clarus.Service.class);
        bind(com.acuo.collateral.transform.trace.transformer_markit.Service.class);
        bind(com.acuo.collateral.transform.trace.transformer_reuters.Service.class);
        bind(com.acuo.collateral.transform.trace.transformer_agreement.MarginAgreement.class);
        bind(com.acuo.collateral.transform.trace.transformer_cme.Service.class);
        bind(com.acuo.collateral.transform.trace.transformer_portfolio.Service.class);
    }
}
