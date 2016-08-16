package com.acuo.collateral.transform.services;

import com.acuo.collateral.transform.trace.services.FromCmeFileOutputWrapper;
import com.acuo.collateral.transform.trace.services.Trace;
import com.acuo.common.model.IrSwap;
import com.acuo.common.model.Request;
import com.acuo.common.util.ArgChecker;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TraceDataMapper implements DataMapper {

    private final static Logger LOG = LoggerFactory.getLogger(TraceDataMapper.class);

    private final Trace mapper;

    public TraceDataMapper() {
        mapper = new Trace();
    }

    public TraceDataMapper(Trace mapper) {
        this.mapper = mapper;
        try {
            mapper.fromCmeFile(null);
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while initialising the data mapper");
            LOG.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public List<IrSwap> fromCmeFile(String data) {
        ArgChecker.notNull(data, "data");
        data = replaceNewLineToWindows(data);
        try {
            FromCmeFileOutputWrapper output = mapper.fromCmeFile(data);
            return Arrays.stream(output.getSwap()).map(IrSwap.class::cast).collect(Collectors.toList());
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the data {} to a list of swaps", data);
            LOG.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public String toCmeFile(List<IrSwap> swaps, LocalDate valueDate) {
        ArgChecker.notEmpty(swaps, "swaps");
        ArgChecker.notNull(valueDate, "valueDate");
        try {
            Request request = new Request();
            request.setValueDate(valueDate);
            return mapper.toCmeFile(swaps.toArray(),request).getOutput();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the list {} and value date {} to cme file", swaps, valueDate);
            LOG.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public String toMarkitPvRequest(List<IrSwap> swaps, LocalDate valueDate) {
        ArgChecker.notEmpty(swaps, "swaps");
        ArgChecker.notNull(valueDate, "valueDate");
        try {
            Request request = new Request();
            request.setValueDate(valueDate);
            return mapper.toMarkitPvRequest(swaps.toArray(),request).getOutput();
        } catch (MomException | RuleException | UnrecognizedMessageException | StructureException e) {
            String msg = String.format("error occurred while mapping the list {} and value date {} to markit pv request", swaps, valueDate);
            LOG.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    private final String DOS = "\r\n", NIX = "\n", MAC = "\r";

    private String replaceNewLineToWindows(String original) {
        return original.replace(DOS, NIX)
                .replace(MAC, NIX)
                .replace(NIX, DOS);
    }
}
