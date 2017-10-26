package com.acuo.collateral.transform.services

import com.acuo.collateral.transform.Transformer
import com.acuo.collateral.transform.TransformerContext
import com.acuo.collateral.transform.modules.TransformerModule
import com.acuo.common.model.product.SwapHelper
import com.acuo.common.model.trade.SwapTrade
import com.acuo.common.util.ResourceFile
import com.google.common.collect.ImmutableList
import com.google.inject.Injector
import org.junit.Rule
import spock.guice.UseModules
import spock.lang.Specification

import javax.inject.Inject
import java.time.LocalDate

import static org.apache.commons.io.IOUtils.toByteArray

@UseModules(TransformerModule)
class MarkitTransformerSpec extends Specification {

    TransformerContext context = null

    @Inject
    Injector injector

    @Rule
    public ResourceFile xxcyTradeFile = new ResourceFile("/portfolio/OneXCCY.xlsx")

    void setup() {
        context = new TransformerContext()
        context.setValueDate(LocalDate.now())
    }

    def "Serialise Vanilla Swap"() {
        given:
        Transformer<SwapTrade, String> transformer = injector.getInstance(MarkitTransformer)
        SwapTrade trade = SwapHelper.createTrade()

        when:
        String xml = transformer.serialise(ImmutableList.of(trade), context)

        then:
        xml != null
    }

    def "Serialise XCCY Swap"() {
        given:
        Transformer<SwapTrade, String> markitTransformer = injector.getInstance(MarkitTransformer)
        Transformer<String, SwapTrade> portfolioImportTransformer = injector.getInstance(PortfolioImportTransformer)
        List<SwapTrade> trades = portfolioImportTransformer.deserialise(toByteArray(xxcyTradeFile.getInputStream())).results()

        when:
        String xml = markitTransformer.serialise(trades, context)

        then:
        xml != null
    }
}
