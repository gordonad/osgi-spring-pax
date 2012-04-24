package org.example.bean.internal;

import org.example.bean.ExampleBean;

/**
 * Internal implementation of our example Spring Bean
 */
public class ExampleBeanImpl
        implements ExampleBean {
    public boolean isABean() {
        return true;
    }
}
