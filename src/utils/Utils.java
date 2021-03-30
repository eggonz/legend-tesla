package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Utils {

    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();

        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new BufferedInputStream(
                            Utils.class.getResourceAsStream(path))));
            String line;
            while ((line = br.readLine()) != null)
                builder.append(line + "\n");

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static String[] loadFileAsLines(String path){
        try{

            ArrayList<String> lines = new ArrayList<>();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new BufferedInputStream(
                            Utils.class.getResourceAsStream(path))));
            String line;

            while((line = br.readLine())!=null){
                lines.add(line);
            }

            return lines.toArray(new String[lines.size()]);

        } catch (IOException ex){
            ex.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0; //default
        }
    }

    public static int[] copyArray(int[] src) {
        return Arrays.copyOf(src, src.length);
    }

    public static int[][] copyArray2(int[][] src) {
        int[][] dst = new int[src.length][];
        for (int i = 0; i < src.length; i++) {
            dst[i] = Arrays.copyOf(src[i], src[i].length);
        }
        return dst;
    }
}
