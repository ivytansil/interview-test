import com.sun.tools.javac.util.Pair;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Main {

  private static int findMaxProfit(List<Integer> numbers) {
    Pair<Integer, Integer> minNumb = Pair.of(0, numbers.get(0));
    Pair<Integer, Integer> maxNumb = Pair.of(0, numbers.get(0));

    TreeSet<Integer> profits = new TreeSet<>();
    for (int i = 0; i < numbers.size() - 1; i++) {
      if (minNumb.snd > numbers.get(i + 1)) {
        minNumb = Pair.of(i + 1, numbers.get(i + 1));
      }

      if (maxNumb.snd < numbers.get(i + 1)) {
        maxNumb = Pair.of(i + 1, numbers.get(i + 1));
      }

      if (minNumb.fst < maxNumb.fst) {
        profits.add(maxNumb.snd - minNumb.snd);
      }
    }

    int result = 0;
    return profits.isEmpty() ? result : profits.last();
  }

  public static void main(String[] args) {
    try {
      String path = Paths.get("src", "main", "resources", "file.txt").toFile().getAbsolutePath();
      File file = new File(path);
      FileInputStream fileInputStream = new FileInputStream(file);
      DataInputStream dataInputStream = new DataInputStream(fileInputStream);
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
      String line;
      String[] arr = null;
      while ((line = bufferedReader.readLine()) != null) {
        arr = line.split(" ");
      }

      List<Integer> numbers = new ArrayList<>();
      for (String data : arr) {
        numbers.add(Integer.parseInt(data));
      }

      System.out.print("Maximum Profit : " + findMaxProfit(numbers) + "\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
