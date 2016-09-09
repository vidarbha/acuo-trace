package com.acuo.collateral.transform.trace.lookup;

import com.tracegroup.transformer.configfiles.ConfigUpdater;
import com.tracegroup.transformer.configfiles.Project;
import com.tracegroup.transformer.dad.DadNodeReference;
import com.tracegroup.transformer.dad.Property;
import com.tracegroup.transformer.dad.PropertyDefinition;
import com.tracegroup.transformer.dad.PropertyDefinitionSet;
import com.tracegroup.transformer.lookup.ILookupKeys;
import com.tracegroup.transformer.lookup.ILookupKeysAndValues;
import com.tracegroup.transformer.tbeans.propertyeditors.PropertyRendering;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
class ConfigFileLookup implements ILookupKeys<Object>, ILookupKeysAndValues<String, String> {

    private final String configFilePath;
    private final Project project;

    private PropertyDefinitionSet propertyDefinitionSet;
    private Properties holidaysCode;

    public ConfigFileLookup(Project project, String configFilePath) {
        this.project = project;
        this.configFilePath = configFilePath;
        this.holidaysCode = loadFromFile(configFilePath);
    }

    @Override
    public boolean containsKey(Object key) {
        log.debug("containsKey {}", key);
        return holidaysCode.containsKey(formatKey((String) key));
    }

    @Override
    public String getValue(String key) {
        log.debug("getValue {}", key);
        return holidaysCode.getProperty(formatKey(key));
    }

    @Override
    public Class getValueClass() {
        return String.class;
    }

    @Override
    public Class getKeyClass() {
        return String.class;
    }

    @Override
    public PropertyDefinitionSet getKeyAndValuePropertyDefinitionSet() {
        return getInputOutputPropertyDefinitionSet();
    }

    @Override
    public void resetCache() {
        log.debug("resetCache");
        holidaysCode = loadFromFile(configFilePath);
    }

    public synchronized PropertyDefinitionSet getInputOutputPropertyDefinitionSet() {
        if(this.propertyDefinitionSet == null) {
            this.buildInputOutProperties();
        }

        return this.propertyDefinitionSet;
    }

    private void buildInputOutProperties() {
        this.propertyDefinitionSet = new PropertyDefinitionSet();
        this.propertyDefinitionSet.addPropertyDefinition( makePropertyDef("Input", "string", false), (ConfigUpdater)null);
        this.propertyDefinitionSet.addPropertyDefinition( makePropertyDef("Output", "string", true), (ConfigUpdater)null);
        this.propertyDefinitionSet.setProject(this.project);
    }

    private PropertyDefinition makePropertyDef(String name, String type, boolean isOutput)
    {
        PropertyDefinition pdef = PropertyDefinition.createDefinition(name, "nodespec");
        if (isOutput)
        {
            Property metaP = Property.createBooleanValue("isDestination", Boolean.valueOf(true));
            pdef.addMetaProperty(metaP, null);
        }
        DadNodeReference dnr = new DadNodeReference();
        dnr.setBotReference(type, null);
        Property metaP = Property.createDadNodeRefValue("Type", dnr);
        pdef.addMetaProperty(metaP, null);

        metaP = PropertyRendering.makeRenderingMetaProp(PropertyRendering.Behaviour.SUPPRESSED);

        pdef.addMetaProperty(metaP, null);

        return pdef;
    }

    private Properties loadFromFile(String fileName) {
        try (InputStream stream = ConfigFileLookupProvider.class.getResourceAsStream(fileName)) {
            Properties config = new Properties();
            config.load(stream);
            return config;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private String formatKey(String key) {
        return (key == null) ? null : key.replace(" ", "_");
    }
}
