package info.boaventura.bench;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopierJournalDevSolution2 implements Copier {

  File source;
  File destiny;

  FileChannel sourceChannel;
  FileChannel destChannel;

  public CopierJournalDevSolution2(String source, String destiny) {
    this.source = new File(source);
    this.destiny = new File(destiny);
  }

  @Override
  public String getStrategy() {
    return "Solution 2: https://www.journaldev.com/861/java-copy-file";
  }

  @Override
  public void initialize() throws IOException {
    sourceChannel = new FileInputStream(source).getChannel();
    destChannel = new FileOutputStream(destiny).getChannel();
  }

  @Override
  public void read() throws IOException {
  }

  @Override
  public void write() throws IOException {
    destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
  }

  @Override
  public void close() {
    IOFacility.closeResource(sourceChannel);
    IOFacility.closeResource(destChannel);
  }

  @Override
  public void cleanup() {
    destiny.delete();
  }

}
