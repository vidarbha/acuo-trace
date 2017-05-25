package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.trace.transformer_valuations.Mapper;
import com.acuo.collateral.transform.trace.transformer_valuations.PortfolioImportOutputWrapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

@Slf4j
public class PortfolioImportTransformer<T> extends BaseTransformer<T> {

    public PortfolioImportTransformer(Mapper mapper) {
        super(mapper);
    }

    @Override
    public T deserialise(String value) {
        return null;
    }

    @Override
    public List<T> deserialiseToList(String values) {
        return null;
    }

    @Override
    public String serialise(List<T> value, TransformerContext context) {
        return null;
    }

    @Override
    public String serialise(T value, TransformerContext context) {
        return null;
    }

    @Override
    public List<T> deserialise(byte[] input) {
        try {
            PortfolioImportOutputWrapper portfolioImportOutputWrapper = getMapper().portfolioImport(input);
            final Object[] irsb = portfolioImportOutputWrapper.getIRSB();
            final Object[] irsc = portfolioImportOutputWrapper.getIRSC();
            final Object[] oisc = portfolioImportOutputWrapper.getOISC();
            final Object[] frac = portfolioImportOutputWrapper.getFRAC();
            return Stream.concat(
                    Stream.concat(
                            Stream.concat(stream(irsb), stream(irsc)), stream(oisc)
                    ), stream(frac)
            ).map(value -> (T) value)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Exception in PortfolioImportTransformer", e);
            throw new RuntimeException("Exception in PortfolioImportTransformer", e);
        }
    }

}
