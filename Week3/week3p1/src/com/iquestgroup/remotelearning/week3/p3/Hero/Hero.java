package com.iquestgroup.remotelearning.week3.p3.Hero;

import com.iquestgroup.remotelearning.week3.p3.Attacking;
import com.iquestgroup.remotelearning.week3.p3.Character;
import com.iquestgroup.remotelearning.week3.p3.Monsters.FireMonster;
import com.iquestgroup.remotelearning.week3.p3.Monsters.Monster;

public class Hero extends Character implements Attacking {

    private String name;
    private int experience;

    public Hero(int healthPoints, int attack, int defense, String name) {
        super(healthPoints, attack, defense);
        this.name = name;
        this.experience = 0;
    }

    @Override
    public void attack(Character c) {
        this.gainExperience();

        if (c.getClass() == FireMonster.class) {
            if (((FireMonster) c).isMagic()) {
                c.setHealthPoints(c.getHealthPoints() - this.getAttackStrength() / 2);
            } else {
                c.setHealthPoints(c.getHealthPoints() - this.getAttackStrength());
            }
        } else {
            c.setHealthPoints(c.getHealthPoints() - this.getAttackStrength());
        }
        if (c.isDead()) {
            System.out.println("Monster is dead");
        }
    }

    public void gainExperience() {
        this.experience += 10;
    }

    @Override
    public void showStatus() {
        if (!isDead()) {
            System.out.println("Hero " + this.name + " has " + this.getHealthPoints() + "HP and experience: "
                    + this.experience);
        }
    }
}
