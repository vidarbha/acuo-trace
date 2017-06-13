package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.Transformer;
import com.acuo.collateral.transform.trace.transformer_assets.Reuters;
import com.acuo.collateral.transform.trace.transformer_valuations.Mapper;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class BaseTransformer<T> implements Transformer<T> {

    private final Mapper mapper;
    private final Reuters reuters;

    public BaseTransformer() {
        this.mapper = new Mapper();
        this.reuters = new Reuters();
        try {
            mapper.fromCmeFile(null);
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while initialising the clarus mapper");
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    public BaseTransformer(Mapper mapper) {
        this.mapper = mapper;
        this.reuters = new Reuters();
    }

    public Mapper getMapper() {
        return mapper;
    }

    public Reuters getReuters()
    {
        return reuters;
    }

    @Override
    public List<T> deserialise(byte[] value)
    {
        return null;
    }
}
