package processingvcf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProcessingVCF {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String dir="d:/lomolith/Downloads";
        String file="acc_vcf.txt";
        
        if (args!=null && args.length>0) {
            dir=args[0];
            if (args.length>1) file=args[1];
        }
        FileWriter fw = new FileWriter(dir+"/"+file.substring(0,file.indexOf(".txt"))+".converted.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        FileReader fr = new FileReader(dir+"/"+file);
        BufferedReader br = new BufferedReader(fr);
        String line=br.readLine();
        bw.write(line+"\n");
        while((line=br.readLine())!=null) {
            String cols[] = line.replaceAll("\\./\\.:\\.:\\.", "0").split("\t");
            String output="";
            for (String col: cols) {
                if (!output.equals("")) output+="\t";
                if (col.equals("0")) output+="0";
                else output+="1";
            }
            bw.write(output+"\n");
        }
        bw.close();
        fw.close();
        br.close();
        fr.close();
    }
    
}
