package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.TransformerOutput;
import com.acuo.collateral.transform.services.BaseTransformFrom;
import com.acuo.collateral.transform.trace.transformer_margin.FromResponseOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_margin.MarginCall;
import com.acuo.collateral.transform.utils.OutputBuilder;
import com.acuo.marginsphere.Response;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
public class MarginCallTransformer extends BaseTransformFrom<Response> {

    @Inject
    protected MarginCall marginCall = null;

    @Override
    public final TransformerOutput<Response> deserialise(String value) {
        return deserialiseToList(value);
    }

    @Override
    public TransformerOutput<Response> deserialiseToList(String values) {
        try {
            final FromResponseOutputWrapper output = marginCall.fromResponse(values);
            OutputBuilder<Response> outputBuilder = OutputBuilder.of(output.getResponse(), output.getMSError());
            return outputBuilder.build();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of margin calls", values);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

}
