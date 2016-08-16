package com.acuo.collateral.transform.services;

import com.acuo.common.model.IrSwap;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TraceDataMapperTest {

    @Rule
    public ResourceFile itrsOne = new ResourceFile("/cme/IRSTR-1.csv");

     @Rule
    public ResourceFile itrs43 = new ResourceFile("/cme/IRSTR-43.csv");

    DataMapper mapper;

    @Before
    public void setup() throws MomException, StructureException, RuleException, UnrecognizedMessageException {
        mapper = new TraceDataMapper();
    }

    @Test
    public void testCmeToAcuoAndBackWithOne() throws IOException {
        List<IrSwap> trades = mapper.fromCmeFile(itrsOne.getContent());
        assertThat(trades.size()).isEqualTo(1);

        String file = mapper.toCmeFile(trades,LocalDate.of(2016,8,15));
        assertThat(file).isEqualTo(itrsOne.getContent());
    }

    @Test
    public void testCmeToAcuoAndBackWith43() throws IOException {
        List<IrSwap> trades = mapper.fromCmeFile(itrs43.getContent());
        assertThat(trades.size()).isEqualTo(43);

        String file = mapper.toCmeFile(trades,LocalDate.of(2016,8,15));
        assertThat(file).isEqualTo(itrs43.getContent());
    }
}
