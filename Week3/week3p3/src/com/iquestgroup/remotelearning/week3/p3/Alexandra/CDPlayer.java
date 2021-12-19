package com.iquestgroup.remotelearning.week3.p3.Alexandra;

public class CDPlayer extends MusicPlayer{

   private final static String REMOVE = "Cannot remove song from a CDPlayer";

    public CDPlayer(String name) {
        super(name);
    }

    @Override
    public boolean removeSongFromDevice(Song song) {
        System.out.println(REMOVE);
        return false;
    }

    @Override
    public Song findSong(Song song) {
        return super.findSong(song);
    }


    public Song goToSong(Song song){
        Boolean ok = getSongs().contains(song);
      if(ok == true)
          return song;
      else return null;
    }
}
