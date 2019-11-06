package kz.zhabassov.project.entity;

import java.io.Serializable;

public class Team extends Entity implements Serializable {
    private String team;
    private String city;
    private String coach;
    private int captain;
    private String captainName;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        properties.put(PropertyTeam.TEAM, team);
        this.team = team;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        properties.put(PropertyTeam.CITY, city);
        this.city = city;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        properties.put(PropertyTeam.COACH, coach);
        this.coach = coach;
    }

    public int getCaptain() {
        return captain;
    }

    public void setCaptain(int captain) {
        properties.put(PropertyTeam.CAPTAIN, captain);
        this.captain = captain;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public enum PropertyTeam implements Property {
        TEAM(String.class, "team"),
        CITY(String.class, "city"),
        COACH(String.class, "coach"),
        CAPTAIN(String.class, "captain");

        private Class propertyType;
        private String column;

        PropertyTeam(Class propertyType, String column) {
            this.propertyType = propertyType;
            this.column = column;
        }

        @Override
        public Object getPropertyType() {
            return propertyType;
        }

        @Override
        public String getColumn() {
            return column;
        }
    }
}
