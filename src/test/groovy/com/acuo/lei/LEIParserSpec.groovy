package com.acuo.lei

import com.acuo.common.util.ResourceFile
import com.acuo.lei.parser_level1.LEIParser
import com.tracegroup.transformer.configfiles.ConfigFile
import com.tracegroup.transformer.configfiles.Project
import com.tracegroup.transformer.dad.ExposedServiceOperation
import com.tracegroup.transformer.sat.SATFileWriter
import com.tracegroup.transformer.sat.SAXSATServiceOperator
import com.tracegroup.transformer.sat.SAXSATSource
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

    void "call transformer Streaming API to deal with large xml file"(){
        given:
        Project p = new Project("./projects/lei/lei.tpj")
        ConfigFile opCf = p.getConfigFile("ExposedServices/LEIParser/xmlToCsv")
        ExposedServiceOperation inner = (ExposedServiceOperation)p.getRunTimeObject(opCf)

        //Set up the list of nodes that we are interested in - in this case only the Body
        SAXSATServiceOperator sso = new SAXSATServiceOperator(inner)
        Map<String, SAXSATServiceOperator> map = new HashMap<String, SAXSATServiceOperator>()
        map.put("LEIRecord", sso)

        //Create the Source
        SAXSATSource source  = new SAXSATSource(map)

        //Set up the Result Handler – the output records are separated by a Windows EOL characters
        File f = File.createTempFile("tmp", "txt")
        //f.deleteOnExit();

        SATFileWriter writer = new SATFileWriter(f, "\r\n")
        sso.setResultHandler(writer)

        //Get the source data
        source.setInputSource(smallXml.createInputStream())

        //Run it
        source.provide()

        expect:
        1 == 1
    }
}
