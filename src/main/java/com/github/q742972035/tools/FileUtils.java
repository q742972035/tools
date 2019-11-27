package com.github.q742972035.tools;

import com.github.q742972035.tools.exception.DirectoryNotFoundException;
import com.github.q742972035.tools.exception.StringNullException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FileUtils {

    /**
     * @param basePath   目录路径
     * @param fileSuffix 文件后缀
     * @return 该目录路径下所有文件后缀的目录+文件名
     */
    public static List<String> findAllFilePaths(String basePath, String fileSuffix) throws FileNotFoundException {
        List<String> path = new ArrayList<>();
        Optional.ofNullable(basePath).orElseThrow(() -> new StringNullException("basePath cat not be null"));
        Optional.ofNullable(fileSuffix).orElseThrow(() -> new StringNullException("fileSuffix cat not be null"));

        List<String> allPaths = findAllFilePaths(basePath);
        return allPaths.stream().filter(s -> s.toLowerCase().endsWith(fileSuffix.toLowerCase())).collect(Collectors.toList());
    }


    /**
     * @param basePath   目录路径
     * @return 该目录路径下所有文件后缀的目录+文件名
     */
    public static List<String> findAllFilePaths(String basePath) throws FileNotFoundException {
        List<String> path = new ArrayList<>();
        Optional.ofNullable(basePath).orElseThrow(() -> new StringNullException("basePath cat not be null"));

        File baseFile = new File(basePath);
        if (!baseFile.exists()) {
            throw new FileNotFoundException();
        }
        if (!baseFile.isDirectory()) {
            throw new DirectoryNotFoundException(basePath + "is not a directory");
        }

        File[] files = baseFile.listFiles();
        LinkedList<File> link = Arrays.stream(files).collect(Collectors.toCollection(LinkedList::new));

        File currentFile;
        while ((currentFile = link.pollLast())!=null){
            if (currentFile.isDirectory()){
                files = currentFile.listFiles();
                for (File file : files) {
                    link.addFirst(file);
                }
                continue;
            }
            if (currentFile.isFile()){
                path.add(currentFile.getPath());
            }
        }


        return path;
    }
}
