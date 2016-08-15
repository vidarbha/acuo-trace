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
    public ResourceFile itrsOne = new ResourceFile("/cme/ITRS-1.csv");

    @Rule
    public ResourceFile itrsOneExpected = new ResourceFile("/cme/ITRS-1-expected.csv");

    @Rule
    public ResourceFile itrs43 = new ResourceFile("/cme/ITRS-43.csv");

    @Rule
    public ResourceFile itrs43Expected = new ResourceFile("/cme/ITRS-43-expected.csv");

    DataMapper mapper;

    @Before
    public void setup() throws MomException, StructureException, RuleException, UnrecognizedMessageException {
        mapper = new TraceDataMapper();
    }

    @Test
    public void testCmeToAcuoAndBackWithOne() throws IOException {
        List<IrSwap> trades = mapper.fromCmeFile(itrsOne.getContent());
        assertThat(trades.size()).isEqualTo(43);

        String file = mapper.toCmeFile(trades,LocalDate.now());
        assertThat(file).isEqualTo(itrsOneExpected.getContent());
    }

    @Test
    public void testCmeToAcuoAndBackWith43() throws IOException {
        List<IrSwap> trades = mapper.fromCmeFile(itrs43.getContent());
        assertThat(trades.size()).isEqualTo(43);

        String file = mapper.toCmeFile(trades,LocalDate.now());
        assertThat(file).isEqualTo(itrs43Expected.getContent());
    }
}
