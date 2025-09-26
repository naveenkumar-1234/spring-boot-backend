package com.codelab.backend.config;

import com.codelab.backend.enums.UserRoles;
import com.codelab.backend.model.*;
//import com.codelab.backend.repository.ExperimentRepository;
import com.codelab.backend.repository.StaffRepository;
import com.codelab.backend.repository.StudentRepository;
import com.codelab.backend.repository.UsersRepository;
import com.codelab.backend.service.ExperimentService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StartupDataLoader implements CommandLineRunner {

        @Autowired
        private UsersRepository userRepository;

        @Autowired
        private StaffRepository staffRepository;

        @Autowired
        private StudentRepository studentRepository;

        @Autowired
        private ExperimentService experimentService;

        private void addSubjectWiseExperiments() {
                Map<String, List<Experiments>> subjectExperimentMap = new HashMap<>();

                subjectExperimentMap.put("CS101", List.of(
                        createExperiment(1, "Linear Search",
                                "This experiment implements a simple linear search algorithm to check whether a given element exists in the input list or not.",
                                "Input size must be <= 100", "CS101",
                                List.of(
                                        new TestCases("5,8,12,20", "true"),
                                        new TestCases("1,3,5", "false")
                                )
                        ),
                        createExperiment(2, "Duplicate Element Check",
                                "Detect and return whether the given input array contains any duplicate elements. Useful for learning basic hashing or nested loops.",
                                "No duplicates allowed", "CS101",
                                List.of(
                                        new TestCases("1,2,3,4", "false"),
                                        new TestCases("1,2,2,4", "true")
                                )
                        ),
                        createExperiment(3, "Odd and Even Counter",
                                "Count and return the number of odd and even numbers present in a list. This helps understand conditional statements and loops.",
                                "Only non-negative integers", "CS101",
                                List.of(
                                        new TestCases("1,2,3,4", "Even:2,Odd:2"),
                                        new TestCases("7,8,10", "Even:2,Odd:1")
                                )
                        ),
                        createExperiment(4, "Bubble Sort Implementation",
                                "Sort a given array of numbers using the Bubble Sort algorithm and return the sorted result.",
                                "Array length <= 100", "CS101",
                                List.of(
                                        new TestCases("3,2,1", "1,2,3"),
                                        new TestCases("5,3,8,6", "3,5,6,8")
                                )
                        ),
                        createExperiment(5, "Frequency of Element",
                                "Calculate the frequency of each element in an input array. Output should contain each element and its count.",
                                "Integers only", "CS101",
                                List.of(
                                        new TestCases("1,2,2,3", "1:1,2:2,3:1"),
                                        new TestCases("4,4,4", "4:3")
                                )
                        )
                ));

                subjectExperimentMap.put("CS102", List.of(
                        createExperiment(1, "String Reversal",
                                "Write a program to reverse a given string. This experiment helps with string manipulation and loop understanding.",
                                "Maximum string length is 50", "CS102",
                                List.of(
                                        new TestCases("hello", "olleh"),
                                        new TestCases("world", "dlrow")
                                )
                        ),
                        createExperiment(2, "Palindrome Checker",
                                "Check whether a given string is a palindrome. A palindrome is a word that reads the same forwards and backwards.",
                                "Ignore case and spaces", "CS102",
                                List.of(
                                        new TestCases("madam", "true"),
                                        new TestCases("test", "false")
                                )
                        ),
                        createExperiment(3, "Vowel Counter",
                                "Count the number of vowels present in the input string. Focus on both lowercase and uppercase letters.",
                                "Input should be alphabetic only", "CS102",
                                List.of(
                                        new TestCases("apple", "2"),
                                        new TestCases("sky", "0")
                                )
                        ),
                        createExperiment(4, "Word Counter",
                                "Count the number of words in a sentence. Words are separated by spaces. Helps understand splitting strings.",
                                "Only simple English sentences", "CS102",
                                List.of(
                                        new TestCases("Hello world", "2"),
                                        new TestCases("This is a test case", "5")
                                )
                        ),
                        createExperiment(5, "Character Frequency",
                                "Display the frequency of each character in a given string. Useful to practice maps and loops.",
                                "No special characters", "CS102",
                                List.of(
                                        new TestCases("aabbc", "a:2,b:2,c:1"),
                                        new TestCases("abcabc", "a:2,b:2,c:2")
                                )
                        )
                ));

                subjectExperimentMap.put("IT101", List.of(
                        createExperiment(1, "Sum of Array Elements",
                                "Calculate and return the sum of all elements in an integer array. Fundamental for learning iterations.",
                                "Array length <= 100", "IT101",
                                List.of(
                                        new TestCases("1,2,3,4", "10"),
                                        new TestCases("10,20", "30")
                                )
                        ),
                        createExperiment(2, "Find Maximum Element",
                                "Find and return the maximum number from the input list. Helps with comparative logic and iteration.",
                                "Only positive numbers", "IT101",
                                List.of(
                                        new TestCases("5,2,9,1", "9"),
                                        new TestCases("10,30,20", "30")
                                )
                        ),
                        createExperiment(3, "Check Prime Number",
                                "Check whether the given input number is a prime or not. Useful for learning conditionals and loops.",
                                "Input <= 100", "IT101",
                                List.of(
                                        new TestCases("7", "true"),
                                        new TestCases("10", "false")
                                )
                        ),
                        createExperiment(4, "Factorial Calculator",
                                "Calculate the factorial of a given number using loop or recursion. A classic algorithm problem.",
                                "Input <= 10", "IT101",
                                List.of(
                                        new TestCases("5", "120"),
                                        new TestCases("3", "6")
                                )
                        ),
                        createExperiment(5, "Check Armstrong Number",
                                "Verify if the given number is an Armstrong number. An Armstrong number is equal to the sum of cubes of its digits.",
                                "Input is a 3-digit number", "IT101",
                                List.of(
                                        new TestCases("153", "true"),
                                        new TestCases("123", "false")
                                )
                        )
                ));

                // Save to DB
                subjectExperimentMap.forEach((subjectCode, experimentsList) -> {
                        for (Experiments exp : experimentsList) {
                                experimentService.addExperiment(exp);
                        }
                });

                System.out.println("Subject-wise 5 experiments per subject added successfully at startup.");
        }

        private Experiments createExperiment(int no, String name, String desc, String constraints, String subjectCode, List<TestCases> testCasesList) {
                Experiments exp = new Experiments();
                exp.setExperimentNo(no);
                exp.setExperimentName(name);
                exp.setDescription(desc);
                exp.setConstraints(constraints);
                exp.setSubjectCode(subjectCode);
                exp.setCompletedDate(new Date());
                exp.setTestCasesList(testCasesList);
                return exp;
        }

        @Override
        public void run(String... args) throws Exception {

                //Add experiments
                addSubjectWiseExperiments();
                // ➤ Admin
                Users admin = new Users();
                admin.setUsername("Admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(BCrypt.hashpw("admin@123", BCrypt.gensalt()));
                admin.setSpr(1000);
                admin.setUserRole(UserRoles.ADMIN);
                userRepository.save(admin);
                System.out.println("Admin user saved at startup.");

                // ➤ Staffs
                String[] staffNames = { "Ramesh", "Priya", "Sasikala" };
                int[] staffSprs = { 2001, 2002, 2003 };
                String[] subCodes = {"CS101","CS102","IT101"};

                for (int i = 0; i < staffNames.length; i++) {
                        Users staffUser = new Users();
                        staffUser.setUsername(staffNames[i]);
                        staffUser.setEmail(staffNames[i].toLowerCase() + "@gmail.com");
                        staffUser.setPassword(BCrypt.hashpw("staff@123", BCrypt.gensalt()));
                        staffUser.setSpr(staffSprs[i]);
                        staffUser.setUserRole(UserRoles.STAFF);
                        userRepository.save(staffUser);

                        Staff staff = new Staff();
                        staff.setDepartment("Computer Science");
                        staff.setSubjectCodes(List.of(subCodes[i]));
                        staff.setUser(staffUser);
                        staffRepository.save(staff);
                }

                // ➤ Students
                String[] studentNames = {
                        "Naveen", "Manikandan", "Siva", "Kumar", "Sakthi",
                        "Dinesh", "Suriya", "Deepan", "Kayal", "Vignesh"
                };

                int studentSprStart = 3000;

                for (int i = 0; i < studentNames.length; i++) {
                        Users studentUser = new Users();
                        studentUser.setUsername(studentNames[i]);
                        studentUser.setEmail(studentNames[i].toLowerCase() + "@gmail.com");
                        studentUser.setPassword(BCrypt.hashpw("student@123", BCrypt.gensalt()));
                        studentUser.setSpr(studentSprStart + i);
                        studentUser.setUserRole(UserRoles.STUDENT);
                        userRepository.save(studentUser);

                        Student student = new Student();
                        student.setDepartment("Computer Science");
                        student.setRegisterNumber("REG2023" + String.format("%05d", i + 1));
                        student.setYear(3);
                        student.setSemester(6);
                        student.setSubjectCodes(List.of("CS101"));
                        student.setUser(studentUser);
                        studentRepository.save(student);
                }

                String[] studentNames2 = {
                        "Harish", "Kavitha", "Joel", "John", "Lakshmi",
                        "Karthick", "Fathima", "Deepak", "Keerthi", "Prince"
                };

                int studentSprStart2 = 4000;

                for (int i = 0; i < studentNames2.length; i++) {
                        Users studentUser = new Users();
                        studentUser.setUsername(studentNames2[i]);
                        studentUser.setEmail(studentNames2[i].toLowerCase() + "@gmail.com");
                        studentUser.setPassword(BCrypt.hashpw("student@123", BCrypt.gensalt()));
                        studentUser.setSpr(studentSprStart2 + i);
                        studentUser.setUserRole(UserRoles.STUDENT);
                        userRepository.save(studentUser);

                        Student student = new Student();
                        student.setDepartment("Computer Science");
                        student.setRegisterNumber("REG2022" + String.format("%05d", i + 1));
                        student.setYear(3);
                        student.setSemester(6);
                        student.setSubjectCodes(List.of("CS102"));
                        student.setUser(studentUser);
                        studentRepository.save(student);
                }

                String[] studentNames3 = {
                        "Anbu", "Tharun", "Nandha", "Yash", "Preethi",
                        "Oviya", "Ashik", "Dhanush", "Thillai", "James"
                };

                int studentSprStart3 = 5000;

                for (int i = 0; i < studentNames3.length; i++) {
                        Users studentUser = new Users();
                        studentUser.setUsername(studentNames3[i]);
                        studentUser.setEmail(studentNames3[i].toLowerCase() + "@gmail.com");
                        studentUser.setPassword(BCrypt.hashpw("student@123", BCrypt.gensalt()));
                        studentUser.setSpr(studentSprStart3 + i);
                        studentUser.setUserRole(UserRoles.STUDENT);
                        userRepository.save(studentUser);

                        Student student = new Student();
                        student.setDepartment("Information Technology");
                        student.setRegisterNumber("REG2023" + String.format("%02d", i));
                        student.setYear(2);
                        student.setSemester(6);
                        student.setSubjectCodes(List.of("IT101"));
                        student.setUser(studentUser);
                        studentRepository.save(student);
                }

                System.out.println("Admin, Staff, and Students saved at startup.");
        }
}
