package info.boaventura.bench;

import java.io.Closeable;
import java.io.IOException;

public final class IOFacility {

  public static void closeResource(Closeable closeable) {
    if (closeable == null) {
      return;
    }
    try {
      closeable.close();
    } catch (IOException e) {
      System.err.println("* Cannot close: " + e.getMessage());
    }
  }
}
