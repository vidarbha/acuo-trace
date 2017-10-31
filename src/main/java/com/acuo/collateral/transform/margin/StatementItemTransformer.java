package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.trace.transformer_margin.ImportStatementItemOutputWrapper;
import com.acuo.marginsphere.Response;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StatementItemTransformer extends MarginCallTransformer {

    @Override
    public Response deserialiseToList(String values) {
        try {
            ImportStatementItemOutputWrapper output = mapper.importStatementItem(values);
            return (Response) output.getOutput()[0];
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of margin calls", values);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}
