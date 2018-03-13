package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.TransformerOutput;
import com.acuo.collateral.transform.inputs.ClarusInput;
import com.acuo.collateral.transform.inputs.Envelop;
import com.acuo.collateral.transform.trace.transformer_valuations.FromClarusOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_valuations.Mapper;
import com.acuo.collateral.transform.trace.transformer_valuations.ToCmeFileNewOutputWrapper;
import com.acuo.collateral.transform.trace.utils.TraceUtils;
import com.acuo.collateral.transform.utils.OutputBuilder;
import com.acuo.common.model.trade.FRATrade;
import com.acuo.common.model.trade.ProductTrade;
import com.acuo.common.model.trade.SwapTrade;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static com.acuo.common.util.ArgChecker.notNull;

@Slf4j
public class ClarusTransformer<INPUT extends ProductTrade, OUTPUT> extends BaseTransformer<INPUT, OUTPUT> {

    @Inject
    private Mapper mapper = null;

    @Override
    public String serialise(List<INPUT> value, TransformerContext context) {
        try {
            List<Envelop> envelops = value.stream()
                    .map(t -> {
                        Envelop envelop = new Envelop();
                        envelop.setType(t.getType());
                        switch (t.getType()) {
                            case FRA:
                                envelop.setFRATrade((FRATrade) t);
                                break;
                            case SWAP:
                                envelop.setSwapTrade((SwapTrade) t);
                                break;
                            default:
                                throw new UnsupportedOperationException();
                        }
                        return envelop;
                    })
                    .collect(Collectors.toList());
            ClarusInput input = new ClarusInput();
            input.setEnvelops(envelops);
            input.setContext(context);
            ToCmeFileNewOutputWrapper outputWrapper = mapper.toCmeFileNew(input);
            return outputWrapper.getOutput();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of swaps", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public TransformerOutput<OUTPUT> deserialiseToList(String values) {
        values = TraceUtils.replaceNewLineToWindows(notNull(values, "values"));
        try {
            FromClarusOutputWrapper output = mapper.fromClarus(values);
            OutputBuilder<OUTPUT> outputBuilder = OutputBuilder.of(output.getOutput(), output.getError());
            return outputBuilder.build();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of swaps", values);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}