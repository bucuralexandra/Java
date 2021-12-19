package com.iquestgroup.remotelearning.week3.p3.Monsters;


import java.util.Random;

public class RockMonster extends Monster {

    private int randomMagic;

    public RockMonster(int healthPoints, int attack, int defense) {
        super(healthPoints, attack, defense);
        Random rnd = new Random();
        this.randomMagic = rnd.nextInt(100);
        this.setAttackStrength(randomMagic);
    }

    @Override
    public void showStatus() {
        if (!isDead()) {
            System.out.println("RockMonster has: " + this.getHealthPoints() + "HP " + this.getAttackStrength() + " strength");
        }
    }
}
