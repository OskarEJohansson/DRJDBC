package com.OskarJohansson.DungeonRun.Model.Map;

import com.OskarJohansson.DungeonRun.Model.Monster.*;
import com.OskarJohansson.DungeonRun.Model.Monster.Bosses.KjellCOBoss;
import com.OskarJohansson.DungeonRun.Model.Monster.Monster.Nerd;

import java.util.ArrayList;
import java.util.List;

public class KjellCO extends Level {

    public KjellCO() {
        List<EnemyParentModel> setMonsterList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            setMonsterList.add(new Nerd());
        }
        this.setMonsterList(setMonsterList);
        this.setFinalBoss(new KjellCOBoss());
        this.setLevelNumber("3");
        this.setLevelName("Dungeon of Kjell & Co");
    }

}
