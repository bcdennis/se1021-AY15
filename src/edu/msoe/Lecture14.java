package edu.msoe;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Lecture14 {

    private static final Logger LOGGER = Logger.getLogger(Lecture14.class.getName());
    private static final String LOGFILE = "runtime.log";

    public static void main(String[] args){
        Lecture14 lecture = new Lecture14();

        try {
            lecture.setUpLogger();
        } catch (IOException e) {
            throw new RuntimeException("The logfile could not be created.");
        }

        Scanner standardIn = new Scanner(System.in);
        QuoteParser parser = lecture.getParser();
        String userInput   = "";

        System.out.println("Enter a filename ('q' to quit):");
        userInput = standardIn.nextLine();
        LOGGER.info("User entered '" + userInput + "' for the filename.");

        while (!userInput.equalsIgnoreCase("q")) {
            try {
                ArrayList<Quote> quotes = parser.parse(userInput);

                for (Quote quote: quotes) {
                    LOGGER.info("Quote: " + quote.getTitle());
                    System.out.println(quote);
                }
                System.out.println("Enter a filename ('q' to quit):");
                userInput = standardIn.nextLine();
                LOGGER.info("User entered '" + userInput + "' for the filename.");

            } catch (InvalidPathException e) {
                LOGGER.warning("Invalid path.");
                System.out.println("The path to the quote file was invalid.");
            } catch (NoSuchFileException e) {
                LOGGER.warning("File does not exist.");
                System.out.println("The quote file does not exist.");
            } catch (IllegalArgumentException e) {
                LOGGER.warning("Quote file is improperly formatted.");
                System.out.println("The quote file contained an improperly formatted line.");
            } finally {
                LOGGER.info("Program done.");
            }
        }
    }

    /**
     * Initializes and configures the class logger.
     *
     * @throws IOException
     */
    public void setUpLogger() throws IOException{
        FileHandler logfileHandler;
        SimpleFormatter logfileFormatter;

        logfileHandler = new FileHandler(LOGFILE);
        logfileFormatter = new SimpleFormatter();
        logfileHandler.setFormatter(logfileFormatter);

        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(logfileHandler);
    }


    /**
     * Factory method to return an instance of the QuoteParser.
     *
     * @return QuoteParser the parser
     */
    public QuoteParser getParser() {
        return new QuoteParser();
    }

































    /**
     * Simple data structure representing a quote.
     */
    private class Quote {
        private String title;
        private String quote;

        private Quote() {}

        /**
         * Quote constructor to require no quotes without a title and a quote body.
         *
         * @param title The title of the quote.
         * @param quote The body of the quote.
         */
        public Quote(String title, String quote) {
            this.title = title;
            this.quote = quote;
        }

        /**
         * Pretty prints the quote.
         * @return a pretty print of the quote.
         */
        public String toString() {
            return this.title + ":\n" + this.quote + "\n";
        }

        /**
         * Access for the quote's title.
         * @return the title of the quote.
         */
        public String getTitle() {
            return this.title;
        }

    }























    private class QuoteParser {
        private static final String DELIMITER = ":";

        /**
         * Parses the quotes file.
         *
         * @param filename  the filename containing the quotes to parse.
         * @return an ArrayList<Quote> containing the quotes in the file.
         */
        public ArrayList<Quote> parse(String filename) throws InvalidPathException, NoSuchFileException {
            ArrayList<Quote> quotes = new ArrayList<Quote>();
            ArrayList<String> lines = parseFile(filename);

            for (String line: lines) {
                quotes.add(parseQuote(line));
            }

            return quotes;
        }

        private  ArrayList<String> parseFile(String filename) throws InvalidPathException, NoSuchFileException {
            ArrayList<String> quotes = new ArrayList<String>();
            String currentLine = "";
            Scanner fileIn;
            try {
                fileIn = new Scanner(Paths.get(filename));

                while (fileIn.hasNext()) {
                    currentLine = fileIn.nextLine();
                    if (!currentLine.isEmpty()) {
                        quotes.add(currentLine);
                    }
                }
            } catch (InvalidPathException | NoSuchFileException e) {
                LOGGER.severe(e.getClass() + "\n" + e.getMessage());
                throw e;
            } catch (IOException e) {
                LOGGER.severe(e.getClass() + "\n" + e.getMessage());
                throw new RuntimeException("I/O error reading parse file.");
            }
            return quotes;
        }

        private Quote parseQuote(String line) throws IllegalArgumentException {
            String parts[];
            if (line.contains(DELIMITER)) {
                parts = line.split(DELIMITER);
                if (parts.length != 2) {
                    LOGGER.severe("Error parsing line: '" + line + "'.  Does not contain two fields.");
                    //throw new java.text.ParseException("Improperly formatted line in parse file.", 0);
                    throw new IllegalArgumentException("Improperly formatted line in parse file.");
                }
            } else {
                throw new IllegalArgumentException("Line does not contain delimiter.");
                //LOGGER.severe("Error parsing line: '" + line + "'.  No delimiter found.");
            }
            return new Quote(parts[0], parts[1]);
        }
    }
}
