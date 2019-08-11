package info.boaventura.bench;

import java.io.IOException;

public interface Copier {

  String getStrategy();

  void initialize() throws IOException;
  void read() throws IOException;
  void write() throws IOException;
  void close();
  void cleanup();

}
