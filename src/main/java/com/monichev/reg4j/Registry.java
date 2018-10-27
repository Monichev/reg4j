package com.monichev.reg4j;

/**
 * Registry interface
 */
public interface Registry {

    /**
     * Get a value for given <b>path</b> and <b>key</b>
     *
     * @param path the path
     * @param key  the key
     * @return the value
     * @throws RegistryException no such value or other problems
     */
    RegistryValue get(String path, String key);

    /**
     * Add a value to given <b>path</b> and <b>key</b>
     *
     * @param path  the path
     * @param key   the key
     * @param value the value
     * @throws RegistryException no access or other problems
     */
    void add(String path, String key, RegistryValue value);

    /**
     * Delete a value for given <b>path</b> and <b>key</b>
     *
     * @param path  the path
     * @param key   the key
     * @throws RegistryException no access or other problems
     */
    void delete(String path, String key);
}
