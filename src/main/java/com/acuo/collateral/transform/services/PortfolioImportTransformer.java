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
            final Object[] irsb_errors = portfolioImportOutputWrapper.getIRSBError();
            Stream<Object> irsb_stream = Stream.concat(stream(irsb), stream(irsb_errors));

            final Object[] irsc = portfolioImportOutputWrapper.getIRSC();
            final Object[] irsc_errors = portfolioImportOutputWrapper.getIRSCError();
            Stream<Object> irsc_stream = Stream.concat(stream(irsc), stream(irsb_errors));

            final Object[] oisc = portfolioImportOutputWrapper.getOISC();
            final Object[] oisc_errors = portfolioImportOutputWrapper.getOISCError();
            Stream<Object> oisc_stream = Stream.concat(stream(oisc), stream(oisc_errors));

            final Object[] frac = portfolioImportOutputWrapper.getFRAC();
            final Object[] frac_errors = portfolioImportOutputWrapper.getFRACError();
            Stream<Object> frac_stream = Stream.concat(stream(frac), stream(frac_errors));

            final Object[] fxsw = portfolioImportOutputWrapper.getFXSW();
            final Object[] fxsw_errors = portfolioImportOutputWrapper.getFXSWError();
            Stream<Object> fxsw_stream = Stream.concat(stream(fxsw), stream(fxsw_errors));

            return Stream.concat(
                    Stream.concat(
                            Stream.concat(
                                    Stream.concat(irsb_stream, irsc_stream), oisc_stream
                            ), frac_stream)
                    , fxsw_stream).map(value -> (OUTPUT) value).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Exception in PortfolioImportTransformer", e);
            throw new RuntimeException("Exception in PortfolioImportTransformer", e);
        }
    }
}
