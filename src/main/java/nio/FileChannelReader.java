package nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.*;
import java.nio.channels.FileChannel;

public class FileChannelReader {

    public static void main(String[] args) {
        FileChannelReader fileChannelReader = new FileChannelReader();
        fileChannelReader.readFile();
    }

    private void readFile() {

        try(FileInputStream fileInputStream = new FileInputStream("E:\\technology_workspace\\data\\surnames.csv")) {

            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            while (fileChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();

                while (byteBuffer.hasRemaining()) {
                    byte b = byteBuffer.get();
                    System.out.print((char)b);
                }
                byteBuffer.clear();
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
