package com.github.q742972035.tools.exception;

import java.io.FileNotFoundException;

public class DirectoryNotFoundException extends FileNotFoundException {
    public DirectoryNotFoundException(String s) {
        super(s);
    }
}
