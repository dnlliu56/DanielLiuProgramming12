package com.liu;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that takes in an ArrayList and sorts the players by various criteria
 * using a mergesort.
 */

public class PlayerSorter {
    //The following fields are the different sort types:
    public static final int GAMESPLAYED = 0;
    public static final int ATBATS = 1;
    public static final int RUNS = 2;
    public static final int PERCENTRUNS = 3; // Calculated by: runs/atBats
    public static final int PERCENTONBASE = 4; // Calculated by (hits + doubles + triples) / atBats
    public int sortType;
    public int listLength;

    /**
     * This method is an interface that allows the sorting of a player given based on
     * certain criteria.  There are 5 options to sort a player which are by Games played,
     * at bats, runs, percent runs, percent on base.
     * The sort is in descending order (Largest to smallest)
     * This method calls one of 5 private methods that will do the sort based on the chosen criteria.
     *
     * @param list     List of players containing the statistics
     * @param sortType How the user wants the players sorted.  The value should be chosed from one of
     *                 the 5 static fields from the class.
     * @return An ArrayList that is sorted according to the users specification.
     */
    public static ArrayList<PlayerBattingStats> sort(ArrayList<PlayerBattingStats> list, int sortType) {
        // Recursive control 'if' statement.
        if (list.size() <= 1) {
            return list;
        }

        int midpoint = list.size() / 2;

        // Create sublists using subList
        List<PlayerBattingStats> leftSubList = list.subList(0, midpoint);
        List<PlayerBattingStats> rightSubList = list.subList(midpoint, list.size());

        // Convert sublists to ArrayList
        ArrayList<PlayerBattingStats> left = new ArrayList<>(leftSubList);
        ArrayList<PlayerBattingStats> right = new ArrayList<>(rightSubList);

        // Recursive call for left and right lists.
        left = sort(left, sortType);
        right = sort(right, sortType);

        switch (sortType) {    //I would generally use one method, but the criteria calls for five
            case GAMESPLAYED:
                return sortByGamesPlayed(left, right);
            case ATBATS:
                return sortByAtBats(left, right);
            case RUNS:
                return sortByRuns(left, right);
            case PERCENTRUNS:
                return sortByPercentRuns(left, right);
            case PERCENTONBASE:
                return sortByPercentOnBase(left, right);
            default:
                throw new IllegalArgumentException("Invalid sort type");
        }
    }

    //sorting methods in accordance with int sortType

    private static ArrayList<PlayerBattingStats> sortByGamesPlayed(ArrayList<PlayerBattingStats> left, ArrayList<PlayerBattingStats> right) {

        // Merged result array.
        ArrayList<PlayerBattingStats> result = new ArrayList<PlayerBattingStats>(left.size() + right.size());

        // Declare and initialize pointers for all arrays.
        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;

        // While there are items in either array...
        while (leftPointer < left.size() || rightPointer < right.size()) {

            // If there are items in BOTH arrays...
            if (leftPointer < left.size() && rightPointer < right.size()) {

                // If left item is less than right item...
                if (left.get(leftPointer).getGames() > right.get(rightPointer).getGames()) {
                    result.add(left.get(leftPointer++));
                } else {
                    result.add(right.get(rightPointer++));
                }

            } else if (leftPointer < left.size()) {
                // If only items in the left array...
                result.add(left.get(leftPointer++));
            } else {
                // If only items in the right array...
                result.add(right.get(rightPointer++));
            }
        }

        return result;
    }

    private static ArrayList<PlayerBattingStats> sortByAtBats(ArrayList<PlayerBattingStats> left, ArrayList<PlayerBattingStats> right) {

        // Merged result array.
        ArrayList<PlayerBattingStats> result = new ArrayList<>(left.size() + right.size());

        // Declare and initialize pointers for all arrays.
        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;

        // While there are items in either array...
        while (leftPointer < left.size() || rightPointer < right.size()) {

            // If there are items in BOTH arrays...
            if (leftPointer < left.size() && rightPointer < right.size()) {

                // If left item is less than right item...
                if (left.get(leftPointer).getAtBats() > right.get(rightPointer).getAtBats()) {
                    result.add(left.get(leftPointer++));
                } else {
                    result.add(right.get(rightPointer++));
                }

            } else if (leftPointer < left.size()) {
                // If only items in the left array...
                result.add(left.get(leftPointer++));
            } else {
                // If only items in the right array...
                result.add(right.get(rightPointer++));
            }
        }

        return result;
    }

