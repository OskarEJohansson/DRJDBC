package com.OskarJohansson.DungeonRun.Model.Map;

import com.OskarJohansson.DungeonRun.Model.Monster.Monster.Minion;
import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;
import com.OskarJohansson.DungeonRun.Model.Monster.Bosses.IcaBoss;

import java.util.ArrayList;
import java.util.List;

public class ICA extends Level {

    public ICA() {

        List<EnemyParentModel> setMonsterList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            setMonsterList.add(new Minion());
        }
        this.setMonsterList(setMonsterList);
        this.setFinalBoss(new IcaBoss());
        this.setLevelNumber("1");
        this.setLevelName("Dungeon of Ica");
    }

}
