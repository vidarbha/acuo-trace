package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.Transformer;
import com.acuo.collateral.transform.trace.services.Trace;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseTransformer<T> implements Transformer<T> {

    private final Trace mapper;

    public BaseTransformer() {
        this.mapper = new Trace();
        try {
            mapper.fromCmeFile(null);
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while initialising the clarus mapper");
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    public BaseTransformer(Trace mapper) {
        this.mapper = mapper;
    }

    public Trace getMapper() {
        return mapper;
    }
}
