package com.OskarJohansson.DungeonRun.Model.Map;

import com.OskarJohansson.DungeonRun.Model.Monster.Bosses.IcaBoss;
import com.OskarJohansson.DungeonRun.Model.Monster.Bosses.KjellCOBoss;
import com.OskarJohansson.DungeonRun.Model.Monster.Bosses.SatsBoss;
import com.OskarJohansson.DungeonRun.Model.Monster.Bosses.TechWizOfDoom;
import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;

import java.util.ArrayList;
import java.util.List;

public class TeacherLounge extends Level {

    public TeacherLounge() {

        List<EnemyParentModel> setMonsterList = new ArrayList<>();
        setMonsterList.add(new IcaBoss());
        setMonsterList.add(new SatsBoss());
        setMonsterList.add(new KjellCOBoss());
        setFinalBoss(new TechWizOfDoom());
        this.setMonsterList(setMonsterList);
        this.setLevelNumber("4");
        this.setLevelName("Dungeon of The Teachers Lounge");
    }
}
