package com.acuo.collateral.transform.modules;

import com.acuo.collateral.transform.trace.transformer_assets.Reuters;
import com.acuo.collateral.transform.trace.transformer_margin.MarginCall;
import com.acuo.collateral.transform.trace.transformer_valuations.Mapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

@Slf4j
public class TransformerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Reuters.class);
        bind(MarginCall.class);
    }

    @Provides
    @Singleton
    Mapper mapper() {
        Mapper mapper = new Mapper();
        try {
            mapper.fromCmeFile(null);
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while initialising the markit/clarus mapper");
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
        return mapper;
    }
}
