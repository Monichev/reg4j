package com.monichev.reg4j.windows;

import java.io.IOException;

import com.monichev.reg4j.Registry;
import com.monichev.reg4j.RegistryException;
import com.monichev.reg4j.RegistryValue;

public class WindowsRegistry implements Registry {

    @Override
    public RegistryValue get(String path, String key) {
        String action = "query";
        try {
            WindowsRegistryAccessor provider = createRegistryAccessor(getCommand(path, key, action));

            if (!provider.getError().isEmpty()) {
                throw new RegistryException(action, path, key, provider.getError());
            }

            String[] outputEntries = provider.getOutput().split("\r\n");
            String[] valueEntries = outputEntries[3].trim().replaceAll("\\s+", " ").split(" ");
            return new WindowsRegistryValue(WindowsRegistryValue.Type.valueOf(valueEntries[1]), valueEntries[2]);
        } catch (IOException e) {
            throw new RegistryException(action, path, key, e);
        }
    }

    @Override
    public void add(String path, String key, RegistryValue value) {
        String action = "add";
        try {
            WindowsRegistryAccessor provider = createRegistryAccessor(getCommand(path, key, action, value));
            if (!provider.getError().isEmpty()) {
                throw new RegistryException(action, path, key, provider.getError());
            }
        } catch (IOException e) {
            throw new RegistryException(action, path, key, e);
        }
    }

    private String getCommand(String path, String key, String action) {
        return getCommand(path, key, action, null);
    }

    private String getCommand(String path, String key, String action, RegistryValue value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(action);
        stringBuilder.append(" \"");
        stringBuilder.append(path);
        stringBuilder.append("/v ");
        stringBuilder.append(key);
        if (value != null) {
            stringBuilder.append(" /t ");
            stringBuilder.append(value.getType());
            stringBuilder.append(" /d ");
            stringBuilder.append(value.getValue());
        }
        stringBuilder.append(" /f");
        return stringBuilder.toString();
    }

    @Override
    public void delete(String path, String key) {
        String action = "delete";
        try {
            WindowsRegistryAccessor provider = createRegistryAccessor(getCommand(path, key, action));
            if (!provider.getError().isEmpty()) {
                throw new RegistryException(action, path, key, provider.getError());
            }
        } catch (IOException e) {
            throw new RegistryException(action, path, key, e);
        }
    }

    protected WindowsRegistryAccessor createRegistryAccessor(String command) throws IOException {
        return new WindowsRegistryAccessor(command);
    }
}
