package nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWriter {

    public static void main(String[] args) {
        FileChannelWriter fileChannelWriter = new FileChannelWriter();
        fileChannelWriter.writeFile();
    }

    private void writeFile() {
        File file = new File("E:\\technology_workspace\\data\\test.csv");
        try (FileChannel fileChannel = new FileOutputStream(file).getChannel()) {

            String data = "This data is wriiten by FileChannelWriter!";
            ByteBuffer byteBuffer = ByteBuffer.wrap(data.getBytes());
            fileChannel.write(byteBuffer);

            System.out.println("Data has been written to "+file.getAbsolutePath());

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

    }
}
