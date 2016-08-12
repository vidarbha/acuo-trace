package com.acuo.collateral.trace.beans;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Request {

    LocalDate valueDate;
    Integer clientId;

}
