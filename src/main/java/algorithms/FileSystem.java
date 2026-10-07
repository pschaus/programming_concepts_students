package algorithms;

import java.util.*;
import java.util.function.Predicate;

/**
 * (Proposed at the Exam of August 2025)
 * In this exercise, you have to implement a basic filesystem for a new operating system.
 *
 * A filesystem is a tree-based data structure, where each node corresponds to
 * one directory. The root node is the entry point of the filesystem.
 * Each directory can contain multiple subdirectories, as well as multiple files.
 */

public class FileSystem
{
    /**
     * Class that represents one file on the filesystem. A
     * file is characterized by a name (e.g., "Hello.txt") and by a
     * size (expressed in bytes).
     */
    static class File {
        private final String name;
        private final int size;

        /**
         * Constructs a file with the given name and size.
         */
        public File(String name, int size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public int getSize() {
            return size;
        }
    }


    /**
     * Class that represents one directory on the filesystem. A
     * directory is characterized by a name, by its subdirectories,
     * and by the files it stores.
     */
    static class Directory implements Iterable<File> {

        // TODO: Add the member variables you need here


        /**
         * Constructs a new Directory with the given name.
         * The directory is initially empty, with no files or subdirectories.
         *
         * @param name the name of the directory
         */
        public Directory(String name) {
            // TODO: constructor
        }

        /**
         * Returns the name of this directory.
         */
        public String getName() {
            // TODO
             return null;
        }

        /**
         * Add a new file to this directory.
         *
         * @param file the file to be added
         */
        public void addFile(File file) {
            // TODO
        }

        /**
         * Add a new subdirectory to this directory.
         *
         * @param directory the subdirectory to be added
         */
        public void addDirectory(Directory directory) {
            // TODO
        }

        /**
         * Returns the total size of all files in this directory and its subdirectories.
         *
         * @return the total size (expressed in bytes)
         */
        public int getTotalSize() {
            // TODO
             return -1;
        }


        /**
         * Returns an iterator over all the files in the Directory,
         * including files in its subdirectories. The order of the files is arbitrary.
         *
         * The FileSystem is assumed not be modified while iterating over the files:
         * There is thus no need to worry about ConcurrentModificationException.
         *
         * @return the iterator over all the files
         */
        @Override
        public Iterator<File> iterator() {
            // TODO
             return null;
        }

        /**
         * Returns an iterator over the files in the Directory that match the given filter,
         * including files in its subdirectories. The order of the files is arbitrary.
         *
         * The FileSystem is assumed not be modified while iterating over the files:
         * There is thus no need to worry about ConcurrentModificationException.
         *
         * @param filter a predicate to filter the files of interest
         * @return the iterator over the filtered files
         */
        public Iterator<File> iterator(Predicate<File> filter) {
            // TODO
             return null;
        }
    }

}
