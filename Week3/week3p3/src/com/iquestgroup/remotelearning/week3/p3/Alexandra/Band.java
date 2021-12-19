package com.iquestgroup.remotelearning.week3.p3.Alexandra;

import java.util.Calendar;
import java.util.HashSet;

public class Band {
    private static final String ON_TOUR = "Band on tour";
    private String name;
    private HashSet<Musician> members;
    private HashSet<Song> songs;
    private String namagerName;
    private String tourName;

    public Band(String name, String namagerName) {
        this.name = name;
        this.namagerName = namagerName;
        this.members = new HashSet<>();
        this.songs = new HashSet<>();
        this.tourName = null;
    }

    public Band(String name, String namagerName, String tourName) {
        this(name,namagerName);
        this.name = name;
        this.namagerName = namagerName;
        this.tourName = tourName;
    }

    private boolean onTour(){
        if(this.tourName == null)
           return false;
        else
           return true;
    }

    public void goOnTour(String tourName){
        if (onTour() == true){
            System.out.println(ON_TOUR);
        }
        else {
            this.tourName = tourName;
        }
    }

    public String getName() {
        return name;
    }

    public HashSet<Musician> getMembers() {
        return members;
    }

    public HashSet<Song> getSongs() {
        return songs;
    }

    public String getNamagerName() {
        return namagerName;
    }

    public String getTourName() {
        return tourName;
    }

    public void showMembers(){
        if(this.members != null){
            for( Musician p: members){
                System.out.println(p.getName());
            }
        }
    }
    public void haveConcert(String cityName){
        System.out.println("Band " + this.name + " has a concert in " + cityName);
    }

    public void createSong(String name, Double duration){
        songs.add(new Song(name, duration, Calendar.getInstance().get(Calendar.YEAR)));
    }

    public void addMember(Musician p){
        members.add(p);
        p.joinBand(this);
    }

    public boolean isInBand(Musician p){
        return members.contains(p);
    }
    public void removeMember( Musician m){
        this.members.remove(m);
    }

    public void playSong(){
        for (Musician musician: members){
            musician.play();
        }
    }
}
