package info.boaventura.bench;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
      System.err.println("*** Erreur benching: " + e.getMessage());
    } finally {
      // close resources even in case of errors
      copierNewIOBufferReadBenchmark.close();
      copierNewIOBufferReadBenchmark.summary();
      copierNewIOBufferReadBenchmark.cleanup();
      printHead();
    }
  }

  private static void generateFile(String path, long size) throws IOException {
    System.out.printf("Génération de fichier dans: %s", path);
    RandomAccessFile f = new RandomAccessFile(path, "rw");
    f.setLength(size);
    f.close();
    System.out.printf("Fichier généré.");
  }

  /**
   * Génère un fichier de 1 Go sur n'importe quel système d'exploitation.
   * @param path Lieu où le fichier sera écrit
   * @throws IOException S'il y a un problème avec le fichier
   */
  private static void setup(String path) throws IOException {
    // Générer un fichier de 1GiB
    generateFile(path, (long)Math.pow(2, 30));
    new File(path).deleteOnExit();
  }

  public static void main(String[] args) {
    String fileSource = "1g.img";
    // La bonne chose serait de changer la source ou la destination pour différents endroits pour la vérification
    String source = Paths.get(System.getProperty("java.io.tmpdir"), fileSource).toString();
    String destiny = Paths.get(System.getProperty("java.io.tmpdir"), fileSource + ".to").toAbsolutePath().toString();

    try {
      setup(source);
    } catch (IOException e) {
      throw new RuntimeException("Impossible de continuer: le chemin n'est probablement pas disponible pour l'écriture", e);
    }

    try {
      bench(new CopierJournalDevSolution1(source, destiny, 2048));
      bench(new CopierJournalDevSolution2(source, destiny));
      bench(new CopierJournalDevSolution3(source, destiny));
      bench(new CopierNewIO(source, destiny));
      bench(new CopierNewIOBufferRead(source, destiny));
      bench(new CopierNewIOBufferRead2(source, destiny));
    } catch (Exception e) {
      throw new RuntimeException("Un problème est survenu lors de l'exécution d'une stratégie de copie de fichiers", e);
    }

  }

}
