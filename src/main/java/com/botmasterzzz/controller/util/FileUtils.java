package com.botmasterzzz.controller.util;

import java.io.File;

public class FileUtils {

    public static void fileDelete(String path) {
        try {
            File f = new File(path);
            if (f.delete()) {
                System.out.println(f.getName() + " deleted");
            } else {
                System.out.println("failed => " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
