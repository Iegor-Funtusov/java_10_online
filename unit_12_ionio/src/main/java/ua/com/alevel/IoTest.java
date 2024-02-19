package ua.com.alevel;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class IoTest {

    public void test() {
//        createFile(FileType.FILE_NAME.getPath());
//        createDirectory(FileType.DIR_NAME.getPath());
//        createDirectories(FileType.DIRS_NAME.getPath());
//        readDir(FileType.DIR_NAME.getPath());
//        deleteFile(FileType.FILE_NAME.getPath());
//        deleteDir(FileType.DIR_NAME.getPath());
//        deleteApacheDir(FileType.DIR_NAME.getPath());
        renameFile(FileType.FILE_NAME.getPath(), "test1.txt");
    }

    private void createFile(String path) {
        File file = new File(path);
        boolean isFile = file.isFile();
        System.out.println("isFile = " + isFile);
        String absolutePath = file.getAbsolutePath();
        System.out.println("absolutePath = " + absolutePath);
        try {
            file.createNewFile();
            isFile = file.isFile();
            System.out.println("isFile = " + isFile);
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private void createDirectory(String path) {
        File file = new File(path);
        boolean directory = file.isDirectory();
        System.out.println("directory = " + directory);
        String absolutePath = file.getAbsolutePath();
        System.out.println("absolutePath = " + absolutePath);
        file.mkdir();
    }

    private void createDirectories(String path) {
        File file = new File(path);
        boolean directory = file.isDirectory();
        System.out.println("directory = " + directory);
        String absolutePath = file.getAbsolutePath();
        System.out.println("absolutePath = " + absolutePath);
        boolean mkdir = file.mkdirs();
        System.out.println("mkdir = " + mkdir);
    }

    private void readDir(String path) {
        File file = new File(path);
        if (file.isFile()) {
            System.out.println("file = " + file.getName());
        } else {
            readDirectory(file);
        }
    }

    private void deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            boolean delete = file.delete();
            System.out.println("delete = " + delete);
        }
    }

    private void deleteDir(String path) {
        File file = new File(path);
        System.out.println("file = " + file.getAbsolutePath());
        if (file.exists()) {
            boolean delete = file.delete();
            System.out.println("delete = " + delete);
        }
    }

    private void deleteApacheDir(String path) {
        File file = new File(path);
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void renameFile(String oldPath, String newPath) {
        File oldFile = new File(oldPath);
        File newFile = new File(newPath);
        if (oldFile.exists() && !newFile.exists()) {
            boolean isRenamed = oldFile.renameTo(newFile);
            System.out.println("isRenamed = " + isRenamed);
        }
    }

    private void readDirectory(File dir) {
        System.out.println("dir = " + dir.getAbsolutePath());
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                readDirectory(file);
            } else {
                System.out.println("file = " + file.getAbsolutePath());
            }
        }
    }
}
