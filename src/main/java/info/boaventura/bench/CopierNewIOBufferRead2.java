package info.boaventura.bench;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static info.boaventura.bench.IOFacility.closeResource;

public class CopierNewIOBufferRead2 implements Copier {

  String origin;
  String destiny;

  private static final int BUFFER = 4096*16;

  FileChannel in;
  FileChannel out;
  long size;
  ByteBuffer buffer;

  public CopierNewIOBufferRead2(String origin, String destiny) {
    this.origin = origin;
    this.destiny = destiny;
  }


  public void initialize() throws IOException {
    in = new FileInputStream(origin).getChannel();
    out = new FileOutputStream(destiny).getChannel();
    size = in.size();

  }

  public String getStrategy() {
    return "Cópia de NewIO com MappedByteBuffer Bufferizado (BUFFER: " + BUFFER + ")";
  }

  public void read() throws IOException {
    // Read file into buffer
    buffer = ByteBuffer.allocateDirect(BUFFER);
  }

  public void write() throws IOException {
    while (in.read(buffer) != -1) {
      buffer.flip();
      while(buffer.hasRemaining()){
        out.write(buffer);
      }
      buffer.clear();
    }
  }

  public void close() {
    closeResource(in);
    closeResource(out);
  }

  @Override
  public void cleanup() {
    new File(destiny).delete();
  }

}
