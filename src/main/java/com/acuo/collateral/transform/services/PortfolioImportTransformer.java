package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.trace.transformer_valuations.Mapper;
import com.acuo.collateral.transform.trace.transformer_valuations.PortfolioImportOutputWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

@Slf4j
public class PortfolioImportTransformer<INPUT, OUTPUT> extends BaseTransformer<INPUT, OUTPUT> {

    @Inject
    private Mapper mapper = null;

    @Override
    public List<OUTPUT> deserialise(byte[] input) {
        try {
            PortfolioImportOutputWrapper portfolioImportOutputWrapper = mapper.portfolioImport(input);
            final Object[] irsb = portfolioImportOutputWrapper.getIRSB();
            final Object[] irsc = portfolioImportOutputWrapper.getIRSC();
            final Object[] oisc = portfolioImportOutputWrapper.getOISC();
            final Object[] frac = portfolioImportOutputWrapper.getFRAC();
            return Stream.concat(
                    Stream.concat(
                            Stream.concat(stream(irsb), stream(irsc)), stream(oisc)
                    ), stream(frac)
            ).map(value -> (OUTPUT) value)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Exception in PortfolioImportTransformer", e);
            throw new RuntimeException("Exception in PortfolioImportTransformer", e);
        }
    }
}
