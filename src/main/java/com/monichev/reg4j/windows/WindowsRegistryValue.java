package com.monichev.reg4j.windows;

import com.monichev.reg4j.RegistryValue;

public class WindowsRegistryValue implements RegistryValue {
    private final Type type;
    private final String value;

    public WindowsRegistryValue(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getType() {
        return type.toString();
    }

    public enum Type {
        /**
         * No type (the stored value, if any)
         */
        REG_NONE(0),
        /**
         * A string value, normally stored and exposed in UTF-16LE (when using the Unicode version of Win32 API functions), usually terminated by a
         * NUL character
         */
        REG_SZ(1),
        /**
         * An "expandable" string value that can contain environment variables, normally stored and exposed in UTF-16LE, usually terminated by a
         * NUL character
         */
        REG_EXPAND_SZ(2),
        /**
         * Binary data (any arbitrary data)
         */
        REG_BINARY(3),
        /**
         * A DWORD value, a 32-bit unsigned integer (numbers between 0 and 4,294,967,295 [232 – 1]) (little-endian)
         */
        REG_DWORD(4),
        /**
         * A DWORD value, a 32-bit unsigned integer (numbers between 0 and 4,294,967,295 [232 – 1]) (big-endian)
         */
        REG_DWORD_BIG_ENDIAN(5),
        /**
         * A symbolic link (UNICODE) to another registry key, specifying a root key and the path to the target key
         */
        REG_LINK(6),
        /**
         * A multi-string value, which is an ordered list of non-empty strings, normally stored and exposed in UTF-16LE, each one terminated by a
         * NUL character, the list being normally terminated by a second NUL character.
         */
        REG_MULTI_SZ(7),
        /**
         * A resource list (used by the Plug-n-Play hardware enumeration and configuration)
         */
        REG_RESOURCE_LIST(8),
        /**
         * A resource descriptor (used by the Plug-n-Play hardware enumeration and configuration)
         */
        REG_FULL_RESOURCE_DESCRIPTOR(9),
        /**
         * A resource requirements list (used by the Plug-n-Play hardware enumeration and configuration)
         */
        REG_RESOURCE_REQUIREMENTS_LIST(10),
        /**
         * A QWORD value, a 64-bit integer (either big- or little-endian, or unspecified) (introduced in Windows XP)
         */
        REG_QWORD(11);
        final int id;

        Type(int id) {
            this.id = id;
        }
    }
}
