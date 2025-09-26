package env;

import cartago.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Translator extends Artifact{

    private Process process;

    public void init(){
        /* 
        String pythonEXE = "C:/Users/guilh/OneDrive/Desktop/slmAgent/slmAgentMAS/src/env/slmAgentVEnv/Scripts/python.exe";
        String pythonScript = "C:/Users/guilh/OneDrive/Desktop/slmAgent/slmAgentMAS/src/env/modelUse.py";

        try {
            ProcessBuilder builder = new ProcessBuilder(pythonEXE, pythonScript);
            builder.redirectErrorStream(true);

            process = builder.start();

            System.out.println("[JAVA]: artifact build sucess");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("[JAVA]: artifact build fail");
        } */
    }

    @OPERATION public void translate(String sentence, OpFeedbackParam<String> result) {
        try {
            HttpURLConnection con = (HttpURLConnection)new URL("http://127.0.0.1:8000/translate").openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            os.write(("{\"sentence\":\""+sentence+"\"}").getBytes(StandardCharsets.UTF_8));
            os.flush();


            InputStream is = con.getInputStream();
            String resp = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            
            result.set(resp);

        } catch(Exception e) {
            result.set("ERROR");
        }
    }
}