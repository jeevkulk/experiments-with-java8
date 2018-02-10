package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MemoryMappedReader {

    public void read(String filename) {
        Path path = Paths.get(filename);
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 5);
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            while (mappedByteBuffer.hasRemaining()) {
                byte b = mappedByteBuffer.get();
                //System.out.print((char)b);
            }
            System.out.println("----------------ByteBuffer Direct?----------------------" + mappedByteBuffer.isDirect());

            System.out.println("----------------ByteBuffer Direct?----------------------" + byteBuffer.isDirect());
            while (fileChannel.read(byteBuffer) > 1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    byte b = byteBuffer.get();
                    //System.out.print((char)b);
                }
                byteBuffer.clear();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
