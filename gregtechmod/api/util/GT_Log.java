package gregtechmod.api.util;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class GT_Log {

   public static PrintStream out = System.out;
   public static PrintStream err = System.err;
   public static PrintStream ore = new GT_Log.LogBuffer();
   public static File mLogFile;
   public static File mOreDictLogFile;



   public static class LogBuffer extends PrintStream {

      public final List<String> mBufferedOreDictLog = new ArrayList();


      public LogBuffer() {
         super(new OutputStream() {
            public void write(int arg0) {}
         });
      }

      public void println(String aString) {
         this.mBufferedOreDictLog.add(aString);
      }
   }
}
