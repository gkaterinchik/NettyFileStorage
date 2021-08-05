package ClientSide;


import ServerSide.Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLOutput;

/**
 * Created by Administrator on 2017/3/11.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof String){
            System.out.println(msg);
        }else{
            Request m = (Request)msg;
            System.out.println("client: "+m.getCommand()+" имя файла = "+ m.getFileName());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("ChannelActive");
        File file = new File("Counter-Strike 1.6 All-Servers.7z");
        FileInputStream fis= new FileInputStream(file);
        byte[] buffer = new byte[fis.available()];

        fis.read(buffer, 0, fis.available());
        Request request = new Request();
        System.out.println("Create request");
        request.setId("1");
        request.setFileName(file.getName());
        request.setFileSize(file.length());
        request.setContent(buffer);

        ctx.channel().writeAndFlush(request);


        System.out.println("ctx.channel().writeAndFlush(request);");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client is close");
    }
}