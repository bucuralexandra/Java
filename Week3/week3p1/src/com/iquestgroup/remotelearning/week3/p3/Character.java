package com.iquestgroup.remotelearning.week3.p3;

public abstract class Character {

    private int healthPoints;
    private int attackStrength;
    private int defenseStrength;

    public Character() {
    }

    public Character(int healthPoints, int attack, int defense) {
        this.healthPoints = healthPoints;
        this.attackStrength = attack;
        this.defenseStrength = defense;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackStrength() {
        return attackStrength;
    }

    public void setAttackStrength(int attack) {
        this.attackStrength = attack;
    }

    public void showStatus() {
        System.out.println("Character has: " + this.healthPoints + " health ");
    }

    public boolean isDead() {
        if (healthPoints <= 0) {
            return true;
        }
        return false;
    }
}
