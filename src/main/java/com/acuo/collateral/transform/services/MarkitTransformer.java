package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.trace.transformer_markit.Service;
import com.acuo.collateral.transform.trace.transformer_markit.ToIRSMarkitOutputWrapper;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;

@Slf4j
public class MarkitTransformer<INPUT, OUTPUT> extends BaseTransformer<INPUT, OUTPUT> {

    @Inject
    private Service service = null;

    @Override
    public String serialise(List<INPUT> value, TransformerContext context) {
        try {
            ToIRSMarkitOutputWrapper outputWrapper = service.toIRSMarkit(value.toArray(), context);
            return outputWrapper.getOutput();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of swaps", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}

