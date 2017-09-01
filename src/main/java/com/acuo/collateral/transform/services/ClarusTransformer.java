package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.trace.transformer_valuations.FromClarusOutputWrapper;
import com.acuo.collateral.transform.trace.transformer_valuations.Mapper;
import com.acuo.collateral.transform.trace.transformer_valuations.ToCmeFileOutputWrapper;
import com.acuo.collateral.transform.trace.utils.TraceUtils;
import com.acuo.common.util.ArgChecker;
import com.google.common.collect.ImmutableList;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ClarusTransformer<T> extends BaseTransformer<T> {

    public ClarusTransformer(Mapper mapper) {
        super(mapper);
    }

    @Override
    public String serialise(T value, TransformerContext context) {
        return serialise(ImmutableList.of(value), context);
    }

    @Override
    public String serialise(List<T> value, TransformerContext context) {
        try {
            ToCmeFileOutputWrapper outputWrapper = getMapper().toCmeFile(value.toArray(), context, null);
            return outputWrapper.getOutput();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data {} to a list of swaps", value);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public T deserialise(String value) {
        return null;
    }

    @Override
    public List<T> deserialiseToList(String values) {
        ArgChecker.notNull(values, "values");
        values = TraceUtils.replaceNewLineToWindows(values);
        try {
            FromClarusOutputWrapper output = getMapper().fromClarus(values);
            return Arrays.stream(output.getOutput()).map(value -> (T) value).collect(Collectors.toList());
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data {} to a list of swaps", values);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}
