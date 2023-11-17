package stacklab;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;


public class Lister {

    private File file;
    private boolean showHidden;
    private boolean prettyPrint = false;

    public Lister(File file, boolean showHidden, boolean prettyPrint) {
        this.file = file;
        this.showHidden = showHidden;
        this.prettyPrint = prettyPrint;
    }

    public void list() {
//        listFilesRecurse(file);
        listFilesStack(file);
    }

    private void listFilesRecurse(File f) {
        // Different logic for directories vs files:
        // - Files are base cases; they have no leaves on the call graph
        // - Directories are non-leaf nodes in the call graph
        if (f.isDirectory()) {
            // Iterate all subitems of this directory, which could be a directory or a file; this shall be handled in the recursive listFilesRecurse() call
            File[] files = f.listFiles();
            for (File file : files) {
                // Descend into the recursive call
                listFilesRecurse(file);
            }
        } else {
            // Print the file to stdout only if it's requested by user:
            // - If it's hidden and the user requested to show them (e.g. `ls -a`), print them
            // - Otherwise do not show hidden files (e.g. `ls`)
            if (showHidden || !f.isHidden()) {
                System.out.println(f.getName());
            }
        }
    }

    private static class FileStackJob {
        File[] subitems;
        int currN;
        int childDepth;
    }

    //fill this in
    private void listFilesStack(File f) {
        Deque<FileStackJob> stack = new ArrayDeque<>();
        listFilesStackHelper(f, 0, stack);
        while (!stack.isEmpty()) {
            var top = stack.peekLast();
            if (top.currN < top.subitems.length) {
                listFilesStackHelper(top.subitems[top.currN++], top.childDepth, stack);
            } else {
                stack.removeLast();
            }
        }
    }

    private void listFilesStackHelper(File f, int depth, Deque<FileStackJob> stack) {
        if (f.isDirectory()) {
            if (prettyPrint) {
                for (int i = 0; i < depth * 2; i++) {
                    System.out.print(' ');
                }
                System.out.println(f.getName() + "/");
            }

            var e = new FileStackJob();
            e.subitems = f.listFiles();
            e.currN = 0;
            e.childDepth = depth + 1;
            stack.addLast(e);
        } else {
            if (showHidden || !f.isHidden()) {
                if (prettyPrint) {
                    for (int i = 0; i < depth * 2; i++) {
                        System.out.print(' ');
                    }
                }
                System.out.println(f.getName());
            }
        }
    }

    public static void main(String[] args) {
        //replace with a directory of your own
//        String directory = "/Users/agc/eclipse-workspace/homework5";
        String directory = ".";
        File dir = new File(directory);
        Lister l = new Lister(dir, true, false);
        l.list();
    }

}
