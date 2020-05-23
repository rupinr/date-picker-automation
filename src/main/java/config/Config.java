package config;

import resolver.OS;
import resolver.Version;

public class Config {

    private OS os;
    private Version version;

    public Config(OS os, Version version) {
        this.os = os;
        this.version = version;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }
}
