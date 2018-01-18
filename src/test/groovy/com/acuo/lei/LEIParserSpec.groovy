package com.acuo.lei

import com.acuo.common.util.ResourceFile
import com.acuo.lei.parser_level1.LEIParser
import org.junit.Rule
import spock.lang.Ignore
import spock.lang.Specification

class LEIParserSpec extends Specification {

    @Rule
    public ResourceFile smallXml = new ResourceFile("/lei/small-level1.xml")

    @Rule
    public ResourceFile smallOtherNameXml = new ResourceFile("/lei/small-othername-level1.xml")

    void "parse a small xml file to csv"() {
        given:
        LEIParser parser = new LEIParser()

        when:
        String csv = parser.parseCsv(smallXml.content)

        then:
        csv != null
    }

    @Ignore
    void "parse a small xml with other names file to csv"() {
        given:
        LEIParser parser = new LEIParser()

        when:
        String csv = parser.parseCsv(smallOtherNameXml.content)

        then:
        csv != null
    }

}
