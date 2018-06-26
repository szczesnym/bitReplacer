package com.bitReplacer;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {


    public static void usage() {
        System.out.print("Usage: systemPath fileExtensionToSearchIn bytePatternToLookFor bytePatternToReplace\n");
        System.out.print("Example c:\\ txt 97 98\n,");
        System.out.print("will search all text files from c:\\ and subfolders and will replace each 'a'(97) to 'b'(98)\n");
    }

    public static void main(String[] args) throws IOException {
        Path directoryToStart;
        byte bytePatternToSearch = 0, bytePatternToReplace = 0;
        String fileExtension;

        if (args.length != 4) {
            System.out.println("Wrong parameters count -> exiting");
            usage();
            System.exit(-1);
        }

        try {
            directoryToStart = Paths.get(args[0]);
        } catch (InvalidPathException e) {
            System.out.println("parameter 1 is not a valid Path -> existing");
            usage();
            System.exit(-1);
        }

        try {
            bytePatternToSearch = new Byte(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("parameter 3 is not a valid byte -> existing");
            usage();
            System.exit(-1);
        }

        try {
            bytePatternToReplace = new Byte(args[3]);
        } catch (NumberFormatException e) {
            System.out.println("parameter 4 is not a valid byte -> existing");
            usage();
            System.exit(-1);
        }
        fileExtension = args[1];
        BitReplacer bitReplacer = new BitReplacer(args[0], fileExtension);
        bitReplacer.replace(bytePatternToSearch, bytePatternToReplace);
    }
}
