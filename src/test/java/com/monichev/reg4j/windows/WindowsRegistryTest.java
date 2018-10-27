package com.monichev.reg4j.windows;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.monichev.reg4j.RegistryException;
import com.monichev.reg4j.RegistryValue;

import org.junit.jupiter.api.Test;

class WindowsRegistryTest {

    private static WindowsRegistryAccessor createMockProvider(String path, String key, String type, String value) {
        WindowsRegistryAccessor mockProvider = mock(WindowsRegistryAccessor.class);
        when(mockProvider.getError()).thenReturn("");
        when(mockProvider.getOutput()).thenReturn("Active code page: 65001\r\n"
                + "\r\n"
                + path + "\r\n"
                + "    " + key + "    " + type + "    " + value + "\r\n"
                + "\r\n");
        return mockProvider;
    }

    @Test
    void get() throws IOException, RegistryException {
        String path = "HKEY_LOCAL_MACHINE\\SYSTEM\\Select";
        String key = "Default";
        String type = "REG_DWORD";
        String value = "0x1";
        WindowsRegistryAccessor mockProvider = createMockProvider(path, key, type, value);

        WindowsRegistry registry = mock(WindowsRegistry.class);
        when(registry.get(anyString(), anyString())).thenCallRealMethod();
        doReturn(mockProvider).when(registry).createRegistryAccessor(anyString());
        RegistryValue clientIC = registry.get(path, key);
        assertThat(clientIC.getValue(), is(value));
    }

    @Test
    void add() throws IOException, RegistryException {
        String path = "HKEY_LOCAL_MACHINE\\SYSTEM\\Select";
        String key = "New";
        String type = "REG_DWORD";
        String value = "0x00000001";
        WindowsRegistryAccessor mockProvider = createMockProvider(path, key, type, value);

        WindowsRegistry registry = mock(WindowsRegistry.class);
        when(registry.get(anyString(), anyString())).thenCallRealMethod();
        doReturn(mockProvider).when(registry).createRegistryAccessor(anyString());
        registry.add(path, key, new WindowsRegistryValue(WindowsRegistryValue.Type.valueOf(type), value));
    }

    @Test
    void remove() throws IOException, RegistryException {
        String path = "HKEY_LOCAL_MACHINE\\SYSTEM\\Select";
        String key = "New";
        String type = "REG_DWORD";
        String value = "0x00000001";
        WindowsRegistryAccessor mockProvider = createMockProvider(path, key, type, value);

        WindowsRegistry registry = mock(WindowsRegistry.class);
        when(registry.get(anyString(), anyString())).thenCallRealMethod();
        doReturn(mockProvider).when(registry).createRegistryAccessor(anyString());
        registry.delete(path, key);
    }
}
