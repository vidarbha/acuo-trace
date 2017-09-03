package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.trace.transformer_valuations.ExtractNPVOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_valuations.Mapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class TradeValuationTransformer<T> extends BaseTransformer<T> {

    public TradeValuationTransformer(Mapper mapper) {
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
            ExtractNPVOutputWrapper extractNPVOutputWrapper = getMapper().extractNPV(input);
            return Stream.concat(Arrays.stream(extractNPVOutputWrapper.getOutput()), Arrays.stream(extractNPVOutputWrapper.getErrors()))
                    .map(value -> (T) value).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Exception in TradeValuationTransformer", e);
            throw new RuntimeException("Exception in TradeValuationTransformer", e);
        }
    }
}
