package com.codelab.backend.controller;

import com.codelab.backend.model.CodeRequest;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/compile")
public class CompilerController {

        @PostMapping("/java")
        public String javaCompiler(@RequestBody CodeRequest request){
            String code = request.getCode();
            String methodName = "addTwoNumbers";
            System.out.println(code);
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter("./Dockers/java-compiler/Solution.java"));
                writer.write(code);
                writer.close();
                System.out.println("written");
                String mainCode =String.format("""
    public class Main {
        public static void main(String[] args) {
            Solution s = new Solution();
            System.out.println(s.%s(10, 20));
        }
    }
    """, methodName);
                BufferedWriter mainWriter = new BufferedWriter(new FileWriter( "./Dockers/java-compiler/Main.java"));
                mainWriter.write(mainCode);
                mainWriter.close();

//                ProcessBuilder processBuilder = new ProcessBuilder("docker","build","-t","java-runner","./Dockers/java-compiler/");
//                Process process = processBuilder.start();
//                BufferedReader rd = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                String line;
//                while ((line = rd.readLine()) != null) {
//                    System.out.println(line);
//                }

                String hostPath = System.getProperty("user.dir") + "/Dockers/java-compiler";
                ProcessBuilder processBuilder1 = new ProcessBuilder(
                        "docker", "run", "--rm", "-v", hostPath+":/app", "java-runner"
                );

                Process process1 = processBuilder1.start();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process1.getInputStream()));
                BufferedReader stdOut = new BufferedReader(new InputStreamReader(process1.getInputStream()));
                BufferedReader stdErr = new BufferedReader(new InputStreamReader(process1.getErrorStream()));
                String line1;
                StringBuilder output = new StringBuilder();
                while ((line1 = stdOut.readLine()) != null) {
//                    System.out.println(line1);
                    output.append(line1).append("\n");
                }
                System.out.println(stdErr.toString());
                while ((line1 = stdErr.readLine()) != null) {
                    output.append(line1).append("\n");
                }
                int exitCode = process1.waitFor();
                System.out.println("-------------");
//                System.out.println(output);
                return output.toString();

            }catch (Exception e){
                e.printStackTrace();
            }


            return "works";
        }
}
