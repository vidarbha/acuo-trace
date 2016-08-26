package com.acuo.collateral.transform.trace;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.trace.services.Trace;
import com.acuo.collateral.transform.trace.utils.TraceUtils;
import com.acuo.common.util.ResourceFile;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class TraceTest {

    @Rule
    public ResourceFile itrsOne = new ResourceFile("/cme/IRSTR-1.csv");

    @Rule
    public ResourceFile itrs43 = new ResourceFile("/cme/IRSTR-43.csv");

    Trace trace = new Trace();

    TransformerContext context = null;

    @Before
    public void setup() {
        context = new TransformerContext();
        context.setValueDate(LocalDate.of(2016, 8, 16));
    }

    @Test
    public void testCmeToAcuoAndBackWithOne() throws IOException, MomException, StructureException, RuleException, UnrecognizedMessageException {
        String input = TraceUtils.replaceNewLineToWindows(itrsOne.getContent());
        Object[] trades = trace.fromCmeFile(input).getSwap();
        assertThat(trades.length).isEqualTo(1);

        String output = trace.toCmeFile(trades, context).getOutput();
        assertThat(output).isNotEmpty();
    }

    @Test
    public void testCmeToAcuoToMarkit() throws IOException, MomException, StructureException, RuleException, UnrecognizedMessageException {
        String input = TraceUtils.replaceNewLineToWindows(itrsOne.getContent());
        Object[] trades = trace.fromCmeFile(input).getSwap();
        assertThat(trades.length).isEqualTo(1);

        String output = trace.toMarkitPvRequest(trades, context).getOutput();
        assertThat(output).isNotEmpty();
    }

    @Test
    @Ignore
    public void testCmeToAcuoAndBackWith43() throws IOException, MomException, StructureException, RuleException, UnrecognizedMessageException {
        String input = TraceUtils.replaceNewLineToWindows(itrs43.getContent());
        Object[] trades = trace.fromCmeFile(input).getSwap();
        assertThat(trades.length).isEqualTo(43);

        String output = trace.toCmeFile(trades, context).getOutput();
        assertThat(output).isEqualTo(itrs43.getContent());
    }
}
