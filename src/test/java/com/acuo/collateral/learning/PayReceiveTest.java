package com.acuo.collateral.learning;

import com.opengamma.strata.product.common.PayReceive;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PayReceiveTest {

    @Test
    public void testParse() {
        PayReceive pay = PayReceive.of("Pay");

        assertThat(pay).isEqualTo(PayReceive.PAY);
    }

}
