package org.example.bean;

import org.springframework.core.env.StandardEnvironment;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * User: gordondickens
 * Date: 4/24/12
 * Time: 2:27 PM
 */
public interface MyEnvironment {

    @Override
    public String toString();

    public void afterInstantiation();

    public StandardEnvironment getEnvironment();

    public Properties getSystemEnvironment();

    public Map<String, String> getSortedSystemEnvironment();

    public Properties getSystemProperties();

    public Map<String, String> getSortedSystemProperties();

    public List<String> getServletConfigInitParams();

    public List<String> getServletContextInitParams();

    public List<String> getJndiProperties();
}
