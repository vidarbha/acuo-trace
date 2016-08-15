package com.acuo.collateral.transform.services;

import com.acuo.common.model.IrSwap;

import java.time.LocalDate;
import java.util.List;

public interface DataMapper {

    List<IrSwap> fromCmeFile(String data);

    String toCmeFile(List<IrSwap> swaps, LocalDate valueDate);
}
