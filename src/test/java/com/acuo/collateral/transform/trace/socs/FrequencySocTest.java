package com.acuo.collateral.transform.trace.socs;

import com.opengamma.strata.basics.schedule.Frequency;
import org.junit.Test;

import static com.opengamma.strata.basics.schedule.Frequency.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FrequencySocTest {

    private FrequencySoc soc = new FrequencySoc();

    @Test
    public void transformerFromExternalObject() throws Exception {

        Object o = soc.transformerFromExternalObject(P4W);
        assertThat(o).isNotNull().isEqualTo("P4W");
    }

    @Test
    public void externalObjectFromTransformer() throws Exception {
        Frequency frequency = soc.externalObjectFromTransformer("4W");
        assertThat(frequency).isNotNull().isEqualTo(P4W);
    }

    @Test
    public void getExternalObjectClass() throws Exception {

    }

}