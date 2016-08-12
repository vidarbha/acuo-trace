package com.acuo.collateral.trace.beans;

import com.opengamma.strata.basics.currency.Currency;
import lombok.Data;

import java.time.LocalDate;

@Data
public abstract class BaseTrade implements Trade {

    private String tradeId;
    private LocalDate tradeDate;
    private String clearedTradeId;
    private LocalDate clearedTradeDate;
    private String book;
    private Currency currency;
    private ProductType productType;
    private TradeStatus tradeStatus;

}
