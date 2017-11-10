package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.inputs.Envelop;
import com.acuo.collateral.transform.inputs.ValuationInput;
import com.acuo.collateral.transform.trace.transformer_markit.Markit;
import com.acuo.collateral.transform.trace.transformer_markit.ToMarkitOutputWrapper;
import com.acuo.collateral.transform.utils.InputBuilder;
import com.acuo.common.model.trade.ProductTrade;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;

@Slf4j
public class MarkitTransformer<INPUT extends ProductTrade, OUTPUT> extends BaseTransformer<INPUT, OUTPUT> {

    @Inject
    private Markit service = null;

    @Override
    public String serialise(List<INPUT> value, TransformerContext context) {
        try {
            List<Envelop> envelops = InputBuilder.envelops(value);
            ValuationInput input = new ValuationInput();
            input.setEnvelops(envelops);
            input.setContext(context);
            ToMarkitOutputWrapper outputWrapper = service.toMarkit(input);
            return outputWrapper.getOutput();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of swaps", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}

