package Concurrent.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private Selector selector;
    private ExecutorService tp = Executors.newCachedThreadPool();
    public static Map<Socket, Long> timeStat = new HashMap<Socket, Long>(10240);

    public void startServer() throws IOException {
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(), 8000);
        ssc.socket().bind(isa);

        SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

        for (;;) {
            selector.select();
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            long e = 0;
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                iterator.remove();

                if(sk.isAcceptable()) {
                    doAccept(sk);
                }
                else if(sk.isValid() && sk.isReadable()) {
                    if(!timeStat.containsKey(((SocketChannel) sk.channel()).socket()))
                        timeStat.put(((SocketChannel) sk.channel()).socket(), System.currentTimeMillis());
                    doRead(sk);
                }
                else if (sk.isValid() && sk.isWritable()) {
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b = timeStat.remove(((SocketChannel) sk.channel()).socket());
                    System.out.println("spend:" + (e-b) + "ms");
                }
            }
        }
    }

    private void doAccept(SelectionKey sk) {
        ServerSocketChannel server = (ServerSocketChannel) sk.channel();
        SocketChannel clientChannel;
        try {
            clientChannel = server.accept();
            clientChannel.configureBlocking(false);

            //Register this channel for reading
            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
            // Allocate an EchoClient instance and attach it to this selection key.
            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);
            InetAddress clientAddress = clientChannel.socket().getInetAddress();
            System.out.println("Accepted connection from " + clientAddress.getHostAddress() + ".");
        } catch (IOException e) {
            System.out.println("Failed to accept new client");
            e.printStackTrace();
        }
    }

    private void doRead(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;

        try {
            len = channel.read(bb);
            if(len < 0) {
                disconnect(sk);
                return;
            }
        } catch (IOException e) {
            System.out.println("Failed to read from client");
            e.printStackTrace();
            disconnect(sk);
            return;
        }

        bb.flip();
        tp.execute(new HandleMsg(sk,bb));
    }

    private void doWrite(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        EchoClient echoClient = (EchoClient) sk.attachment();
        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();

        ByteBuffer bb = outq.getLast();
        try {
            int len = channel.write(bb);
            if(len == -1) {
                disconnect(sk);
                return;
            }
            if(bb.remaining() == 0) {
                //The buffer was completely written, remove it
                outq.removeLast();
            }
        } catch (IOException e) {
            System.out.println("Failed to write to client");
            e.printStackTrace();
            disconnect(sk);
        }

        if(outq.size() == 0 ) {
            sk.interestOps(SelectionKey.OP_READ);
        }
    }

    private void disconnect(SelectionKey sk) {
        try {
            sk.selector().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class EchoClient {
        private LinkedList<ByteBuffer> outq;

        public EchoClient() {
            outq = new LinkedList<ByteBuffer>();
        }

        public LinkedList<ByteBuffer> getOutputQueue() {
            return outq;
        }

        public void enqueue(ByteBuffer bb) {
            outq.addFirst(bb);
        }
    }

    class HandleMsg implements Runnable {
        SelectionKey sk;
        ByteBuffer bb;

        public HandleMsg(SelectionKey sk, ByteBuffer bb) {
            this.sk = sk;
            this.bb = bb;
        }

        public void run() {
            EchoClient echoClient = (EchoClient) sk.attachment();
            echoClient.enqueue(bb);
            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            //强迫select立即返回
            selector.wakeup();

        }
    }
}


