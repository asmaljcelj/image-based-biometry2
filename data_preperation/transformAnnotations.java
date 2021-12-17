import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class transformAnnotations {

    static double width = 480;
    static double height = 360;
    public static void main(String[] args) throws Exception {
        File dir = new File("./test_YOLO_format");
        File[] annotationsFiles = dir.listFiles();

        if (annotationsFiles != null) {
            for (File file : annotationsFiles) {
                Scanner sc = new Scanner(file);
                String line = sc.nextLine();
                String[] split = line.split(" ");
                double[] newSplit = new double[4];

                newSplit[0] = Double.valueOf(split[1]) / width;
                newSplit[1] = Double.valueOf(split[2]) / height;
                newSplit[2] = Double.valueOf(split[3]) / width;
                newSplit[3] = Double.valueOf(split[4]) / height;

                newSplit[0] += (newSplit[2] / 2);
                newSplit[1] += (newSplit[3] / 2);

                String newLine = split[0] + " " + Arrays.stream(newSplit).mapToObj(Double::toString).collect(Collectors.joining(" "));
                
                File newFile = new File("new_annotations/" + file.getName());
                newFile.createNewFile();
                FileWriter myWriter = new FileWriter(newFile);
                myWriter.write(newLine);

                myWriter.close();
                sc.close();
            }
        }
        
    }

    

}