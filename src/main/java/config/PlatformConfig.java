package config;

public class PlatformConfig {

    private Os os;
    private Version version;

    public PlatformConfig(Os os, Version version) {
        this.os = os;
        this.version = version;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Os getOs() {
        return os;
    }

    public void setOs(Os os) {
        this.os = os;
    }
}
