package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.TransformerOutput;
import com.acuo.collateral.transform.trace.transformer_margin.AgreeCallOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_margin.AgreeCallResponseOutputWrapper;
import com.acuo.collateral.transform.utils.OutputBuilder;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AgreeTransformer<T> extends BaseMarginCallTransformer<T> {

    @Override
    public String serialise(List<T> value, TransformerContext context) {
        try {
            AgreeCallOutputWrapper outputWrapper = marginCall.agreeCall(value.toArray());
            return outputWrapper.getMarginCalls();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of margin calls", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public TransformerOutput<T> deserialiseToList(String values) {
        try {
            AgreeCallResponseOutputWrapper output = marginCall.agreeCallResponse(values);
            OutputBuilder<T> outputBuilder = OutputBuilder.of(output.getOutput(), output.getMSError());
            return outputBuilder.build();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of margin calls", values);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}
