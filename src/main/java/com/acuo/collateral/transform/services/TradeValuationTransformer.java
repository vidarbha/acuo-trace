package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.trace.transformer_valuations.ExtractNPVOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_valuations.Mapper;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TradeValuationTransformer<INPUT, OUTPUT> extends BaseTransformer<INPUT, OUTPUT> {

    @Inject
    private Mapper mapper = null;

    @Override
    public List<OUTPUT> deserialise(byte[] input) {
        try {
            ExtractNPVOutputWrapper extractNPVOutputWrapper = mapper.extractNPV(input);
            final Object[] valuations = extractNPVOutputWrapper.getOutput();
            return Arrays.stream(valuations)
                    .map(valuation -> (OUTPUT)valuation)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Exception in TradeValuationTransformer", e);
            throw new RuntimeException("Exception in TradeValuationTransformer", e);
        }
    }
}
