package com.acuo.collateral.transform.margin;

import com.acuo.collateral.transform.services.BaseTransformFrom;
import com.acuo.collateral.transform.trace.transformer_margin.FromResponseOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_margin.MSMapper;
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
    protected MSMapper mapper = null;

    @Override
    public final Response deserialise(String value) {
        return deserialiseToList(value);
    }

    @Override
    public Response deserialiseToList(String values) {
        try {
            final FromResponseOutputWrapper output = mapper.fromResponse(values);
            return (Response) output.getResponse()[0];
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data %s to a list of margin calls", values);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

}
