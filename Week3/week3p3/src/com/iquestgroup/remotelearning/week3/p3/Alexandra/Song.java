package com.iquestgroup.remotelearning.week3.p3.Alexandra;

import java.util.Objects;

public class Song {
    private String name;
    private double duration;
    private int releaseYear;
    private Band band;

    public Song(String name, double duration, int releaseYear) {
        this.name = name;
        this.duration = duration;
        this.releaseYear = releaseYear;
        this.band = null;
    }

    public Song(String name, double duration, int releaseYear, Band band) {
       this(name,duration,releaseYear);
        this.band = band;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Double.compare(song.duration, duration) == 0 && releaseYear == song.releaseYear && name.equals(song.name) && Objects.equals(band, song.band);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration, releaseYear, band);
    }

    @Override
    public String toString() {
        if(this.band != null) {
            return "Song{" +
                    "name='" + name + '\'' +
                    ", duration=" + duration +
                    ", releaseYear=" + releaseYear +
                    ", album='" + band + '\'' +
                    '}';
        }
        else {
            return "Song{" +
                    "name='" + name + '\'' +
                    ", duration=" + duration +
                    ", releaseYear=" + releaseYear +
                    '}';
        }
    }
}
