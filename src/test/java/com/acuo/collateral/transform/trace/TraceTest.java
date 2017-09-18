package com.acuo.collateral.transform.trace;

import com.acuo.collateral.transform.TransformerContext;
import com.acuo.collateral.transform.inputs.ClarusInput;
import com.acuo.collateral.transform.inputs.Envelop;
import com.acuo.collateral.transform.trace.transformer_valuations.Mapper;
import com.acuo.collateral.transform.trace.utils.TraceUtils;
import com.acuo.common.model.trade.ProductType;
import com.acuo.common.model.trade.SwapTrade;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TraceTest {

    @Rule
    public ResourceFile itrsOne = new ResourceFile("/cme/IRSTR-1.csv");

    @Rule
    public ResourceFile itrs43 = new ResourceFile("/cme/IRSTR-43.csv");

    Mapper trace = new Mapper();

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

        final List<Envelop> envelops = Arrays.stream(trades).map(o -> {
            Envelop envelop = new Envelop();
            envelop.setSwapTrade((SwapTrade) o);
            envelop.setType(ProductType.SWAP);
            return envelop;
        })
                .collect(Collectors.toList());
        ClarusInput clarusInput = new ClarusInput();
        clarusInput.setContext(context);
        clarusInput.setEnvelops(envelops);
        String output = trace.toCmeFileNew(clarusInput).getOutput();
        assertThat(output).isNotEmpty();
    }

    @Test
    public void testCmeToAcuoToMarkit() throws IOException, MomException, StructureException, RuleException, UnrecognizedMessageException {
        String input = TraceUtils.replaceNewLineToWindows(itrsOne.getContent());
        Object[] trades = trace.fromCmeFile(input).getSwap();
        assertThat(trades.length).isEqualTo(1);

        String output = trace.toIRSMarkit(trades, context).getOutput();
        assertThat(output).isNotEmpty();
    }

    @Test
    @Ignore
    public void testCmeToAcuoAndBackWith43() throws IOException, MomException, StructureException, RuleException, UnrecognizedMessageException {
        String input = TraceUtils.replaceNewLineToWindows(itrs43.getContent());
        Object[] trades = trace.fromCmeFile(input).getSwap();
        assertThat(trades.length).isEqualTo(43);

        String output = trace.toCmeFile(trades, context, null).getOutput();
        assertThat(output).isEqualTo(itrs43.getContent());
    }
}
