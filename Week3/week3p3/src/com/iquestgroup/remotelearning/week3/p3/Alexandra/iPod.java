package com.iquestgroup.remotelearning.week3.p3.Alexandra;

import java.util.LinkedHashSet;

public class iPod extends MusicPlayer{

    private static final String NON_EXISTENT = "Playlist does not exist in this device";
    private LinkedHashSet<Playlist> playlists;

    public iPod(String name) {
        super(name);
        this.playlists = new LinkedHashSet<>();
    }

    public void playPlaylist(String name) {
        Playlist p = findPlaylist(name);
        if (p != null) {
            p.showSongs();
        } else {
            System.out.println(NON_EXISTENT);
        }
    }

    public LinkedHashSet<Playlist> getPlaylists() {
        return playlists;
    }

    public void createPlaylist(String name) {
        Playlist p = new Playlist(name);
        if (findPlaylist(p) == null) {
            playlists.add(p);
        }
    }

    public Playlist findPlaylist(String name) {
        for (Playlist i : playlists) {
            if (i.getName().equals(name))
                return i;
        }
        return null;
    }

    private Playlist findPlaylist(Playlist playlist) {
        if (playlists != null) {
            for (Playlist i : playlists) {
                if (i.equals(playlist)) return i;
            }
        }
        return null;
    }

    public boolean addSongToPlaylist(Playlist playlist, Song song) {
        Playlist p = findPlaylist(playlist);
        if (p != null) {
            if( findSong(song) != null) {
                p.addSongToPlaylist(song);
                return true;
            }
            else {
                System.out.println(this.getNonExistentSong());
            }
        }
        System.out.println(NON_EXISTENT);
        return false;
    }
}
