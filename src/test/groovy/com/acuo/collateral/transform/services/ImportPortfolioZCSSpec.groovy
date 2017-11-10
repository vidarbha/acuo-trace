package com.acuo.collateral.transform.services

import com.acuo.collateral.transform.Transformer
import com.acuo.collateral.transform.TransformerContext
import com.acuo.collateral.transform.TransformerOutput
import com.acuo.collateral.transform.modules.TransformerModule
import com.acuo.common.model.results.Error
import com.acuo.common.model.trade.SwapTrade
import com.acuo.common.util.ResourceFile
import com.google.common.collect.ImmutableList
import com.google.inject.Injector
import org.apache.commons.io.IOUtils
import org.junit.Rule
import spock.guice.UseModules
import spock.lang.Specification

import javax.inject.Inject
import java.time.LocalDate

@UseModules(TransformerModule)
class ImportPortfolioZCSSpec extends Specification {

    TransformerContext context = null

    @Inject
    Injector injector

    @Rule
    public ResourceFile zcsTradeFile = new ResourceFile("/portfolio/OneZCS.xlsx")

    void setup() {
        context = new TransformerContext()
        context.setValueDate(LocalDate.now())
    }

    def "Deserialize Vanilla ZC Swap"() {
        given:
        Transformer<String, SwapTrade> transformer = injector.getInstance(PortfolioImportTransformer.class)
        TransformerOutput<SwapTrade> output = transformer.deserialise(IOUtils.toByteArray(zcsTradeFile.getInputStream()))

        when:
        List<SwapTrade> trades = output.results()
        List<Error> errors = output.errors()

        then:
        trades != null
        trades.size() == 1

        errors != null
        errors.size() == 0
    }

    def "Serialise Vanilla ZC Swap into CSV string"() {
        given:
        Transformer<String, SwapTrade> portfolioImportTransformer = injector.getInstance(PortfolioImportTransformer.class)
        TransformerOutput<SwapTrade> output = portfolioImportTransformer.deserialise(IOUtils.toByteArray(zcsTradeFile.getInputStream()))
        SwapTrade trade = output.results().first()

        Transformer<SwapTrade, String> clarusTransformer = injector.getInstance(ClarusTransformer.class)

        when:
        String csv = clarusTransformer.serialise(ImmutableList.of(trade), context)

        then:
        csv != null
    }
}
