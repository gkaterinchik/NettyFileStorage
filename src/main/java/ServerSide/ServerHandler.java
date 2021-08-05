package ServerSide;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("пришло сообщение");
        if (msg instanceof String) {
            System.out.println(msg);
        } else if (msg instanceof Request) {
            System.out.println("Сервер получил : " + ((Request) msg).getFileName());
        } else {
            System.out.println("Сервер получил не Request");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }


}
