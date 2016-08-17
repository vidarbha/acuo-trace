package com.acuo.collateral.transform.services;

import com.acuo.common.model.trade.SwapTrade;

import java.time.LocalDate;
import java.util.List;

public interface DataMapper {

    List<SwapTrade> fromCmeFile(String data);

    String toCmeFile(List<SwapTrade> swaps, LocalDate valueDate);

    String toMarkitPvRequest(List<SwapTrade> swaps, LocalDate valueDate);
}
