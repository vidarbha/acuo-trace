package com.acuo.collateral.learning;

import com.opengamma.strata.basics.ReferenceData;
import com.opengamma.strata.basics.date.BusinessDayAdjustment;
import com.opengamma.strata.basics.date.DaysAdjustment;
import com.opengamma.strata.product.swap.Swap;
import com.opengamma.strata.product.swap.SwapTrade;
import com.opengamma.strata.product.swap.type.FixedIborSwapConvention;
import com.opengamma.strata.product.swap.type.FixedRateSwapLegConvention;
import com.opengamma.strata.product.swap.type.IborRateSwapLegConvention;
import com.opengamma.strata.product.swap.type.ImmutableFixedIborSwapConvention;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static com.opengamma.strata.basics.currency.Currency.GBP;
import static com.opengamma.strata.basics.currency.Currency.USD;
import static com.opengamma.strata.basics.date.BusinessDayConventions.FOLLOWING;
import static com.opengamma.strata.basics.date.BusinessDayConventions.MODIFIED_FOLLOWING;
import static com.opengamma.strata.basics.date.DayCounts.ACT_360;
import static com.opengamma.strata.basics.date.DayCounts.ACT_365F;
import static com.opengamma.strata.basics.date.HolidayCalendarIds.GBLO;
import static com.opengamma.strata.basics.index.IborIndices.GBP_LIBOR_3M;
import static com.opengamma.strata.basics.index.IborIndices.USD_LIBOR_3M;
import static com.opengamma.strata.basics.index.IborIndices.USD_LIBOR_6M;
import static com.opengamma.strata.basics.schedule.Frequency.P3M;
import static com.opengamma.strata.basics.schedule.Frequency.P6M;
import static com.opengamma.strata.product.common.BuySell.BUY;
import static com.opengamma.strata.product.common.PayReceive.PAY;
import static com.opengamma.strata.product.common.PayReceive.RECEIVE;
import static org.junit.Assert.assertEquals;

public class StrataFixing {

    private static final ReferenceData REF_DATA = ReferenceData.standard();
    private static final double NOTIONAL_2M = 2_000_000d;
    private static final BusinessDayAdjustment BDA_FOLLOW = BusinessDayAdjustment.of(FOLLOWING, GBLO);
    private static final BusinessDayAdjustment BDA_MOD_FOLLOW = BusinessDayAdjustment.of(MODIFIED_FOLLOWING, GBLO);
    private static final DaysAdjustment PLUS_ONE_DAY = DaysAdjustment.ofBusinessDays(1, GBLO);

    private static final String NAME = "USD-Swap";
    private static final FixedRateSwapLegConvention FIXED =
            FixedRateSwapLegConvention.of(USD, ACT_360, P6M, BDA_FOLLOW);
    private static final FixedRateSwapLegConvention FIXED2 =
            FixedRateSwapLegConvention.of(GBP, ACT_365F, P3M, BDA_MOD_FOLLOW);
    private static final IborRateSwapLegConvention IBOR = IborRateSwapLegConvention.of(USD_LIBOR_3M);
    private static final IborRateSwapLegConvention IBOR2 = IborRateSwapLegConvention.of(GBP_LIBOR_3M);
    private static final IborRateSwapLegConvention IBOR3 = IborRateSwapLegConvention.of(USD_LIBOR_6M);

    @Test
    public void test_toTrade_dates() {
        FixedIborSwapConvention base = ImmutableFixedIborSwapConvention.of(NAME, FIXED, IBOR);
        LocalDate tradeDate = LocalDate.of(2015, 5, 5);
        LocalDate startDate = LocalDate.of(2015, 8, 5);
        LocalDate endDate = LocalDate.of(2015, 11, 5);
        SwapTrade test = base.toTrade(tradeDate, startDate, endDate, BUY, NOTIONAL_2M, 0.25d);
        Swap expected = Swap.of(
                FIXED.toLeg(startDate, endDate, PAY, NOTIONAL_2M, 0.25d),
                IBOR.toLeg(startDate, endDate, RECEIVE, NOTIONAL_2M));
        assertEquals(test.getInfo().getTradeDate(), Optional.of(tradeDate));
        assertEquals(test.getProduct(), expected);
    }

}

