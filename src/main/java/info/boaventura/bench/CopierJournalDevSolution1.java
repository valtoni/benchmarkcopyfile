package info.boaventura.bench;

import java.io.*;

public class CopierJournalDevSolution1 implements Copier {

  File source;
  File destiny;

  FileInputStream is;
  FileOutputStream os;

  int bufferlen;

  public CopierJournalDevSolution1(String source, String destiny, int bufferlen) {
    this.source = new File(source);
    this.destiny = new File(destiny);
    this.bufferlen = bufferlen;
  }

  @Override
  public String getStrategy() {
    return "Solution 1: https://www.journaldev.com/861/java-copy-file";
  }

  @Override
  public void initialize() throws IOException {
    is = new FileInputStream(source);
    os = new FileOutputStream(destiny);
  }

  @Override
  public void read() throws IOException {
  }

  @Override
  public void write() throws IOException {
      byte[] buffer = new byte[bufferlen];
      int length;
      while ((length = is.read(buffer)) > 0) {
        os.write(buffer, 0, length);
      }
  }

  @Override
  public void close() {
    IOFacility.closeResource(is);
    IOFacility.closeResource(os);
  }

  @Override
  public void cleanup() {
    destiny.delete();
  }
}
