package org.modules.helpers.config;

public enum ConfigKey {

    TOKEN("token"),
    BASEURL("base.url");
    private final String key;

    ConfigKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
