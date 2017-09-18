package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.TransformerOutput;
import com.acuo.collateral.transform.trace.transformer_assets.FromReutersOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_assets.Reuters;
import com.acuo.collateral.transform.trace.transformer_assets.ToReutersOutputWrapper;
import com.acuo.collateral.transform.trace.utils.TraceUtils;
import com.acuo.collateral.transform.utils.OutputBuilder;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;

import static com.acuo.common.util.ArgChecker.notNull;

@Slf4j
public class ReutersTransformer<INPUT, OUTPUT> extends BaseTransformer<INPUT, OUTPUT> {

    @Inject
    private Reuters reuters = null;

    @Override
    public String serialise(List<INPUT> value, TransformerContext context) {
        try {
            ToReutersOutputWrapper outputWrapper = reuters.toReuters(value.toArray());
            return outputWrapper.getReutersInput();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of assets", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public TransformerOutput<OUTPUT> deserialiseToList(String values) {
        values = TraceUtils.replaceNewLineToWindows(notNull(values, "values"));
        try {
            FromReutersOutputWrapper output = reuters.fromReuters(values);
            OutputBuilder<OUTPUT> outputBuilder = OutputBuilder.of(output.getOutput(), output.getError());
            return outputBuilder.build();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of assets", values);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}
