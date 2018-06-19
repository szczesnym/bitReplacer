package com.bitReplacer;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import static java.nio.file.FileVisitResult.CONTINUE;

public class BitReplacerFileVisitor extends SimpleFileVisitor<Path> {
    private String filePattern;
    private List<Path> filesMachingFilePattern;

    public BitReplacerFileVisitor(String filePattern) {
        this.filePattern = filePattern;
        this.filesMachingFilePattern = new ArrayList<>();

    }

    public List<Path> getFilesMatchingFilePattern() {
        return filesMachingFilePattern;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        //find(dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        //System.err.println(exc);
        return CONTINUE;
    }


    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        //System.err.println(exc);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        final PathMatcher matcher;
        Path name = file.getFileName();
        if (FilenameUtils.getExtension(name.toString()).equals(filePattern)) {
            this.filesMachingFilePattern.add(file);
        }
        return CONTINUE;
    }
}
