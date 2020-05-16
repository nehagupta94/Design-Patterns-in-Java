package primeDetectorSockets.primeDetector.util;

public class Logger{


    public static enum DebugLevel { DEBUG0,DEBUG1,DEBUG2,DEBUG3,DEBUG4
    };

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
        switch (levelIn) {
            case 4: debugLevel = DebugLevel.DEBUG4; break;
            case 3: debugLevel = DebugLevel.DEBUG3; break;
            case 2: debugLevel = DebugLevel.DEBUG2; break;
            case 1: debugLevel = DebugLevel.DEBUG1; break;
            case 0: debugLevel = DebugLevel.DEBUG0; break;
        }
    }

    public static void setDebugValue (DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    public static void writeMessage (String message ,DebugLevel levelIn ) {
        if (levelIn == debugLevel)
            System.out.print(message);
    }

    public String toString() {
        return "Debug Level is " + debugLevel;
    }
}