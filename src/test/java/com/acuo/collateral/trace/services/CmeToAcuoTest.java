package com.acuo.collateral.trace.services;

import com.acuo.collateral.trace.beans.Swaps;
import com.acuo.common.util.ResourceFile;
import com.tracegroup.transformer.exposedservices.MomException;
import com.tracegroup.transformer.exposedservices.RuleException;
import com.tracegroup.transformer.exposedservices.StructureException;
import com.tracegroup.transformer.exposedservices.UnrecognizedMessageException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CmeToAcuoTest {

    @Rule
    public ResourceFile minus = new ResourceFile("/cme/cme-minus.csv");

    @Rule
    public ResourceFile cme100 = new ResourceFile("/cme/cme-100.csv");

    CmeToAcuo service;

    @Before
    public void setup() throws MomException, StructureException, RuleException, UnrecognizedMessageException {
        service = new CmeToAcuo();
        service.parse(null);
    }

    @Test
    public void testCmeToAcuo() throws MomException, StructureException, RuleException, UnrecognizedMessageException, IOException {
        ParseOutputWrapper parse = service.parse(minus.getContent());
        Swaps swaps = (Swaps)parse.getOutput();
        assertThat(swaps.getItems().size()).isEqualTo(43);
    }

    @Test
    public void testCmeToAcuo100() throws MomException, StructureException, RuleException, UnrecognizedMessageException, IOException {
        ParseOutputWrapper parse = service.parse(cme100.getContent());
        Swaps swaps = (Swaps)parse.getOutput();
        assertThat(swaps.getItems().size()).isEqualTo(100);
    }

    @Test
    public void testCmeToAcuo101() throws MomException, StructureException, RuleException, UnrecognizedMessageException, IOException {
        ParseOutputWrapper parse = service.parse(cme100.getContent());
        Swaps swaps = (Swaps)parse.getOutput();
        assertThat(swaps.getItems().size()).isEqualTo(100);
    }
}
