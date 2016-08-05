package com.acuo.collateral.trace.beans;

import lombok.Data;

@Data
public class Clarus {

    public String ccp;
    public String reportingCcy;
    public String houseClient;
    public boolean calcAddons;
    public byte[] body;
    public PortfolioData portfolioData;

    @Data
    public static class PortfolioData {

        public String dataType;
        public String dataFormat;
        public String data;
    }

}