    private static ArrayList<PlayerBattingStats> sortByRuns(ArrayList<PlayerBattingStats> left, ArrayList<PlayerBattingStats> right) {

        // Merged result array.
        ArrayList<PlayerBattingStats> result = new ArrayList<>(left.size() + right.size());

        // Declare and initialize pointers for all arrays.
        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;

        // While there are items in either array...
        while (leftPointer < left.size() || rightPointer < right.size()) {

            // If there are items in BOTH arrays...
            if (leftPointer < left.size() && rightPointer < right.size()) {

                // If left item is less than right item...
                if (left.get(leftPointer).getRuns() > right.get(rightPointer).getRuns()) {
                    result.add(left.get(leftPointer++));
                } else {
                    result.add(right.get(rightPointer++));
                }

            } else if (leftPointer < left.size()) {
                // If only items in the left array...
                result.add(left.get(leftPointer++));
            } else {
                // If only items in the right array...
                result.add(right.get(rightPointer++));
            }
        }

        return result;
    }

    private static ArrayList<PlayerBattingStats> sortByPercentRuns(ArrayList<PlayerBattingStats> left, ArrayList<PlayerBattingStats> right) {

        // Merged result array.
        ArrayList<PlayerBattingStats> result = new ArrayList<>(left.size() + right.size());

        // Declare and initialize pointers for all arrays.
        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;

        // While there are items in either array...
        while (leftPointer < left.size() || rightPointer < right.size()) {

            // If there are items in BOTH arrays...
            if (leftPointer < left.size() && rightPointer < right.size()) {

                // If left item is less than right item...
                double leftPercentRuns = (double) left.get(leftPointer).getRuns() / left.get(leftPointer).getAtBats();
                double rightPercentRuns = (double) right.get(rightPointer).getRuns() / right.get(rightPointer).getAtBats();
                if (leftPercentRuns > rightPercentRuns) {
                    result.add(left.get(leftPointer++));
                } else {
                    result.add(right.get(rightPointer++));
                }

            } else if (leftPointer < left.size()) {
                // If only items in the left array...
                result.add(left.get(leftPointer++));
            } else {
                // If only items in the right array...
                result.add(right.get(rightPointer++));
            }
        }

        return result;
    }

    private static ArrayList<PlayerBattingStats> sortByPercentOnBase(ArrayList<PlayerBattingStats> left, ArrayList<PlayerBattingStats> right) {

        // Merged result array.
        ArrayList<PlayerBattingStats> result = new ArrayList<>(left.size() + right.size());

        // Declare and initialize pointers for all arrays.
        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;

        // While there are items in either array...
        while (leftPointer < left.size() || rightPointer < right.size()) {

            // If there are items in BOTH arrays...
            if (leftPointer < left.size() && rightPointer < right.size()) {

                // If left item is less than right item...
                double leftPercentOnBase = (double) (left.get(leftPointer).getHits() + left.get(leftPointer).getDoubles() + left.get(leftPointer).getTriples()) / left.get(leftPointer).getAtBats();
                double rightPercentOnBase = (double) (right.get(rightPointer).getHits() + right.get(rightPointer).getDoubles() + right.get(rightPointer).getTriples()) / right.get(rightPointer).getAtBats();
                if (leftPercentOnBase > rightPercentOnBase) {
                    result.add(left.get(leftPointer++));
                } else {
                    result.add(right.get(rightPointer++));
                }

            } else if (leftPointer < left.size()) {
                // If only items in the left array...
                result.add(left.get(leftPointer++));
            } else {
                // If only items in the right array...
                result.add(right.get(rightPointer++));
            }
        }

        return result;
    }

}