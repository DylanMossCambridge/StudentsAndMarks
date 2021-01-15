import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Students {

    HashMap<String,Float> studentsAndMarks;

    public static void main(String[] args) {
        Students students = new Students();
        students.add("Dylan",.59f);
        students.add("Leoni",.65f);
        students.add("Mingle",.53f);
        students.add("Henri",.57f);
        students.add("Sam",.49f);
        students.add("Kyra",.57f);
        students.add("Jonathan",.63f);

        System.out.printf("Alphabetical order of students: %s\n",students.alphabetical().toString());
        System.out.printf("Top 50%% of students: %s\n",students.topPercent(0.5f).toString());
        System.out.printf("Median mark: %f\n",students.median());
    }

    Students(){
        studentsAndMarks = new HashMap<>();
    }

    public void add(String name, float mark){
        studentsAndMarks.put(name,mark);
    }

    public List<String> alphabetical(){
        return studentsAndMarks.entrySet()
                .stream()
                .map(element -> element.getKey())
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> topPercent(float P) {

        int count = Math.round(studentsAndMarks.size() * P);

        return studentsAndMarks.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(element -> element.getKey())
                .limit(count)
                .collect(Collectors.toList());
    }

    public float median(){
        int size = studentsAndMarks.size();
        if(size % 2 == 0){
            return studentsAndMarks.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .skip(size/2)
                    .limit(2)
                    .map(element -> element.getValue())
                    .reduce(0f, Float::sum)/2;
        } else{
            return studentsAndMarks.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .skip(size/2)
                    .findFirst()
                    .map(element -> element.getValue())
                    .get();
        }
    }
}
