package info.boaventura.bench;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static info.boaventura.bench.IOFacility.closeResource;

public class CopierNewIOBufferRead implements Copier {

  String origin;
  String destiny;

  RandomAccessFile readFile;
  FileChannel readFileChannel;
  MappedByteBuffer bufferRead;
  private RandomAccessFile writeFile;
  private FileChannel writeFileChannel;
  private MappedByteBuffer bufferWrite;

  public CopierNewIOBufferRead(String origin, String destiny) {
    this.origin = origin;
    this.destiny = destiny;
  }

  public void initialize() throws IOException {
    // Reading
    readFile = new RandomAccessFile(origin, "r");
    readFileChannel = readFile.getChannel();
    bufferRead = readFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, readFileChannel.size());
    // Writing
    writeFile = new RandomAccessFile(destiny, "rw");
    writeFileChannel = writeFile.getChannel();
    bufferWrite = writeFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, readFileChannel.size());
  }

  public String getStrategy() {
    return "Cópia de NewIO com MappedByteBuffer";
  }

  public void read() throws IOException {
    // Read file into buffer
    bufferRead.load();
  }

  public void write() {
    // Write file into buffer
    bufferWrite.put(bufferRead);
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
