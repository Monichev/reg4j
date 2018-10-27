package com.monichev.reg4j;

/**
 * All possible problems while accessing the registry
 */
public class RegistryException extends RuntimeException {
    public RegistryException(String action, String path, String key, String details) {
        super(getMessageString(action, path, key) + " details: " + details);
    }

    public RegistryException(String action, String path, String key, Throwable cause) {
        super(getMessageString(action, path, key), cause);
    }

    private static String getMessageString(String action, String path, String key) {
        return "Can't " + action + " key: " + key + " for path: " + path;
    }
}
