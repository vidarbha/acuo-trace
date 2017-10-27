package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.TransformerOutput;
import com.acuo.collateral.transform.trace.transformer_reuters.FromAssetOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_reuters.Reuters;
import com.acuo.collateral.transform.trace.transformer_reuters.ToAssetOutputWrapper;
import com.acuo.collateral.transform.trace.utils.TraceUtils;
import com.acuo.collateral.transform.utils.OutputBuilder;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ReutersAssetTransformer<INPUT, OUTPUT> extends BaseTransformer<INPUT, OUTPUT> {

    @Inject
    private Reuters service = null;

    @Override
    public String serialise(List<INPUT> value, TransformerContext context) {
        try {
            final ToAssetOutputWrapper outputWrapper = service.toAsset(value.toArray(), context);
            return outputWrapper.getReutersInput();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of assets", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public TransformerOutput<OUTPUT> deserialiseToList(String values) {
        values = TraceUtils.replaceNewLineToWindows(Objects.requireNonNull(values, "values"));
        try {
            final FromAssetOutputWrapper output = service.fromAsset(values);
            OutputBuilder<OUTPUT> outputBuilder = OutputBuilder.of(output.getOutput(), output.getError());
            return outputBuilder.build();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of assets", values);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}
