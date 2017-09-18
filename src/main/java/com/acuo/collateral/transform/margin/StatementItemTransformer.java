package com.acuo.collateral.transform.margin;

import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class StatementItemTransformer<T> extends BaseMarginCallTransformer<T> {

    @Override
    public List<T> deserialiseToList(String values) {
        try {
            Object outputs = marginCall.importStatementItem(values).getOutput();
            return Arrays.asList((T[]) outputs);
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of margin calls", values);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}
