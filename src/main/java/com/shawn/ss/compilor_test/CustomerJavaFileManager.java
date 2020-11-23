package com.shawn.ss.compilor_test;

import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class CustomerJavaFileManager implements JavaFileManager {
    @Override
    public int isSupportedOption(String option) {
        return standardFileManager.isSupportedOption(option);
    }

    private final StandardJavaFileManager standardFileManager;

    public CustomerJavaFileManager(StandardJavaFileManager standardFileManager) {

        this.standardFileManager = standardFileManager;
    }

    @Override
    public boolean isSameFile(FileObject a, FileObject b) {
        return standardFileManager.isSameFile(a, b);
    }

    @Override
    public ClassLoader getClassLoader(Location location) {
        System.out.println("call getClassLoader location:"+location);
        return standardFileManager.getClassLoader(location);
    }

    @Override
    public Iterable<JavaFileObject> list(Location location, String packageName, Set<JavaFileObject.Kind> kinds, boolean recurse) throws IOException {
        return standardFileManager.list(location, packageName, kinds, recurse);
    }

    @Override
    public String inferBinaryName(Location location, JavaFileObject file) {
        return standardFileManager.inferBinaryName(location, file);
    }

    @Override
    public boolean handleOption(String current, Iterator<String> remaining) {
        System.out.println("call handleOption param:"+current +" remain:"+remaining);
        return standardFileManager.handleOption(current, remaining);
    }

    @Override
    public boolean hasLocation(Location location) {
        return standardFileManager.hasLocation(location);
    }

    @Override
    public JavaFileObject getJavaFileForInput(Location location, String className, JavaFileObject.Kind kind) throws IOException {
        return standardFileManager.getJavaFileForInput(location, className, kind);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        return standardFileManager.getJavaFileForOutput(location, className, kind, sibling);
    }

    @Override
    public FileObject getFileForInput(Location location, String packageName, String relativeName) throws IOException {
        return standardFileManager.getFileForInput(location, packageName, relativeName);
    }

    @Override
    public FileObject getFileForOutput(Location location, String packageName, String relativeName, FileObject sibling) throws IOException {
        return standardFileManager.getFileForOutput(location, packageName, relativeName, sibling);
    }

    @Override
    public void flush() throws IOException {
        standardFileManager.flush();
    }

    @Override
    public void close() throws IOException {
        standardFileManager.close();
    }

    public Iterable<? extends JavaFileObject> getJavaFileObjectsFromFiles(Iterable<? extends File> files) {
        return standardFileManager.getJavaFileObjectsFromFiles(files);
    }

    public Iterable<? extends JavaFileObject> getJavaFileObjects(File... files) {
        return standardFileManager.getJavaFileObjects(files);
    }

    public Iterable<? extends JavaFileObject> getJavaFileObjectsFromStrings(Iterable<String> names) {
        return standardFileManager.getJavaFileObjectsFromStrings(names);
    }

    public Iterable<? extends JavaFileObject> getJavaFileObjects(String... names) {
        return standardFileManager.getJavaFileObjects(names);
    }

    public void setLocation(Location location, Iterable<? extends File> path) throws IOException {
        standardFileManager.setLocation(location, path);
    }

    public Iterable<? extends File> getLocation(Location location) {
        return standardFileManager.getLocation(location);
    }

}
