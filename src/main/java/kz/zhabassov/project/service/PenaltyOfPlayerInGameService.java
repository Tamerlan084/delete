package kz.zhabassov.project.service;

import kz.zhabassov.project.dao.PenaltyOfPlayerInGameDao;
import kz.zhabassov.project.entity.PenaltyOfPlayerInGame;

public class PenaltyOfPlayerInGameService {
    private PenaltyOfPlayerInGameDao penaltyOfPlayerInGameDao;

    public PenaltyOfPlayerInGame insertPenaltyOfPlayerInGame(PenaltyOfPlayerInGame penaltyOfPlayerInGame) {
        return penaltyOfPlayerInGameDao.insert(penaltyOfPlayerInGame);
    }
}
