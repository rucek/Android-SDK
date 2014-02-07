package com.estimote.examples.demos;

import com.estimote.sdk.Beacon;

public class UniqueBeaconId {
    private String uuid;
    private int minor;
    private int major;

    public UniqueBeaconId(String uuid, int minor, int major) {
        this.uuid = uuid;
        this.minor = minor;
        this.major = major;
    }

    public UniqueBeaconId(Beacon beacon) {
        this(beacon.getProximityUUID(), beacon.getMinor(), beacon.getMajor());
    }

    @Override
    public String toString() {
        return "UniqueBeaconId{" +
                "uuid='" + uuid + '\'' +
                ", minor='" + minor + '\'' +
                ", major='" + major + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UniqueBeaconId that = (UniqueBeaconId) o;

        if (major != that.major) return false;
        if (minor != that.minor) return false;
        if (!uuid.equals(that.uuid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + minor;
        result = 31 * result + major;
        return result;
    }
}
