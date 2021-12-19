package com.iquestgroup.remotelearning.week3.p3.Monsters;

import com.iquestgroup.remotelearning.week3.p3.Attacking;
import com.iquestgroup.remotelearning.week3.p3.Character;
import com.iquestgroup.remotelearning.week3.p3.Hero.Hero;

public abstract class Monster extends Character implements Attacking {

    public Monster() {
        super();
    }

    public Monster(int healthPoints, int attack, int defense) {
        super(healthPoints, attack, defense);
    }

    @Override
    public void attack(Character c) {
        c.setHealthPoints(c.getHealthPoints() - this.getAttackStrength());
        if (c.isDead() == true) {
            System.out.println("Hero is dead");
        }
    }

    @Override
    public void showStatus() {
        if (!isDead()) {
            System.out.println("Monster has: " + this.getHealthPoints() + "HP");
        }
    }
}
