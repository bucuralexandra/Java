package com.iquestgroup.remotelearning.week3.p3.Alexandra;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        //Playlist p = new Playlist("HappySongs");
        Song s1 = new Song("Happy", 2.33, 2015);
        Song s2 = new Song("Life is good", 4.23,2000);
        //p.addSongToPlaylist(s1);
        //p.addSongToPlaylist(s2);

        iPod ipod = new iPod("iPod1");
        CDPlayer cdPlayer = new CDPlayer("Playerr");
        cdPlayer.addSongToDevice(s1);
        cdPlayer.addSongToDevice(s2);
        cdPlayer.playSongs();
        ipod.playSongs();

        Guitar guitar = new Guitar("rv200", 200,"Blue picks");
        Drumset drumset = new Drumset("drums21", 209, "wooden");

        Musician alex = new Musician("Alex", 34);
        Guitarist maria = new Guitarist("Maria", 30, guitar);
        Drummer mihai = new Drummer("Mihai", 29, drumset);
        RhythmGuitarist lola = new RhythmGuitarist("Lola", 21, guitar, 2);
        Band b = new Band("Alexa&Maria", "Horatiu" );
        Band b2 = new Band("SingleBand", "Ale");
        b.addMember(alex);
        b.addMember(maria);
        b.addMember(mihai);
        b.addMember(lola);
        System.out.println(maria.getBand().getName());
        System.out.println();
        b.showMembers();
        b.playSong();
        System.out.println("_________");
        mihai.changeBand(b2);
        System.out.println(mihai.getBand().getName());

        ipod.createPlaylist("Favourites");
        ipod.addSongToDevice(s1);
        Iterator<Playlist> iterator = ipod.getPlaylists().iterator();
        if(iterator.hasNext()) {
            ipod.addSongToPlaylist(iterator.next(),s1);
        }
        ipod.playPlaylist("Favourites");
    }
}
