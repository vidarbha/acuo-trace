package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformerOutput;
import com.acuo.collateral.transform.trace.transformer_portfolio.ExtractNPVOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_portfolio.Service;
import com.acuo.collateral.transform.utils.OutputBuilder;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
public class TradeValuationTransformer<INPUT, OUTPUT> extends BaseTransformer<INPUT,OUTPUT> {

    @Inject
    private Service mapper = null;

    @Override
    public TransformerOutput<OUTPUT> deserialise(byte[] input) {
        try {
            ExtractNPVOutputWrapper output = mapper.extractNPV(input);
            OutputBuilder<OUTPUT> outputBuilder = OutputBuilder.of(output.getOutput(), output.getErrors());
            return outputBuilder.build();
        } catch (Exception e) {
            log.error("Exception in TradeValuationTransformer", e);
            throw new RuntimeException("Exception in TradeValuationTransformer", e);
        }
    }
}
