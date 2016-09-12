package com.acuo.collateral.learning;

import com.opengamma.strata.product.swap.FixingRelativeTo;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FixingRelativeToTest {

    @Test
    public void testParse() {
        FixingRelativeTo periodStart = FixingRelativeTo.of("PeriodStart");
        assertThat(periodStart).isEqualTo(FixingRelativeTo.PERIOD_START);

        assertThat(FixingRelativeTo.PERIOD_START.toString()).isEqualTo("PeriodStart");
    }

}
