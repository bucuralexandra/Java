package com.iquestgroup.remotelearning.week3.p3.Alexandra;

public class LeadGuitarist extends Guitarist implements Playing{

    private double introInSoloDuration;

    public LeadGuitarist(String name, int age, Guitar guitar, double introInSoloDuration) {
        super(name, age, guitar);
        this.introInSoloDuration = introInSoloDuration;
    }

    public double getIntroInSoloDuration() {
        return introInSoloDuration;
    }

    public void setIntroInSoloDuration(double introInSoloDuration) {
        this.introInSoloDuration = introInSoloDuration;
    }

    @Override
    public void play() {
        System.out.println("Lead_guitarist" + this.getName() + "has an intro of " + introInSoloDuration);
        System.out.println(this.getGuitar().playInstrument());
    }
}
