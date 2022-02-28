package info.boaventura.bench;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class Main {

  private static void printHead() {
    System.out.println("-".repeat(80)); // String.format("%0" + n + "d", 0).replace("0", s);
  }

  private static void bench(Copier copier) {
    System.out.println(copier.getStrategy());
    printHead();
    CopierBenchMark copierNewIOBufferReadBenchmark = new CopierBenchMark(copier);
    try {
      copierNewIOBufferReadBenchmark.initialize();
      copierNewIOBufferReadBenchmark.read();
      copierNewIOBufferReadBenchmark.write();
    } catch (Exception e) {
      System.err.println("*** Error benching: " + e.getMessage());
    } finally {
      copierNewIOBufferReadBenchmark.summary();
      copierNewIOBufferReadBenchmark.cleanup();
      printHead();
    }
  }

  public static final void main(String[] args) throws IOException {
    String fileSource = "1g.img";
    String source = Paths.get(System.getProperty("user.home"), "work", fileSource).toString();
    String destiny = "/tmp/" + fileSource;

    //bench(new CopierJournalDevSolution1(source, destiny, 2048));

    //bench(new CopierJournalDevSolution2(source, destiny));
    //bench(new CopierJournalDevSolution3(source, destiny));
    bench(new CopierNewIOBufferRead2(source, destiny));
    bench(new CopierNewIOBufferRead(source, destiny));
    bench(new CopierNewIO(source, destiny));

  }

}
