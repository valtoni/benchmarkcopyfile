package info.boaventura.bench;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static info.boaventura.bench.IOFacility.closeResource;

public class CopierNewIO implements Copier {

  String origin;
  String destiny;

  File fileToCopy;

  RandomAccessFile readFile;
  FileChannel readFileChannel;
  private RandomAccessFile writeFile;
  private FileChannel writeFileChannel;
  private MappedByteBuffer bufferWrite;

  public CopierNewIO(String origin, String destiny) {
    this.origin = origin;
    this.destiny = destiny;
    this.fileToCopy = new File(origin);
  }

  public void initialize() throws IOException {
    // Reading
    readFile = new RandomAccessFile(origin, "r");
    readFileChannel = readFile.getChannel();
    // Writing
    writeFile = new RandomAccessFile(destiny, "rw");
    writeFileChannel = writeFile.getChannel();
  }

  public String getStrategy() {
    return "Cópia de NewIO";
  }

  public void read() throws IOException {
    // Read file into buffer
  }

  public void write() throws IOException {
    // Write file into buffer
    readFileChannel.transferTo(0, fileToCopy.length(), writeFileChannel);
  }

  public void close() {
    closeResource(writeFileChannel);
    closeResource(writeFile);
    closeResource(readFileChannel);
    closeResource(readFile);
  }

  @Override
  public void cleanup() {
    new File(destiny).delete();
  }

}
