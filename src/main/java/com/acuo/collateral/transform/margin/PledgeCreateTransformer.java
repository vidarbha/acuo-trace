package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.trace.transformer_margin.PledgeCreateOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_margin.PledgeCreateResponseOutputWrapper;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class PledgeCreateTransformer<T> extends BaseMarginCallTransformer<T> {

    @Override
    public String serialise(List<T> value, TransformerContext context) {
        try {
            PledgeCreateOutputWrapper pledgeCreateOutputWrapper = marginCall.pledgeCreate(value.toArray());
            return pledgeCreateOutputWrapper.getOutput();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of margin calls", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public List<T> deserialiseToList(String values) {
        try {
            PledgeCreateResponseOutputWrapper outputs = marginCall.pledgeCreateResponse(values);
            if (Arrays.stream(outputs.getOutput()).count() > 0) {
                return Arrays.asList((T[]) outputs.getOutput());
            } else {
                return Arrays.asList((T[]) outputs.getMSError());
            }
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of margin calls", values);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}
