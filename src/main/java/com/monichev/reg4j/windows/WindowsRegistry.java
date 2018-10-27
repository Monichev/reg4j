package com.monichev.reg4j.windows;

import java.io.IOException;

import com.monichev.reg4j.Registry;
import com.monichev.reg4j.RegistryException;
import com.monichev.reg4j.RegistryValue;

public class WindowsRegistry implements Registry {

    private static String getCommand(String path, String key, RegAction action) {
        return getCommand(path, key, action, null);
    }

    private static String getCommand(String path, String key, RegAction action, RegistryValue value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(action);
        stringBuilder.append(" \"");
        stringBuilder.append(path);
        stringBuilder.append("\" /v ");
        stringBuilder.append(key);
        if (value != null) {
            stringBuilder.append(" /t ");
            stringBuilder.append(value.getType());
            stringBuilder.append(" /d ");
            stringBuilder.append(value.getValue());
        }
        switch (action) {
        case DELETE:
        case ADD:
            stringBuilder.append(" /f");
            break;
        default:
            break;
        }
        return stringBuilder.toString();
    }

    @Override
    public RegistryValue get(String path, String key) {
        RegAction action = RegAction.QUERY;
        try {
            WindowsRegistryAccessor provider = createRegistryAccessor(getCommand(path, key, action));

            if (!provider.getError().isEmpty()) {
                throw new RegistryException(action.toString(), path, key, provider.getError());
            }

            String[] outputEntries = provider.getOutput().split("\r\n");
            String[] valueEntries = outputEntries[3].trim().replaceAll("\\s+", " ").split(" ");
            return new WindowsRegistryValue(WindowsRegistryValue.Type.valueOf(valueEntries[1]), valueEntries[2]);
        } catch (IOException e) {
            throw new RegistryException(action.toString(), path, key, e);
        }
    }

    @Override
    public void add(String path, String key, RegistryValue value) {
        RegAction action = RegAction.ADD;
        try {
            WindowsRegistryAccessor provider = createRegistryAccessor(getCommand(path, key, action, value));
            if (!provider.getError().isEmpty()) {
                throw new RegistryException(action.toString(), path, key, provider.getError());
            }
        } catch (IOException e) {
            throw new RegistryException(action.toString(), path, key, e);
        }
    }

    @Override
    public void delete(String path, String key) {
        RegAction action = RegAction.DELETE;
        try {
            WindowsRegistryAccessor provider = createRegistryAccessor(getCommand(path, key, action));
            if (!provider.getError().isEmpty()) {
                throw new RegistryException(action.toString(), path, key, provider.getError());
            }
        } catch (IOException e) {
            throw new RegistryException(action.toString(), path, key, e);
        }
    }

    protected WindowsRegistryAccessor createRegistryAccessor(String command) throws IOException {
        return new WindowsRegistryAccessor(command);
    }

    private enum RegAction {
        QUERY,
        ADD,
        DELETE,
        COPY,
        SAVE,
        RESTORE,
        LOAD,
        UNLOAD,
        COMPARE,
        EXPORT,
        IMPORT,
        FLAGS;
    }
}
