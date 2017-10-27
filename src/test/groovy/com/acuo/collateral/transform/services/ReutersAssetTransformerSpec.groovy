package com.acuo.collateral.transform.services

import com.acuo.collateral.transform.TransformerContext
import com.acuo.collateral.transform.TransformerOutput
import com.acuo.collateral.transform.modules.TransformerModule
import com.acuo.common.model.assets.Assets
import com.acuo.common.model.results.AssetValuation
import com.acuo.common.util.ResourceFile
import com.opengamma.strata.basics.currency.Currency
import org.junit.Rule
import spock.guice.UseModules
import spock.lang.Specification
import spock.lang.Subject

import javax.inject.Inject
import java.time.LocalDate

import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson
import static org.junit.Assert.assertThat

@UseModules(TransformerModule)
class ReutersAssetTransformerSpec extends Specification {

    @Rule
    ResourceFile responsjson = new ResourceFile("/reuters/asset/response.json")

    TransformerContext context = null

    @Subject
    @Inject
    ReutersAssetTransformer transformer

    void setup(){
        context = new TransformerContext()
        context.setValueDate(LocalDate.now())
    }

    def "Serialise one asset"() {
        given:
        Assets assets = new Assets(assetId: "1231",
                availableQuantities: 11,
                currency: Currency.USD,
                fitchRating: "1")

        when:
        String json = transformer.serialise([assets], context)

        then:
        json != null

        and:
        assertThat json, isJson()
    }

    def "Deserialize one asset"() {
        when:
        TransformerOutput<AssetValuation> output = transformer.deserialiseToList(responsjson.getContent())

        then:
        with(output.results()) {
            it.size() > 0
            it.get(0) != null
        }
    }
}
