package kz.zhabassov.project.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Game extends Entity {
    private int gameId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private int hostScore;
    private int guestScore;
    private String hostTeam;
    private String guestTeam;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        properties.put(PropertyGame.GAME_ID, gameId);
        this.gameId = gameId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        properties.put(PropertyGame.DATE, date);
        this.date = date;
    }

    public int getHostScore() {
        return hostScore;
    }

    public void setHostScore(int hostScore) {
        properties.put(PropertyGame.HOST_SCORE, hostScore);
        this.hostScore = hostScore;
    }

    public int getGuestScore() {
        return guestScore;
    }

    public void setGuestScore(int guestScore) {
        properties.put(PropertyGame.GUEST_SCORE, guestScore);
        this.guestScore = guestScore;
    }

    public String getHostTeam() {
        return hostTeam;
    }

    public void setHostTeam(String hostTeam) {
        properties.put(PropertyGame.HOST_TEAM, hostTeam);
        this.hostTeam = hostTeam;
    }

    public String getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(String guestTeam) {
        properties.put(PropertyGame.GUEST_TEAM, guestTeam);
        this.guestTeam = guestTeam;
    }

    public enum PropertyGame implements Property {
        GAME_ID(Integer.class, "entity_id"),
        DATE(Date.class, "date"),
        HOST_SCORE(Integer.class, "host_score"),
        GUEST_SCORE(Integer.class, "guest_score"),
        HOST_TEAM(String.class, "host_team"),
        GUEST_TEAM(String.class, "guest_team");
        private Class propertyType;
        private String column;

        PropertyGame(Class propertyType, String column) {
            this.propertyType = propertyType;
            this.column = column;
        }

        public Class getPropertyType() {
            return propertyType;
        }

        public String getColumn() {
            return column;
        }

    }
}
