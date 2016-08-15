package com.acuo.collateral.transform.services;

import com.acuo.common.model.IrSwap;
import com.acuo.common.model.Request;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TraceDataMapperTest {

    @Rule
    public ResourceFile itrs43 = new ResourceFile("/cme/ITRS-43.csv");

    @Rule
    public ResourceFile itrs100 = new ResourceFile("/cme/ITRS-100.csv");

    DataMapper mapper;

    @Before
    public void setup() throws MomException, StructureException, RuleException, UnrecognizedMessageException {
        mapper = new TraceDataMapper();
    }

    @Test
    @Ignore
    public void testCmeToAcuoAndBack() throws IOException {
        List<IrSwap> trades = mapper.fromCmeFile(itrs43.getContent());
        assertThat(trades.size()).isEqualTo(43);

        Request request = new Request();
        request.setValueDate(LocalDate.now());
        String file = mapper.toCmeFile(trades,request);
        assertThat(file).isEqualTo(itrs43.getContent());

    }

    @Test
    public void testCmeToAcuo100() throws IOException {
        List<IrSwap> trades = mapper.fromCmeFile(itrs100.getContent());
        assertThat(trades.size()).isEqualTo(100);
    }

    @Test
    public void testCmeToAcuo101() throws IOException {
        List<IrSwap> trades = mapper.fromCmeFile(itrs100.getContent());
        assertThat(trades.size()).isEqualTo(100);
    }
}
