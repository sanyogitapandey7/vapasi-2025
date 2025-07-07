package com.tw.util;

import java.util.regex.Pattern;

public class AppConstants {
    public static final Pattern ExpenseInputPattern = Pattern.compile("(\\w+) spent (\\d+) for (.*?) for ([A-Z](?:, [A-Z])*)");
    public static final String ExpenseFilePath = "/Users/sanyogitapandey/IdeaProjects/Vapasi-2025/splitwise/src/main/java/com/tw/text-input/expenses.txt"; // Ensure this file is in the root of your project or specify full path
    public static final String EmptyInputFile = "/Users/sanyogitapandey/IdeaProjects/Vapasi-2025/splitwise/src/test/java/com/tw/bll/test-input-files/emptyInput.txt";
    public static final String ValidInputFile = "/Users/sanyogitapandey/IdeaProjects/Vapasi-2025/splitwise/src/test/java/com/tw/bll/test-input-files/validInput.txt";
    public static final String SettledInput = "/Users/sanyogitapandey/IdeaProjects/Vapasi-2025/splitwise/src/test/java/com/tw/bll/test-input-files/settledInput.txt";
    public static final double PrecisionValue = 0.01;

    public static final String InvalidInputMessage = "Invalid input ";
    public static final String Settled = "All settled";
    public static final String ErrorReadingFile = "Error reading input file.";

}
