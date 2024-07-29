package com.liu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;


public class Main {
    public static ArrayList<PlayerBattingStats> players = new ArrayList<>(1);

    /**
     * Takes in a comma sperated values file and parses the data.  It takes the data and
     * creates a com.liu.PlayerBattingStats object and adds it to the players list.  The CSV file
     * must be in the format of:
     * Name,Team,Gamesplayed,At bats, runs, hits, doubles, triples, home runs, rbi's
     * @param file containg the data
     */
    public static void parseCSVData(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] splitter = line.split(",");
            String name = splitter[0];
            String team = splitter[1];
            int gamesPlayed = Integer.parseInt(splitter[2]);
            int atBats = Integer.parseInt(splitter[3]);
            int runs = Integer.parseInt(splitter[4]);
            int hits = Integer.parseInt(splitter[5]);
            int doubles = Integer.parseInt(splitter[6]);
            int triples = Integer.parseInt(splitter[7]);
            int homeRuns = Integer.parseInt(splitter[8]);
            int rbis = Integer.parseInt(splitter[9]);
            players.add(new PlayerBattingStats(name, team, gamesPlayed, atBats, runs, hits, doubles, triples, homeRuns, rbis));
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            parseCSVData(new File("PlayerData.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[] sortTypes = {PlayerSorter.GAMESPLAYED, PlayerSorter.ATBATS, PlayerSorter.RUNS, PlayerSorter.PERCENTRUNS, PlayerSorter.PERCENTONBASE};
        String[] sortTypeTitles = {"Sorted by games played: ", "Sorted by at bats: ", "Sorted by runs: ", "Sorted by run percentage: ", "Sorted by on base percentage: "};
        PrintWriter out = new PrintWriter("BaseballPlayerStats.txt");
        out.println("Baseball Player Stats");
        out.println("\n-----------------------------------\n");
        for (int i = 0; i < 5; i++) {
            out.println(sortTypeTitles[i] + "\n");
            for (PlayerBattingStats player : PlayerSorter.sort(players, i)) {
                out.println(player.toString(sortTypes[i]));
            }
            out.println("\n-----------------------------------\n");
        }
        out.close();
    }
}