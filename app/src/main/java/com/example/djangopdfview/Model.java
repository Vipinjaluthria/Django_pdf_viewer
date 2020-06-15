package com.example.djangopdfview;

public class Model {
    public String getUsername() {
        return username;
    }

    public String getLink() {
        return link;
    }

    public String getTeamname() {
        return teamname;
    }

    public String getEventname() {
        return eventname;
    }

    String username;
    String link;

    public Model(String username, String link, String teamname, String eventname) {
        this.username = username;
        this.link = link;
        this.teamname = teamname;
        this.eventname = eventname;
    }

    String teamname;
    String eventname;
}
