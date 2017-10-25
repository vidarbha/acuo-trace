package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformerOutput;
import com.acuo.collateral.transform.trace.transformer_portfolio.PortfolioImportOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_portfolio.Service;
import com.acuo.collateral.transform.utils.OutputBuilder;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
public class PortfolioImportTransformer<INPUT, OUTPUT> extends BaseTransformer<INPUT,OUTPUT> {

    @Inject
    private Service service = null;

    @Override
    public TransformerOutput<OUTPUT> deserialise(byte[] input) {
        try {
            PortfolioImportOutputWrapper output = service.portfolioImport(input);
            OutputBuilder<OUTPUT> outputBuilder = OutputBuilder
                    .of(output.getIRSB(), output.getIRSBError());

            outputBuilder.addResults(output.getIRSC())
                    .addErrors(output.getIRSCError())
                    .addResults(output.getOISC())
                    .addErrors(output.getOISCError())
                    .addResults(output.getFRAC())
                    .addErrors(output.getFRACError())
                    .addResults(output.getFXSW())
                    .addErrors(output.getFXSWError())
                    .addResults(output.getZCSC())
                    .addErrors(output.getZCSError());

            return outputBuilder.build();
        } catch (Exception e) {
            log.error("Exception in PortfolioImportTransformer", e);
            throw new RuntimeException("Exception in PortfolioImportTransformer", e);
        }
    }
}
