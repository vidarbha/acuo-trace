package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.trace.transformer_valuations.Mapper;
import com.acuo.collateral.transform.trace.transformer_valuations.PortfolioImportOutputWrapper;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<T> deserialise(byte[] input)
    {
        try {
            PortfolioImportOutputWrapper portfolioImportOutputWrapper = getMapper().portfolioImport(input);
            return Arrays.stream(portfolioImportOutputWrapper.getOutput()).map(value -> (T) value).collect(Collectors.toList());
        }
        catch (Exception e)
        {
            log.error("Exception in PortfolioImportTransformer" , e);
            throw new RuntimeException("Exception in PortfolioImportTransformer", e);
        }
    }

}
