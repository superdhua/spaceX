import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class ReaderFile {
    private static int i = 0;
    private static String regex= "[a-zA-z]";
    static HashMap<String,Integer> hashMap = new HashMap<>();
    public static void main(String[] args) throws Exception {
//        Path path = Paths.get("E:\\jd8");
//        System.out.print(Files.isDirectory(path));
         ReaderFile rf = new ReaderFile();
         File file = new File("E:\\jd8");
//         File file = new File("C:\\Users\\ms\\Desktop\\ss.java");
         rf.readFile(file);


        List<Map.Entry<String ,Integer>> list = new ArrayList<>(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> map, Map.Entry<String, Integer> t1) {
                return map.getValue().compareTo(t1.getValue());
            }
        });
        for (Map.Entry<String ,Integer> entry: list) {
            System.out.printf("单词:%-12s 出现次数:%d\n",entry.getKey(),entry.getValue());
        }
        System.out.print(hashMap.size());
//        Iterator<String> iterator=hashMap.keySet().iterator();
//        while(iterator.hasNext())
//        {
//            String word=iterator.next();
//			System.out.printf("单词： "+word+"出现次数："+hashMap.get(word));
//            System.out.printf("单词:%-12s 出现次数:%d\n",word,hashMap.get(word));
//        }
    }






    public void readFile(File file) throws Exception {
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f: files) {
                readFile(f);
            }
        }else if (file.isFile() && file.getName().toLowerCase().endsWith(".java")){
//            System.out.println(file.getName().toString());
            String line;
            BufferedReader br = new BufferedReader(new FileReader(file),2048);
            while ((line = br.readLine()) != null){
                  String[] linesword = line.split("\\W+");
                  Set<String> set = hashMap.keySet();

                for (String word :  linesword) {
                    if(set.contains(word)){
                        Integer num = hashMap.get(word);
                        num++;
                        hashMap.put(word,num);
                    }else {
                        hashMap.put(word,1);
                    }
                }
            }
            if (br != null){
                br.close();
            }
        }
    }
}
