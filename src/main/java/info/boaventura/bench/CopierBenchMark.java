package info.boaventura.bench;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CopierBenchMark implements Copier {


  private final Copier copier;

  private Instant start;

  private List<Point> flow = new ArrayList<>();

  private class Point {
    String op;
    Duration duration;

    public Point(String op, Duration duration) {
      this.op = op;
      this.duration = duration;
    }

  }

  private void start() {
    start = Instant.now();
  }

  private void end(String op) {
    flow.add(new Point(op, Duration.between(start, Instant.now())));
  }

  public CopierBenchMark(Copier copier) {
    this.copier = copier;
  }

  @Override
  public void initialize() throws IOException {
    start();
    copier.initialize();
    end("initialiser");
  }

  public void read() throws IOException {
    start();
    copier.read();
    end("lire");
  }

  public void write() throws IOException {
    start();
    copier.write();
    end("écrivez");
  }

  public void close() {
    start();
    copier.close();
    end("fermer");
  }

  @Override
  public String getStrategy() {
    return copier.getStrategy();
  }

  private String human(Duration duration) {
    return duration.toString().substring(2)
        .replaceAll("(\\d[HMS])(?!$)", "$1 ")
        .toLowerCase();
  }

  public void summary() {
    Duration total = Duration.ofSeconds(0);
    for (Point point: flow) {
      System.out.printf("%s: %s\n", point.op, human(point.duration));
      total = total.plus(point.duration);
    }
    System.out.println("Dureé totale: " + human(total));
  }

  @Override
  public void cleanup() {
    copier.cleanup();
  }
}
