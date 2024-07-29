package com.liu;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that takes in an ArrayList and sorts the players by various criteria
 * using a mergesort.
 */

public class PlayerSorterBackUp {
    //The following fields are the different sort types:
    public static final int GAMESPLAYED = 0;
    public static final int ATBATS = 1;
    public static final int RUNS = 2;
    public static final int PERCENTRUNS = 3; // Calculated by: runs/atBats
    public static final int PERCENTONBASE = 4; // Calculated by (hits + doubles + triples) / atBats

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

        /*System.out.println("Original list size: " + list.size());
        System.out.println("Midpoint: " + midpoint);

        // Declare and initialize left and right lists.
        ArrayList<com.liu.PlayerBattingStats> left = new ArrayList<>(list.subList(0, midpoint));
        ArrayList<com.liu.PlayerBattingStats> right = new ArrayList<>(list.subList(midpoint, list.size()));

        System.arraycopy(list, 0, left, 0, midpoint);
        System.arraycopy(list,midpoint,right,0,midpoint);

        System.out.println("Left list size: " + left.size());
        System.out.println("Right list size: " + right.size());

        // Recursive call for left and right lists.
        left = sort(left, sortType);
        right = sort(right, sortType);*/

        // Create sublists using subList
        List<PlayerBattingStats> leftSubList = list.subList(0, midpoint);
        List<PlayerBattingStats> rightSubList = list.subList(midpoint, list.size());

        // Convert sublists to ArrayList
        ArrayList<PlayerBattingStats> left = new ArrayList<>(leftSubList);
        ArrayList<PlayerBattingStats> right = new ArrayList<>(rightSubList);

        System.out.println("Original list size: " + list.size());
        System.out.println("Left list size: " + left.size());
        System.out.println("Right list size: " + right.size());

        // Recursive call for left and right lists.
        left = sort(left, sortType);
        right = sort(right, sortType);

        switch (sortType) {
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

    /*private static ArrayList<com.liu.PlayerBattingStats> merge(ArrayList<com.liu.PlayerBattingStats> left, ArrayList<com.liu.PlayerBattingStats> right, int sortType) {
        if(sortType == 1) {
            return sortByGamesPlayed(left, right);
        }
        if(sortType == 2) {
            return sortByAtBats(left, right);
        }
        if(sortType == 3) {
            return sortByRuns(left, right);
        }
        if(sortType == 4) {
            return sortByPercentRuns(left, right);
        }
        if(sortType == 5) {
            return sortByPercentOnBase(left, right);
        }
        return null;
    }*/

    private static ArrayList<PlayerBattingStats> sortByGamesPlayed(ArrayList<PlayerBattingStats> left, ArrayList<PlayerBattingStats> right) {

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
                if (left.get(leftPointer).getGames() < right.get(rightPointer).getGames()) {
                    result.set(resultPointer++, left.get(leftPointer++));
                } else {
                    result.set(resultPointer++, right.get(rightPointer++));
                }

            } else if (leftPointer < left.size()) {
                // If only items in the left array...
                result.set(resultPointer++, left.get(leftPointer++));
            } else {
                // If only items in the right array...
                result.set(resultPointer++, right.get(rightPointer++));
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
                if (left.get(leftPointer).getAtBats() < right.get(rightPointer).getAtBats()) {
                    result.set(resultPointer++, left.get(leftPointer++));
                } else {
                    result.set(resultPointer++, right.get(rightPointer++));
                }

            } else if (leftPointer < left.size()) {
                // If only items in the left array...
                result.set(resultPointer++, left.get(leftPointer++));
            } else {
                // If only items in the right array...
                result.set(resultPointer++, right.get(rightPointer++));
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
                if (left.get(leftPointer).getRuns() < right.get(rightPointer).getRuns()) {
                    result.set(resultPointer++, left.get(leftPointer++));
                } else {
                    result.set(resultPointer++, right.get(rightPointer++));
                }

            } else if (leftPointer < left.size()) {
                // If only items in the left array...
                result.set(resultPointer++, left.get(leftPointer++));
            } else {
                // If only items in the right array...
                result.set(resultPointer++, right.get(rightPointer++));
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
                if (left.get(leftPointer).getRuns() / left.get(leftPointer).getAtBats() < right.get(rightPointer).getRuns() / right.get(rightPointer).getAtBats()) {
                    result.set(resultPointer++, left.get(leftPointer++));
                } else {
                    result.set(resultPointer++, right.get(rightPointer++));
                }

            } else if (leftPointer < left.size()) {
                // If only items in the left array...
                result.set(resultPointer++, left.get(leftPointer++));
            } else {
                // If only items in the right array...
                result.set(resultPointer++, right.get(rightPointer++));
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
                if ((left.get(leftPointer).getHits() + left.get(leftPointer).getDoubles() + left.get(leftPointer).getDoubles()) / left.get(leftPointer).getAtBats()
                        < (right.get(rightPointer).getHits() + right.get(rightPointer).getDoubles() + right.get(rightPointer).getDoubles()) / right.get(rightPointer).getAtBats()) {
                    result.set(resultPointer++, left.get(leftPointer++));
                } else {
                    result.set(resultPointer++, right.get(rightPointer++));
                }

            } else if (leftPointer < left.size()) {
                // If only items in the left array...
                result.set(resultPointer++, left.get(leftPointer++));
            } else {
                // If only items in the right array...
                result.set(resultPointer++, right.get(rightPointer++));
            }
        }

        return result;
    }
}
