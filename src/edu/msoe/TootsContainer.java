package edu.msoe;

import com.t00ter.T00t;

import java.util.*;

/**
 * Created by dennis on 12/9/2014.
 */

public class TootsContainer {

    private static final String NUMBER_OF_TOOTS = "Number of t00ts";
    private static final String NUMBER_OF_METATOOTS = "Number of t00ts containing t00t";
    private static final String LONGEST_TOOT = "Length of longest toot";
    private static final String SHORTEST_TOOT = "Length of shortest toot";
    private static final String AVG_LENGTH = "Average length of toots";
    private static final String MIN_TIMESPAN = "Minimum timespan between consecutive toots";
    private static final String MAX_TIMESPAN = "Maximum timespan between consecutive toots";

    ArrayList<T00t> toots;

    public TootsContainer() {
        toots = new ArrayList<T00t>();
    }


    public void add(T00t toot) {
        toots.add(toot);
    }

    public Map<String, String> getStatistics() {

        Map<String, String> statsMap = new HashMap<String, String>();

        statsMap.put(NUMBER_OF_TOOTS, "" + this.toots.size());
        statsMap.put(NUMBER_OF_METATOOTS, "" + calculateMetaToots());
        statsMap.put(LONGEST_TOOT, "" + longestTootLength());
        statsMap.put(SHORTEST_TOOT, "" + shortestTootLength());
        statsMap.put(AVG_LENGTH, "" + calculateAverageTootLength());
        statsMap.put(MIN_TIMESPAN, "" + calculateMinimumTimeSpan());
        statsMap.put(MAX_TIMESPAN, "" + calculateMaximumTimeSpan());

        return statsMap;

    }

    private  long calculateMaximumTimeSpan() {
        long maxSpan = Long.MIN_VALUE;
        long currentSpan = 0;

        T00t lastToot = this.toots.get(0);
        for (int i = 1; i < this.toots.size(); i++) {
            currentSpan = (lastToot.getDate().toInstant().getEpochSecond() - this.toots.get(i).getDate().toInstant().getEpochSecond());
            if (currentSpan > maxSpan) {
                maxSpan = currentSpan;
            }

            lastToot = toots.get(i);
        }

        return maxSpan;
    }

    private  long calculateMinimumTimeSpan() {
        long minSpan = Long.MAX_VALUE;
        long currentSpan = 0;

        T00t lastToot = this.toots.get(0);
        for (int i = 1; i < toots.size(); i++) {
            currentSpan = (lastToot.getDate().toInstant().getEpochSecond() - this.toots.get(i).getDate().toInstant().getEpochSecond());
            if (currentSpan < minSpan) {
                minSpan = currentSpan;
            }

            lastToot = this.toots.get(i);
        }

        return minSpan;
    }

    private  int calculateAverageTootLength() {
        int totalLength = 0;

        for (T00t toot : this.toots) {
            totalLength += toot.getMessage().length();
        }

        return (int) totalLength / this.toots.size();

    }

    private  int shortestTootLength() {
        int minLength = toots.get(0).getMessage().length();
        for (int i = 1; i < toots.size(); i++) {
            if (toots.get(i).getMessage().length() < minLength) {
                minLength = toots.get(i).getMessage().length();
            }
        }
        return minLength;
    }

    private int longestTootLength() {
        int maxLength = this.toots.get(0).getMessage().length();
        for (int i = 1; i < this.toots.size(); i++) {
            if (this.toots.get(i).getMessage().length() > maxLength) {
                maxLength = this.toots.get(i).getMessage().length();
            }
        }
        return maxLength;
    }

    private int calculateMetaToots() {
        int tootCount = 0;
        String message = "";

        for (T00t toot : this.toots) {
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

    public Iterator getIterator() {

        return this.toots.iterator();
    }
}
