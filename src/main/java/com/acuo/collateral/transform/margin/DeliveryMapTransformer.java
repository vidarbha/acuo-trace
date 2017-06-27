package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.Types;
import com.acuo.collateral.transform.trace.transformer_margin.CallDeliveryMapUpdateOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_margin.MarginCall;
import com.google.common.collect.ImmutableList;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class DeliveryMapTransformer<T> extends BaseMarginCallTransformer<T> {


    public DeliveryMapTransformer(MarginCall marginCall) {
        super(marginCall);
    }

    @Override
    public String serialise(T value, TransformerContext context) {
        return serialise(ImmutableList.of(value), context);
    }

    @Override
    public String serialise(List<T> value, TransformerContext context) {
        try {

            CallDeliveryMapUpdateOutputWrapper outputWrapper = getMarginCall().callDeliveryMapUpdate(value.toArray());
            return outputWrapper.getOutput();
            } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
                String msg = String.format("error occurred while mapping the data {} to a list of margin calls", value);
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
