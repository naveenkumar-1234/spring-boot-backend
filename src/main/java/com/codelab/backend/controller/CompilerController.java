        package com.codelab.backend.controller;

        import com.codelab.backend.model.CodeRequest;
        import com.codelab.backend.model.CodeResponse;
        import com.codelab.backend.service.PdfService;
        import com.codelab.backend.utility.QRCodeGenerator;
        import org.springframework.web.bind.annotation.*;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.io.InputStreamReader;
        import java.util.*;


        @CrossOrigin(origins = "http://localhost:3000")
        @RestController
        @RequestMapping("/compile")
        public class CompilerController {

            private final PdfService pdfService;

            public CompilerController(PdfService pdfService) {
                this.pdfService = pdfService;
            }


            @PostMapping("/java")
                public CodeResponse javaCompiler(@RequestBody CodeRequest request){
                    String userCode = request.getCode();
                    List<TestCase> testCases = List.of(
                            new TestCase("10, 20", "30"),
                            new TestCase("5, 5", "10"),
                            new TestCase("100, 50", "150")
                    );

                    System.out.println(userCode);
                    try{
                        BufferedWriter solutionWriter = new BufferedWriter(new FileWriter("./Dockers/java-compiler/Solution.java"));
                        solutionWriter.write(userCode);
                        solutionWriter.close();
                        String methodName = "addTwoNumbers";
                        System.out.println("written");
                        StringBuilder mainCode = new StringBuilder("public class Main {\npublic static void main(String[] args) {\nSolution s = new Solution();\n");
    //
    //                    String mainCode =String.format("""
    //                    public class Main {
    //                    public static void main(String[] args) {
    //                    Solution s = new Solution();
    //                    System.out.println(s.solution(10, 20));
    //            }
    //        }
    //        """, methodName);
                        for(int i = 0 ; i < testCases.size() ; i++){
                            mainCode.append(String.format("System.out.println(\"TC%d:\" + s.%s(%s));\n", i, methodName, testCases.get(i).input()));
                        }
                        mainCode.append("}\n}");
                        BufferedWriter mainWriter = new BufferedWriter(new FileWriter( "./Dockers/java-compiler/Main.java"));
                        mainWriter.write(mainCode.toString());
                        mainWriter.close();

        //                ProcessBuilder processBuilder = new ProcessBuilder("docker","build","-t","java-runner","./Dockers/java-compiler/");
        //                Process process = processBuilder.start();
        //                BufferedReader rd = new BufferedReader(new InputStreamReader(process.getInputStream()));
        //                String line;
        //                while ((line = rd.readLine()) != null) {
        //                    System.out.println(line);
        //                }

                        String hostPath = System.getProperty("user.dir") + "/Dockers/java-compiler";
                        ProcessBuilder processBuilder = new ProcessBuilder(
                                "docker", "run", "--rm", "-v", hostPath+":/app", "java-runner"
                        );

                        processBuilder.redirectErrorStream(true);
                        Process process = processBuilder.start();
        //                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process1.getInputStream()));
    //                    BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
    //                    BufferedReader stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
    //                    String line1;
    //                    List<String> actualOutputs = new ArrayList<>();
    //                    String line;
    //
    //                    while ((line = stdOut.readLine()) != null) {
    //                        if (line.startsWith("TC")) {
    //                            actualOutputs.add(line.trim());
    //                        }
    //                    }
    //                    StringBuilder output = new StringBuilder();
    //                    while ((line1 = stdOut.readLine()) != null) {
    //    //                    System.out.println(line1);
    //                        output.append(line1).append("\n");
    //                    }
    //                    System.out.println(stdErr.toString());
    //                    while ((line1 = stdErr.readLine()) != null) {
    //                        output.append(line1).append("\n");
    //                    }
                        System.out.println("in before input stream");

                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        List<String> actualOutputs = new ArrayList<>();
                        StringBuilder fullOutput = new StringBuilder();
                        System.out.println("after");
                        System.out.println(fullOutput);
                        String line;
                        boolean isCompileError = false;
                        while ((line = reader.readLine()) != null) {
                            fullOutput.append(line).append("\n");
                            if (line.contains("error") || line.contains("Exception")) {
                                isCompileError = true;

                            }else if (line.startsWith("TC")) {
                                actualOutputs.add(line.trim() +"\n");
                            }
                        }

                        int exitCode = process.waitFor();

                        if (isCompileError || actualOutputs.size() < testCases.size()) {
                            return new CodeResponse("failure", "Compilation or Runtime Error:\n" + fullOutput.toString());
                        }

    //                    int exitCode = process.waitFor();
                        System.out.println("-------------");
                        for (int i = 0; i < testCases.size(); i++) {
                            String expected = testCases.get(i).expected();
                            String inputs = testCases.get(i).input();

                            String actual = actualOutputs.get(i).split(":")[1].trim();
                            if (!expected.equals(actual)) {
                                return new CodeResponse("failure", "Test case " + i + " failed.\nInputs are "+ inputs +".\nExpected output : " + expected + ".\nBut actual output: " + actual);
                            }
                        }
                        System.out.println(actualOutputs);
                        System.out.println("------------------");
                        System.out.println(fullOutput);
    //                    return new CodeResponse("success", "All test cases passed.");
                        StringBuilder formattedOutput = new StringBuilder();
                        for (int i = 0; i < testCases.size(); i++) {
                            formattedOutput.append("Inputs: ").append(testCases.get(i).input()).append("\n");
                            formattedOutput.append("Output: ").append(testCases.get(i).expected()).append("\n\n");
                        }
                        System.out.println(formattedOutput.toString());
                        String uniqueId = UUID.randomUUID().toString().toUpperCase().substring(0, 6);
                        System.out.println(uniqueId);


                        byte[] qrBytes = QRCodeGenerator.generateBarCodeImage(uniqueId, 150, 45);
                        String qrBase64 = Base64.getEncoder().encodeToString(qrBytes);

                        Map<String, Object> pdfData = new HashMap<>();
                        pdfData.put("experimentNo", "01");
                        pdfData.put("title", "ADD TWO NUMBERS");
                        pdfData.put("name", "NAVEEN K - 8981");
//                        pdfData.put("")
                        pdfData.put("uniqueId", uniqueId);
                        pdfData.put("code", userCode);
                        pdfData.put("outputText", formattedOutput.toString());
                        pdfData.put("qrCodeBase64", qrBase64);

                        byte[] pdfBytes = pdfService.generatePdf(pdfData);
                        String pdfBase64 = Base64.getEncoder().encodeToString(pdfBytes);

                        return new CodeResponse(pdfBase64, "success", "All test case passed");
                        //                System.out.println(output);
    //                    return new CodeResponse("success",output.toString());

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    return new CodeResponse("server error","empty");
                }

                record TestCase(String input , String expected){}
        }
