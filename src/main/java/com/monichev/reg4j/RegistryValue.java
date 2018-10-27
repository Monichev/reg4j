package com.monichev.reg4j;

/**
 * Registry value
 */
public interface RegistryValue {
    /**
     * Get type of the value
     *
     * @return the type
     */
    String getType();

    /**
     * Get the value
     *
     * @return the value
     */
    String getValue();
}
