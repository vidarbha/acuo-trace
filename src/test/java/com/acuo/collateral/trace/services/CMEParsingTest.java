package com.acuo.collateral.trace.services;

import com.acuo.collateral.basics.model.IrSwap;
import com.acuo.collateral.basics.model.Request;
import com.acuo.common.util.ResourceFile;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CMEParsingTest {

    @Rule
    public ResourceFile itrs43 = new ResourceFile("/cme/ITRS-43.csv");

    @Rule
    public ResourceFile itrs100 = new ResourceFile("/cme/ITRS-100.csv");

    Trace mapper;

    @Before
    public void setup() throws MomException, StructureException, RuleException, UnrecognizedMessageException {
        mapper = new Trace();
        mapper.fromCmeFile(null);
    }

    @Test
    public void testCmeToAcuoAndBack() throws MomException, StructureException, RuleException, UnrecognizedMessageException, IOException {
        FromCmeFileOutputWrapper parse = mapper.fromCmeFile(itrs43.getContent());
        List<IrSwap> trades = Arrays.stream(parse.getSwap()).map(IrSwap.class::cast).collect(Collectors.toList());
        assertThat(trades.size()).isEqualTo(43);

        Request request = new Request();
        request.setValueDate(LocalDate.now());
        String file = mapper.toCmeFile(trades.toArray(),request).getOutput();
        assertThat(file).isEqualTo(itrs43.getContent());

    }

    @Test
    public void testCmeToAcuo100() throws MomException, StructureException, RuleException, UnrecognizedMessageException, IOException {
        FromCmeFileOutputWrapper parse = mapper.fromCmeFile(itrs100.getContent());
        List<IrSwap> trades = Arrays.stream(parse.getSwap()).map(IrSwap.class::cast).collect(Collectors.toList());
        assertThat(trades.size()).isEqualTo(100);
    }

    @Test
    public void testCmeToAcuo101() throws MomException, StructureException, RuleException, UnrecognizedMessageException, IOException {
        FromCmeFileOutputWrapper parse = mapper.fromCmeFile(itrs100.getContent());
        List<IrSwap> trades = Arrays.stream(parse.getSwap()).map(IrSwap.class::cast).collect(Collectors.toList());
        assertThat(trades.size()).isEqualTo(100);
    }
}
