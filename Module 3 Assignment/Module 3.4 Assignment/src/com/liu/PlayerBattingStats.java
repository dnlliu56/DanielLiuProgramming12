package com.liu;

/**
 * This class represents a players batting statistics.  The following are definitions of the stats
 * games -> Games played
 * atBAts -> Number of times a player has batted
 * runs -> Number of times a player reached home base
 * hits -> Number of times the player the ball and reached first base
 * doubles -> Number of times the player the ball and reached second base
 * triples -> Number of times the player the ball and reached third base
 * homeRuns -> Number of times the player got a home run
 * rbi -> Number of times the player got on base and allowed another runner to score
 */

public class PlayerBattingStats {
    private String name;
    private String team;
    private int games;
    private int atBats;
    private int runs;
    private int hits;
    private int doubles;
    private int triples;
    private int homeRuns;
    private int rbi;

    public PlayerBattingStats(String name, String team, int games, int atBats, int runs, int hits, int doubles, int triples, int homeRuns, int rbi) {
        this.name = name;
        this.team = team;
        this.games = games;
        this.atBats = atBats;
        this.runs = runs;
        this.hits = hits;
        this.doubles = doubles;
        this.triples = triples;
        this.homeRuns = homeRuns;
        this.rbi = rbi;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public int getGames() {
        return games;
    }

    public int getAtBats() {
        return atBats;
    }

    public int getRuns() {
        return runs;
    }

    public int getHits() {
        return hits;
    }

    public int getDoubles() {
        return doubles;
    }

    public int getTriples() {
        return triples;
    }

    public int getHomeRuns() {
        return homeRuns;
    }

    public int getRbi() {
        return rbi;
    }

    public String toString(int sortType) {
        String stat;
        switch (sortType) {
            case PlayerSorter.GAMESPLAYED:
                stat = games + " games";
                break;
            case PlayerSorter.ATBATS:
                stat = atBats + " at bats";
                break;
            case PlayerSorter.RUNS:
                stat = runs + " runs";
                break;
            case PlayerSorter.PERCENTRUNS:
                double percentRuns = (double) runs / atBats * 100;
                double roundedPercentRuns = (double) Math.round (percentRuns * 10) / 10;
                stat = roundedPercentRuns + "%";
                break;
            case PlayerSorter.PERCENTONBASE:
                double percentOnBase = (double) (hits + doubles + triples) / atBats * 100;
                double roundedPercentOnBase = (double) Math.round (percentOnBase * 10) / 10;
                stat = roundedPercentOnBase + "%";
                break;
            default:
                stat = "Unknown Stat";
                break;
        }
        return name + " - " + stat;
    }

}

