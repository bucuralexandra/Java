package com.iquestgroup.remotelearning.week3.p3.Game;

import com.iquestgroup.remotelearning.week3.p3.Hero.Hero;
import com.iquestgroup.remotelearning.week3.p3.Monsters.FireMonster;
import com.iquestgroup.remotelearning.week3.p3.Monsters.Monster;
import com.iquestgroup.remotelearning.week3.p3.Monsters.RockMonster;

public class Game {

    public static void main(String[] args) {

        Hero hero = new Hero(100, 10, 10, "Chuck");
        FireMonster fire = new FireMonster(50, 2, 30);
        RockMonster rockM = new RockMonster(40, 20, 20);
        rockM.showStatus();

        hero.attack(fire);
        hero.showStatus();
        fire.showStatus();
        fire.doMagic();
        hero.attack(fire);
        hero.showStatus();
        fire.showStatus();

        rockM.attack(hero);
        hero.showStatus();

        hero.attack(rockM);
        rockM.showStatus();

    }
}
