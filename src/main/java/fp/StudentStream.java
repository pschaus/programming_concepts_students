package fp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StudentStream {
    public static class Grade {
        private String course;
        private double value;

        public Grade(String course,
                     double value) {
            this.course = course;
            this.value = value;
        }

        public String getCourse() {
            return course;
        }

        public double getValue() {
            return value;
        }
    }
    
    public static class Student {
        private String firstName;
        private String lastName;
        private int section;
        private Map<String, Double> grades = new HashMap<>();

        public Map<String, Double> getCourses_results() {  // TODO REMOVE
            return grades;
        }

        public Student(String firstName,
                       String lastName,
                       int section) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.section = section;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getSection() {
            return section;
        }

        public void addGrade(String course,
                             double grade) {
            grades.put(course, grade);
        }

        public boolean hasGrade(String course) {
            return grades.containsKey(course);
        }

        public double getGrade(String course) {
            if (hasGrade(course)) {
                return grades.get(course);
            } else {
                throw new IllegalArgumentException();
            }
        }

        public Stream<Grade> getGradesStream() {
            List<Grade> lst = new ArrayList<>();
            for (Map.Entry<String, Double> entry : grades.entrySet()) {
                lst.add(new Grade(entry.getKey(), entry.getValue()));
            }
            return lst.stream();
        }
    }

    /**
     * Find the N°2 and N°3 top students for the course name given as parameter.
     **/
    public static Stream<Student> findSecondAndThirdTopStudentForGivenCourse(Stream<Student> students, String name) {
        // TODO
         return null;
    }


    /**
     * Compute, for each student in the given section, their average grade result,
     * in the form of an array of arrays structured as follows:
     *
     * [ ["Student FirstName1 LastName1", 7.5], ["Student FirstName2 LastName2", 9.5] ]
     **/
    public static Object[] computeAverageForStudentInSection(Stream<Student> students, int section) {
        // TODO
         return null;
    }

    /**
     * Give the number of students that passed all courses (all grades > 10.0).
     **/
    public static long getNumberOfSuccessfulStudents(Stream<Student> students) {
        // TODO
         return 0;
    }

    /**
     * Find the last student in lexicographic order (First compare
     * students on their lastNames and then on their firstNames)
     **/
    public static Student findLastInLexicographicOrder(Stream<Student> students) {
        // TODO
         return null;
    }

    /**
     * Give the total sum of all grades obtained by all students.
     **/
    public static double getFullSum(Stream<Student> students) {
        // TODO
         return 0;
    }


    
    
    // In order to test your code efficiently, input conditions take the
    // form of a Map<String, Predicate<?>> object structured as follows:
    //    Key   : String corresponding to one of the fields of Student objects
    //            ("firstName", "lastName", "section", "courses_results")
    //    Value : Predicate bound to the type of the field on which the condition applies
    //
    // For example:
    //    Key   : "firstName"
    //    Value : Predicate<String>


    // Returns a student that matches the given conditions
    // If there is none, returns null
    public static Student findFirst(Stream<Student> studentsStream,
                                    Map<String, Predicate<?>> conditions) {
        // TODO
         return null;
    }

    // Returns an array of student(s) that match the given conditions
    public static Student[] findAll(Stream<Student> studentsStream,
                                    Map<String, Predicate<?>> conditions) {
        // TODO
         return null;
    }

    // Returns true if there are at least n student(s) that match the given conditions
    public static boolean exists(Stream<Student> studentsStream,
                                 Map<String, Predicate<?>> conditions,
                                 int n) {
        // TODO
         return false;
    }

    // Returns an array of student(s) that match the given conditions,
    // ordered according to the given comparator
    public static Student[] filterThenSort(Stream<Student> studentsStream,
                                           Map<String, Predicate<?>> conditions,
                                           Comparator<Student> comparator) {
        // TODO
         return null;
    }
}
