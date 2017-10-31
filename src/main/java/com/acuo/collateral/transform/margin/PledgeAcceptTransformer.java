package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.trace.transformer_margin.PledgeAcceptOutputWrapper;
import com.acuo.common.model.margin.MarginCall;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PledgeAcceptTransformer extends MarginCallTransformTo {

    @Override
    public String serialise(List<MarginCall> value, TransformerContext context) {
        try {
            PledgeAcceptOutputWrapper pledgeCreateOutputWrapper = mapper.pledgeAccept(value.toArray());
            return pledgeCreateOutputWrapper.getOutput();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of margin calls", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}