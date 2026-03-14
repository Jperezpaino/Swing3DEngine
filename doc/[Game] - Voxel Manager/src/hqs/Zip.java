package hqs;

 import
  java.io.BufferedInputStream;
 import
  java.io.BufferedOutputStream;
 import
  java.io.ByteArrayOutputStream;
 import
  java.io.IOException;
 import
  java.io.InputStream;
 import
  java.util.zip.Deflater;
 import
  java.util.zip.DeflaterOutputStream;
 import
  java.util.zip.Inflater;
 import
  java.util.zip.InflaterInputStream;

 public final class Zip
        extends AbstractCompressor {

  public Zip(final InputStream input) {
   super(input);
  }

  public final ByteArrayOutputStream compress() throws IOException {
   BufferedInputStream in = new BufferedInputStream(super.input);
   ByteArrayOutputStream bos = new ByteArrayOutputStream();
   DeflaterOutputStream out = new DeflaterOutputStream(bos, new Deflater(), BUF_SIZE);
   byte[] buf = new byte[BUF_SIZE];

   for (int count = in.read(buf); count != -1; count = in.read(buf)) {
    out.write(buf, 0, count);
   }
   in.close();
   out.flush();
   out.close();
   return bos;
  }


    public ByteArrayOutputStream uncompress() throws IOException
    {
        return uncompress(BUF_SIZE);
    }


    public ByteArrayOutputStream uncompress(int expectedLength) throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BufferedOutputStream out = new BufferedOutputStream(bos, expectedLength);
        InflaterInputStream in = new InflaterInputStream(input, new Inflater(), expectedLength);
        byte[] buf = new byte[expectedLength];

        for (int count = in.read(buf); count != -1; count = in.read(buf))
        {
            out.write(buf, 0, count);
        }
        in.close();
        out.flush();
        out.close();
        return bos;
    }

}