package com.iquestgroup.remotelearning.week3.p3.Alexandra;

import java.util.HashSet;

public class MusicPlayer {
    static final String NON_EXISTENT_SONG = "MusicPlayer does not contain such a song";
    private String name;
     private HashSet<Song> songs;


     MusicPlayer(String name){
         this.name = name;
         this.songs = new HashSet<>();
     }

    public static String getNonExistentSong() {
        return NON_EXISTENT_SONG;
    }

    public String getName() {
        return name;
    }


    public HashSet<Song> getSongs() {
        return songs;
    }


    public  Song findSong(Song song) {
        if (songs != null) {
            for (Song i : songs) {
                if (i.equals(song)) return i;
            }
        }
        return null;
    }

    public boolean addSongToDevice(Song song) {
        if (songs != null) {
            boolean ok = songs.add(song);
            return ok;
        }
        return false;
    }

    public boolean removeSongFromDevice(Song song) {
        Song aux = findSong(song);
        if (aux != null) {
            songs.remove(aux);
            return true;
        }
        return false;
    }

    public void playSongs() {
         if(songs.size() == 0) {
             System.out.println("No songs"); return;
         }
        if( songs != null) {
            for (Song i : songs) {
                System.out.println(i.toString());
            }
        }

    }

}
