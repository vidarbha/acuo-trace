package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.Transformer;
import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.Types;
import com.acuo.collateral.transform.trace.transformer_margin.AgreeCallOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_margin.CreateCallOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_margin.MarginCall;
import com.google.common.collect.ImmutableList;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MarginSphereTransformer<T> implements Transformer<T> {

    private final MarginCall marginCall;

    public MarginSphereTransformer(MarginCall marginCall) {
        this.marginCall = marginCall;
    }

    @Override
    public String serialise(T value, TransformerContext context) {
        return serialise(ImmutableList.of(value), context);
    }

    @Override
    public String serialise(List<T> value, TransformerContext context) {
        try {
            Types.MarginCallType mcType = context.getMarginCallType();
            switch (mcType) {
                case Create:
                    CreateCallOutputWrapper outputWrapper = marginCall.createCall(value.toArray());
                    return outputWrapper.getOutput();
                case Agree:
                    AgreeCallOutputWrapper agreeOutputWrapper = marginCall.agreeCall(value.toArray());
                    return agreeOutputWrapper.getMarginCalls();
            }
            return null;
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data {} to a list of swaps", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public T deserialise(String value) {
        return null;
    }

    @Override
    public List<T> deserialiseToList(String values) {
        return null;
    }
}
