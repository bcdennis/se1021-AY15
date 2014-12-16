package edu.msoe;

import com.t00ter.T00t;
import com.t00ter.T00ter;

import java.util.*;

/**
 * Created by dennis on 12/8/2014.
 */
public class Lab1Functional {

    private static final int OPTION_ERROR = -1;
    private static final int OPTION_EXIT = 0;
    private static final int OPTION_ALL = 1;
    private static final int OPTION_USER = 2;
    private static final String OPTION_ERROR_MSG = "Please enter a valid option. <0,1,2>";
    private static final String USERNAME_ERROR_MSG = "A user with that username does not exist.";
    private static final String USERNAME_PROMPT = "Please enter a username: ";
    private static final String MENU = "Enter an option <0,1,2>:\n"
            + "0 - Exit program.\n"
            + "1 - Display most recent toots and statistics.\n"
            + "2 - Display most recent toots and statistics for a specific user.";
    public static void main(String[] args) {

        T00ter validator;
        ArrayList<T00t> toots;
        Map<String, String> stats;

        String buffer = "";
        String parameter = "";
        int response = OPTION_ERROR;

        Scanner inputReader = new Scanner(System.in);


        do {
            System.out.println(MENU);
            buffer = inputReader.nextLine();
            try {
                response = Integer.parseInt(buffer);
            } catch (NumberFormatException e) {
                response = OPTION_ERROR;
            }

            switch (response) {
                case OPTION_EXIT:
                    break;
                case OPTION_ALL:
                    parameter = "";
                    break;
                case OPTION_USER:
                    System.out.println(USERNAME_PROMPT);
                    parameter = inputReader.nextLine();
                    validator = new T00ter(parameter);
                    if (!validator.isValid()) {
                        System.out.println(USERNAME_ERROR_MSG);
                        response = OPTION_ERROR;
                    }
                    break;
                default:
                    System.out.println(OPTION_ERROR_MSG);
                    break;
            }

            if ((response == OPTION_ALL || response == OPTION_USER)) {
                toots = fetchToots(parameter);
                printRecentToots(toots);
                stats = calculateStats(toots);
                printTootStats(stats);
            }

        } while (response != OPTION_EXIT);


    }
    private static void printRecentToots(ArrayList<T00t> toots) {
        for (T00t toot : toots) {
            System.out.println(toot);
        }
    }
    private static void printTootStats(Map<String, String> stats) {

        Iterator statsIterator = stats.entrySet().iterator();

        while (statsIterator.hasNext()) {
            Map.Entry kvPair = (Map.Entry) statsIterator.next();
            System.out.println(kvPair.getKey() + ": " + kvPair.getValue());
            statsIterator.remove();

        }
    }
    private static Map<String, String> calculateStats(ArrayList<T00t> toots) {
        Map<String, String> statsMap = new HashMap<String, String>();

        statsMap.put("Number of t00ts", "" + toots.size());
        statsMap.put("Number of t00ts containing t00t", "" + calculateMetaToots(toots));
        statsMap.put("Length of longest toot", "" + longestTootLength(toots));
        statsMap.put("Length of shortest toot", "" + shortestTootLength(toots));
        statsMap.put("Average length of toots", "" + calculateAverageTootLength(toots));
        statsMap.put("Minimum timespan between consecutive toots", "" + calculateMinimumTimeSpan(toots));
        statsMap.put("Maximum timespan between consecutive toots", "" + calculateMaximumTimeSpan(toots));
        //statsMap.put("Another stastic", implementMe());

        return statsMap;
    }
    private static long calculateMaximumTimeSpan(ArrayList<T00t> toots) {
        long maxSpan = Long.MIN_VALUE;
        long currentSpan = 0;

        T00t lastToot = toots.get(0);
        for (int i = 1; i < toots.size(); i++) {
            currentSpan = (lastToot.getDate().toInstant().getEpochSecond() - toots.get(i).getDate().toInstant().getEpochSecond());
            if (currentSpan > maxSpan) {
                maxSpan = currentSpan;
            }

            lastToot = toots.get(i);
        }

        return maxSpan;
    }
    private static long calculateMinimumTimeSpan(ArrayList<T00t> toots) {
        long minSpan = Long.MAX_VALUE;
        long currentSpan = 0;

        T00t lastToot = toots.get(0);
        for (int i = 1; i < toots.size(); i++) {
            currentSpan = (lastToot.getDate().toInstant().getEpochSecond() - toots.get(i).getDate().toInstant().getEpochSecond());
            if (currentSpan < minSpan) {
                minSpan = currentSpan;
            }

            lastToot = toots.get(i);
        }

        return minSpan;
    }
    private static int calculateAverageTootLength(ArrayList<T00t> toots) {
        int totalLength = 0;

        for (T00t toot : toots) {
            totalLength += toot.getMessage().length();
        }

        return (int) totalLength / toots.size();

    }

    private static int shortestTootLength(ArrayList<T00t> toots) {
        int minLength = toots.get(0).getMessage().length();
        for (int i = 1; i < toots.size(); i++) {
            if (toots.get(i).getMessage().length() < minLength) {
                minLength = toots.get(i).getMessage().length();
            }
        }
        return minLength;
    }

    private static int longestTootLength(ArrayList<T00t> toots) {
        int maxLength = toots.get(0).getMessage().length();
        for (int i = 1; i < toots.size(); i++) {
            if (toots.get(i).getMessage().length() > maxLength) {
                maxLength = toots.get(i).getMessage().length();
            }
        }
        return maxLength;
    }

    private static int calculateMetaToots(ArrayList<T00t> toots) {
        int tootCount = 0;
        String message = "";

        for (T00t toot : toots) {
            message = toot.getMessage().toLowerCase();
            if (message.contains("toot")
                    || message.contains("t0ot")
                    || message.contains("to0t")
                    || message.contains("t00t")) {

                tootCount++;
            }
        }
        return tootCount;
    }

    private static ArrayList<T00t> fetchToots(String parameter) {
        T00ter api = new T00ter(parameter);
        ArrayList<T00t> toots = new ArrayList<T00t>();

        while (api.hasNext()) {
            toots.add(api.next());
        }

        return toots;
    }

}
