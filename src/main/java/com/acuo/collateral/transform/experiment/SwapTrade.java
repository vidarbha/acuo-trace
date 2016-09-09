package com.acuo.collateral.transform.experiment;

import com.opengamma.strata.product.common.BuySell;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SwapTrade {

    private TradeInfo info;
    private BaseConvention convention;

    @Data
    public static class TradeInfo {
        private LocalDate tradeDate;
        private LocalDate startDate;
        private LocalDate endDate;
        private BuySell buySell;
        private double notional;
    }

}
