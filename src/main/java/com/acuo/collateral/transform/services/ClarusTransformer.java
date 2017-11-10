package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.TransformerOutput;
import com.acuo.collateral.transform.inputs.ValuationInput;
import com.acuo.collateral.transform.inputs.Envelop;
import com.acuo.collateral.transform.trace.transformer_clarus.Clarus;
import com.acuo.collateral.transform.trace.transformer_clarus.FromClarusOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_cme.Cme;
import com.acuo.collateral.transform.trace.transformer_cme.ToCmeFileNewOutputWrapper;
import com.acuo.collateral.transform.trace.utils.TraceUtils;
import com.acuo.collateral.transform.utils.InputBuilder;
import com.acuo.collateral.transform.utils.OutputBuilder;
import com.acuo.common.model.trade.FRATrade;
import com.acuo.common.model.trade.FxSwapTrade;
import com.acuo.common.model.trade.ProductTrade;
import com.acuo.common.model.trade.SwapTrade;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class ClarusTransformer<INPUT extends ProductTrade, OUTPUT> extends BaseTransformer<INPUT, OUTPUT> {

    @Inject
    private Cme cme = null;

    @Inject
    private Clarus clarus = null;

    @Override
    public String serialise(List<INPUT> value, TransformerContext context) {
        try {
            List<Envelop> envelops = InputBuilder.envelops(value);
            ValuationInput input = new ValuationInput();
            input.setEnvelops(envelops);
            input.setContext(context);
            ToCmeFileNewOutputWrapper outputWrapper = cme.toCmeFileNew(input);
            return outputWrapper.getOutput();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of swaps", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public TransformerOutput<OUTPUT> deserialiseToList(String values) {
        values = TraceUtils.replaceNewLineToWindows(Objects.requireNonNull(values, "values"));
        try {
            FromClarusOutputWrapper output = clarus.fromClarus(values);
            OutputBuilder<OUTPUT> outputBuilder = OutputBuilder.of(output.getOutput(), output.getError());
            return outputBuilder.build();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of swaps", values);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}