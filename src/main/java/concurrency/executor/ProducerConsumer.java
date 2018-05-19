package concurrency.executor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.*;

public class ProducerConsumer {
    private BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        producerConsumer.process();
    }

    public void process() {
        Producer producer = new Producer(linkedBlockingQueue);
        Consumer consumer = new Consumer(linkedBlockingQueue);

        Future<?> producerFuture = executorService.submit(() -> {
            producer.produce();
        });
        Future<?> consumerFuture = executorService.submit(() -> {
            consumer.consume();
        });
        executorService.shutdown();
    }
}

class Producer {
    private BlockingQueue<String> blockingQueue;

    public Producer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void produce() {
        Path path = getFilePath("olympic-medals2012.csv");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder stringBuilder = null;
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            while (fileChannel.read(buffer) > 0) {
                stringBuilder = new StringBuilder();
                buffer.flip();
                while (buffer.hasRemaining()) {
                    char ch = (char) buffer.get();
                    stringBuilder.append(ch);
                }
                blockingQueue.put(stringBuilder.toString());
                buffer.clear();
            }
            blockingQueue.put("***EOF***");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Path getFilePath(String filename) {
        Path path = null;
        try {
            path = Paths.get(this.getClass().getClassLoader().getResource(filename).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }
}

class Consumer {
    private BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void consume() {
        File file = new File("E:\\technology_workspace\\data\\out\\olympic-medals2012_out.csv");
        try {
            if (file.exists())
                file.delete();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path path = Paths.get(file.toURI());
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.WRITE)) {
            boolean endOfFile = false;
            while (!endOfFile) {
                String data = blockingQueue.take();
                if (!"***EOF***".equals(data)) {
                    ByteBuffer byteBuffer = ByteBuffer.wrap(data.getBytes());
                    fileChannel.write(byteBuffer);
                } else {
                    endOfFile = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
