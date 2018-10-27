package com.monichev.reg4j.windows;

import java.io.IOException;
import java.io.InputStream;

public class WindowsRegistryAccessor {
    private final String error;
    private final String output;

    public WindowsRegistryAccessor(String command) throws IOException {
        Process process = Runtime.getRuntime().exec("cmd /c \"chcp 65001 && reg " + command + "\"");
        error = convertStreamToString(process.getErrorStream());
        output = convertStreamToString(process.getInputStream());
    }

    private static String convertStreamToString(InputStream is) {
        try (java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A")) {
            return s.hasNext() ? s.next() : "";
        }
    }

    public String getError() {
        return error;
    }

    public String getOutput() {
        return output;
    }
}
