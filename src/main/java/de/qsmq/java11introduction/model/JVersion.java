package de.qsmq.java11introduction.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;

@XmlRootElement(name = "java")
@XmlType(propOrder = {"version", "lastLtsVersion"})
public class JVersion {

    private int version;
    private LocalDate releaseDate;
    private int lastLtsVersion;

    public JVersion(int version, LocalDate releaseDate, int lastLtsVersion) {
        this.version = version;
        this.releaseDate = releaseDate;
        this.lastLtsVersion = lastLtsVersion;
    }

    @XmlElement
    public void setVersion(int version) {
        this.version = version;
    }

    @XmlTransient
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @XmlElement
    public void setLastLtsVersion(int lastLtsVersion) {
        this.lastLtsVersion = lastLtsVersion;
    }

    public JVersion() {
    }

    public int getVersion() {
        return version;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getLastLtsVersion() {
        return lastLtsVersion;
    }

    public JVersion nextVersion() {
        int version = this.version + 1;
        LocalDate releaseDate = this.releaseDate.plusMonths(6);
        int lastLtsVersion;
        if (version - this.lastLtsVersion < 6) {
            lastLtsVersion = this.lastLtsVersion;
        } else {
            lastLtsVersion = version;
        }
        return new JVersion(version, releaseDate, lastLtsVersion);
    }

    @Override
    public String toString() {
        return "JavaVersion{" +
                "version=" + version +
                ", releaseDate=" + releaseDate +
                ", lastLtsVersion=" + lastLtsVersion +
                '}';
    }
}
