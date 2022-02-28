package info.boaventura.bench;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CopierJournalDevSolution3 implements Copier {

  File source;
  File destiny;

  public CopierJournalDevSolution3(String source, String destiny) {
    this.source = new File(source);
    this.destiny = new File(destiny);
  }

  @Override
  public String getStrategy() {
    return "Solution 3 (commons-io 2.6): https://www.journaldev.com/861/java-copy-file";
  }

  @Override
  public void initialize() throws IOException {
  }

  @Override
  public void read() throws IOException {
  }

  @Override
  public void write() throws IOException {
    FileUtils.copyFile(source, destiny);
  }

  @Override
  public void close() {
  }

  @Override
  public void cleanup() {
    destiny.delete();
  }

}
