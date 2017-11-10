package com.acuo.collateral.transform.services

import com.acuo.collateral.transform.TransformerContext
import com.acuo.collateral.transform.modules.TransformerModule
import com.acuo.common.model.product.SwapHelper
import com.acuo.common.model.trade.SwapTrade
import com.acuo.common.util.ResourceFile
import com.google.common.collect.ImmutableList
import org.junit.Rule
import spock.guice.UseModules
import spock.lang.Specification
import spock.lang.Subject

import javax.inject.Inject
import java.time.LocalDate

import static org.apache.commons.io.IOUtils.toByteArray

@UseModules(TransformerModule)
class FxSwapTransformerSpec extends Specification {

    TransformerContext context = null

    @Subject
    @Inject
    MarkitTransformer markitTransformer

    @Inject
    PortfolioImportTransformer portfolioImportTransformer

    @Rule
    public ResourceFile fxSwapFile = new ResourceFile("/portfolio/OneFX.xlsx")

    void setup() {
        context = new TransformerContext()
        context.setValueDate(LocalDate.now())
    }

    def "Serialise FX Swap"() {
        given:
        List<SwapTrade> trades = portfolioImportTransformer.deserialise(toByteArray(fxSwapFile.getInputStream())).results()

        when:
        String xml = markitTransformer.serialise(trades, context)

        then:
        xml != null
    }
}
