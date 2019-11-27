package com.github.q742972035.tools;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class FileUtilsTest {

    @Test
    public void testFindAllFiles() throws FileNotFoundException {

        List<String> asc = FileUtils.findAllFilePaths("E:\\Program Files (x86)", "asc");
        List<String> cer = FileUtils.findAllFilePaths("E:\\Program Files (x86)", "cer");
        List<String> cert = FileUtils.findAllFilePaths("E:\\Program Files (x86)", "cert");
        List<String> crt = FileUtils.findAllFilePaths("E:\\Program Files (x86)", "crt");
        List<String> der = FileUtils.findAllFilePaths("E:\\Program Files (x86)", "der");
        List<String> pem = FileUtils.findAllFilePaths("E:\\Program Files (x86)", "pem");
        List<String> gpg = FileUtils.findAllFilePaths("E:\\Program Files (x86)", "gpg");
        List<String> p7c = FileUtils.findAllFilePaths("E:\\Program Files (x86)", "p7c");
        List<String> p12 = FileUtils.findAllFilePaths("E:\\Program Files (x86)", "p12");
        List<String> pfx = FileUtils.findAllFilePaths("E:\\Program Files (x86)", "pfx");
        List<String> pgp = FileUtils.findAllFilePaths("E:\\Program Files (x86)", "pgp");
//        E:\Program Files (x86)\GnuPG\share\gnupg\sks-keyservers.netCA.pem
    }

}
