package ClientSide;


import ServerSide.Request;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.File;
import java.io.FileInputStream;

public class Client {
    private int port;
    private String address;

    public Client(int port, String address) {
        this.port = port;
        this.address = address;
    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientChannelInitializer());


        try {
            ChannelFuture future = bootstrap.connect(address, port).sync();
            System.out.println("Клиент подключился к ");

            File file = new File("Counter-Strike 1.6 All-Servers.7z");
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[fis.available()];

            fis.read(buffer, 0, fis.available());
            Request request = new Request();
            request.setId("1");
            request.setFileName(file.getName());
            request.setFileSize(file.length());


            future.channel().writeAndFlush(request);

            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        Client client = new Client(9000, "127.0.0.1");
        client.start();
    }
}