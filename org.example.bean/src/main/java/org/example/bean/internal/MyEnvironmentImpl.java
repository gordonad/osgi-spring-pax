package org.example.bean.internal;

/**
 * User: gordondickens
 * Date: 4/24/12
 * Time: 2:23 PM
 */

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.example.bean.MyEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * User: gordondickens
 * Date: 2/23/12
 * Time: 8:36 AM
 */
public class MyEnvironmentImpl implements MyEnvironment {
    private static final Logger logger = LoggerFactory.getLogger(MyEnvironmentImpl.class);
    @Value("#{ systemProperties['user.language'] }")
    private String varOne;

    @Value("#{ systemProperties }")
    private Properties systemProperties;

    @Value("#{ systemEnvironment }")
    private Properties systemEnvironment;

    @Value("#{ environment }")
    private StandardEnvironment environment;

    @Override
    public String toString() {
        return "\n\n********************** MyEnvironment: ["
                + "\n\tsystemProperties=" + formatMe(systemProperties.toString())
                + ", \n\n\tsystemEnvironment=" + formatMe(systemEnvironment.toString())
                + ", \n\n\tenvironment=" + formatMe(environment.toString()) + "]";
    }

    private static String formatMe(final String in) {
        String out = in;
        out = in.replace("{", "{\n\t\t");
        out = out.replace(", ", "\n\t\t");

        return out;
    }


    @Override
    @PostConstruct
    public void afterInstantiation() {
        logger.debug(this.toString());
    }

    @Override
    public StandardEnvironment getEnvironment() {
        return environment;
    }

    @Override
    public Properties getSystemEnvironment() {
        return systemEnvironment;
    }

    @Override
    public Map<String, String> getSortedSystemEnvironment() {
        Properties p = systemEnvironment;
        Object[] keys = p.keySet().toArray();
        Arrays.sort(keys);
        Map<String, String> treeMap = new TreeMap<String, String>();
        treeMap.putAll((Map) p);
        return treeMap;
    }

    @Override
    public Properties getSystemProperties() {
/*
servletConfigInitParams
servletContextInitParams
jndiProperties
 */
        return systemProperties;
    }

    @Override
    public Map<String, String> getSortedSystemProperties() {
        Properties p = systemProperties;
        Object[] keys = p.keySet().toArray();
        Arrays.sort(keys);
        Map<String, String> treeMap = new TreeMap<String, String>();
        treeMap.putAll((Map) p);
        return treeMap;
    }


    @Override
    public List<String> getServletConfigInitParams() {
        return getPropertyList("servletConfigInitParams");
    }

    @Override
    public List<String> getServletContextInitParams() {
        return getPropertyList("servletContextInitParams");
    }

    @Override
    public List<String> getJndiProperties() {
        return getPropertyList("jndiProperties");
    }


    private List<String> getPropertyList(String propSrcName) {
        if (environment.getPropertySources().contains(propSrcName)) {
            PropertySource ps = environment.getPropertySources().get(propSrcName);
            String results = ReflectionToStringBuilder.toString(ps, ToStringStyle.SHORT_PREFIX_STYLE);
            List<String> resultList = Arrays.asList(StringUtils.split(results, ','));
            Collections.sort(resultList);
            return resultList;
        } else
            return null;
    }
}