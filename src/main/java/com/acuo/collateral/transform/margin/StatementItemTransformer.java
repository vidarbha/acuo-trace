package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.TransformerOutput;
import com.acuo.collateral.transform.trace.transformer_margin.ImportStatementItemOutputWrapper;
import com.acuo.collateral.transform.utils.OutputBuilder;
import com.acuo.marginsphere.Response;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StatementItemTransformer extends MarginCallTransformer {

    @Override
    public TransformerOutput<Response> deserialiseToList(String values) {
        try {
            ImportStatementItemOutputWrapper output = marginCall.importStatementItem(values);
            OutputBuilder<Response> outputBuilder = OutputBuilder.of(output.getOutput(), null);
            return outputBuilder.build();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of margin calls", values);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}
