package com.tours.utiles;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log {

    private static Logger Log = Logger.getLogger(Log.class);


    public static void info (String message) {

        BasicConfigurator.configure();
        Log.info(message);
    }

    public static void error (String message) {
        BasicConfigurator.configure();
        Log.error(message);
    }

    public static void debug (String message) {
        BasicConfigurator.configure();
        Log.debug(message);
    }
}
