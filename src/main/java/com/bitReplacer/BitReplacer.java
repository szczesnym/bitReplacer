package com.bitReplacer;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;


public class BitReplacer {
    private final List<Path> filesToEdit;
    private final String directoryToStart, fileExtension;

    public BitReplacer(String directoryToStart, String fileExtension) throws IOException {
        this.directoryToStart = directoryToStart;
        this.fileExtension = fileExtension;

        BitReplacerFileVisitor bitReplacerFileVisitor = new BitReplacerFileVisitor(fileExtension);
        Files.walkFileTree(Paths.get(directoryToStart), bitReplacerFileVisitor);
        this.filesToEdit = bitReplacerFileVisitor.getFilesMatchingFilePattern();
    }

    public void replace(byte patternToFind, byte patternToReplaceWith) throws IOException {

        for (Path file : filesToEdit) {
            byte[] fileContent = Files.readAllBytes(file);
            System.out.format("For file:%s ", file);
            byte[] resultReplace = replaceContent(fileContent, patternToFind, patternToReplaceWith);
            try {
                Files.write(file, resultReplace, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (Exception e) {
                System.out.format("System file:%s , write error:%s\n", file, e);
            }
        }
    }

    public byte[] replaceContent(byte[] target, byte patternToFind, byte patternToReplaceWith) {
        byte[] fileContentBytes = new byte[target.length];
        int replaceCount = 0;

        for (int i = 0; i < target.length; i++) {
            if (target[i] == patternToFind) {
                fileContentBytes[i] = patternToReplaceWith;
                replaceCount++;
            } else {
                fileContentBytes[i] = target[i];
            }
        }
        System.out.format(" pattern:%d replaced:%d time(s)\n", (int) patternToFind, replaceCount);
        return fileContentBytes;
    }
}
