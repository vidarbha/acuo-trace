package com.acuo.collateral.transform.trace.lookup;

import com.tracegroup.transformer.configfiles.ConfigObject;
import com.tracegroup.transformer.configfiles.Project;
import com.tracegroup.transformer.dad.TBeanConfiguration;
import com.tracegroup.transformer.lookup.ILookupKeys;
import com.tracegroup.transformer.tbeans.LookupProviderTBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

@Slf4j
public class ConfigFileLookupProvider extends LookupProviderTBean {

    private String configFilePath;
    private Project project;

    public ConfigFileLookupProvider() {
    }

    @Override
    public ILookupKeys<Object> createLookup() {
        return new ConfigFileLookup(project, configFilePath);
    }

    public int instancePattern() {
        return 2;
    }

    public void validateConfiguration(ConfigObject owner, TBeanConfiguration configuration) {
        String filePath = (String) configuration.getPropertyValue("ConfigFile");
        log.debug("validateConfiguration {}", filePath);
        if (StringUtils.isEmpty(filePath)) {
            configuration.setPropertyUnusable("ConfigFile", "Config File must be specified");
            return;
        }
        if (!filePath.startsWith("/")) { filePath = "/" + filePath; }
        if (ConfigFileLookupProvider.class.getResource(filePath) == null) {
            configuration.setPropertyUnusable("ConfigFile", "Config File must exist in the classpath");
            return;
        }
        this.configFilePath = filePath;
    }

    public void setProperties(ConfigObject owner, Map properties, TBeanConfiguration configuration) {
        log.debug("setProperties {}, {}, {}", owner, properties, configuration);
        this.configFilePath = (String) configuration.getPropertyValue("ConfigFile");
        this.project = owner.getProject();
    }
}