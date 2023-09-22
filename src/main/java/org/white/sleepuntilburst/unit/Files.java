package org.white.sleepuntilburst.unit;

import java.io.File;

public class Files {
    public static File dataFolder;

    public static File dreamConfig;

    public static void load(File dataFolder) {
        Files.dataFolder = dataFolder;
        Files.dreamConfig = new File(dataFolder.getAbsolutePath() + "/dreams.xml");

    }

}
