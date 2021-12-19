package com.iquestgroup.remotelearning.week3.p3.Monsters;

import com.iquestgroup.remotelearning.week3.p3.Character;
import com.iquestgroup.remotelearning.week3.p3.Hero.Hero;

public class FireMonster extends Monster {

    private boolean magic = false;

    public FireMonster(int healthPoints, int attack, int defense) {
        super(healthPoints, attack, defense);
    }

    public FireMonster() {
        super();
    }

    public void doMagic() {
        this.magic = true;
        System.out.println("FireMonster does magic");
    }

    @Override
    public void showStatus() {
        if (!isDead()) {
            System.out.println("FireMonster has: " + this.getHealthPoints() + "HP");
        }
    }

    public boolean isMagic() {
        return magic;
    }
}
