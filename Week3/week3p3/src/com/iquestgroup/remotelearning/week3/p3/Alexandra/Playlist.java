package com.iquestgroup.remotelearning.week3.p3.Alexandra;

import java.util.LinkedHashSet;

public  class Playlist {
    private String name;
    private LinkedHashSet<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new LinkedHashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedHashSet<Song> getSongs() {
        return songs;
    }

    public void setSongs(LinkedHashSet<Song> songs) {
        this.songs = songs;
    }
    public boolean findSong( String name){
        for (Song i: songs) {
            if (i.getName().equals(name))
                return true;
        }
        return false;
    }
    private Song findSong( Song song){
        if(songs != null){
        for(Song i:songs) {
            if (i.equals(song)) return i;
        }
        }
        return null;
    }

    public boolean addSongToPlaylist(Song song){
        if(songs != null) {
            if (findSong(song) == null) {
                songs.add(song);
                return true;
            }
        }
        else {
            songs.add(song);
            return true;
        }
        return false;
    }

    public boolean removeSongFromPlaylist(Song song){
        Song aux = findSong(song);
        if(aux!= null){
            songs.remove(aux);
            return true;
        }
        return false;
    }
    public void showSongs(){
        for( Song i:songs){
            System.out.println(i.toString());
        }
    }
}
