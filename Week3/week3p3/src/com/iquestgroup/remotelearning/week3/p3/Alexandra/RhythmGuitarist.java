package com.iquestgroup.remotelearning.week3.p3.Alexandra;

public class RhythmGuitarist extends Guitarist{

    private int nrOfSoloInSong ;

    public RhythmGuitarist(String name, int age, Guitar guitar, int nrOfSoloInSong) {
        super(name, age, guitar);
        this.nrOfSoloInSong = nrOfSoloInSong;
    }

    public int getNrOfSoloInSong() {
        return nrOfSoloInSong;
    }

    public void setNrOfSoloInSong(int nrOfSoloInSong) {
        this.nrOfSoloInSong = nrOfSoloInSong;
    }

    @Override
    public void play() {
        while (nrOfSoloInSong > 0){
            System.out.println("Rhythm guitarist is playing");
            System.out.println(this.getGuitar().playInstrument());
            nrOfSoloInSong--;
        }
    }

}
