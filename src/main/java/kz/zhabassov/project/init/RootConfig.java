package kz.zhabassov.project.init;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import kz.zhabassov.project.dao.*;
import kz.zhabassov.project.service.GameService;
import kz.zhabassov.project.service.PlayerService;
import kz.zhabassov.project.service.TeamService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"kz.zhabassov.project"})
public class RootConfig {

    @Bean
    public DataSource dataSource() {
        try {
            HikariConfig config = new HikariConfig();
            config.setDataSourceClassName("org.postgresql.Driver");
            config.setUsername("admin");
            config.setPassword("password");
            config.addDataSourceProperty("databaseName", "project");
            config.addDataSourceProperty("serverName", "localhost:5432");

            HikariDataSource ds = new HikariDataSource(config);

            return ds;
        } catch (Exception e) {
            return null;
        }
    }

    @Bean
    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public GameDao getGameDao() {
        GameDao gameDao = new GameDao();
        gameDao.setNamedParameterJdbcTemplate(getJdbcTemplate());
        return gameDao;
    }

    @Bean
    public PenaltyDao getPenaltyDao() {
        PenaltyDao penaltyDao = new PenaltyDao();
        penaltyDao.setNamedParameterJdbcTemplate(getJdbcTemplate());
        return penaltyDao;
    }

    @Bean
    public PenaltyOfPlayerInGameDao getPenaltyOfPlayerInGameDao() {
        PenaltyOfPlayerInGameDao penaltyOfPlayerInGameDao = new PenaltyOfPlayerInGameDao();
        penaltyOfPlayerInGameDao.setNamedParameterJdbcTemplate(getJdbcTemplate());
        return penaltyOfPlayerInGameDao;
    }

    @Bean
    public PlayerDao getPlayerDao() {
        PlayerDao playerDao = new PlayerDao();
        playerDao.setNamedParameterJdbcTemplate(getJdbcTemplate());
        return playerDao;
    }

    @Bean
    public TeamDao getTeamDao() {
        TeamDao teamDao = new TeamDao();
        teamDao.setNamedParameterJdbcTemplate(getJdbcTemplate());
        return teamDao;
    }

    @Bean
    public GameService getGameService() {
        GameService gameService = new GameService();
        gameService.setGameDao(getGameDao());
        return gameService;
    }

    @Bean
    public PlayerService getPlayerService() {
        PlayerService playerService = new PlayerService();
        playerService.setPlayerDao(getPlayerDao());
        return playerService;
    }

    @Bean
    public TeamService getTeamService() {
        TeamService teamService = new TeamService();
        teamService.setPlayerDao(getPlayerDao());
        teamService.setTeamDao(getTeamDao());
        return teamService;
    }


}
