# Registry for java (reg4j)
A module to access OS registry.

Currently implemented for windows only.

## Windows
### Add
```
try {
    registry.add("HKEY_CURRENT_USER\\Environment", "Test", new WindowsRegistryValue(REG_DWORD, "0x1"));
} catch (RegistryException e) {
    throw new IllegalStateException("Can't add registry entry", e);
}
```
### Get
```
try {
    RegistryValue value = registry.get("HKEY_CURRENT_USER\\Environment", "Test");
} catch (RegistryException e) {
    throw new IllegalStateException("Can't add registry entry", e);
}
```
### Delete
```
try {
    registry.delete("HKEY_CURRENT_USER\\Environment", "Test");
} catch (RegistryException e) {
    throw new IllegalStateException("Can't delete registry entry", e);
}
```