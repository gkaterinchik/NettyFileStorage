package ClientSide;


import ServerSide.MarshallingCodeCFactory;
import io.netty.channel.ChannelInitializer;
        import io.netty.channel.ChannelPipeline;
        import io.netty.channel.socket.SocketChannel;
        import io.netty.handler.codec.DelimiterBasedFrameDecoder;
        import io.netty.handler.codec.Delimiters;
        import io.netty.handler.codec.string.StringDecoder;
        import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by Administrator on 2017/3/11.
 */
public class ClientChannelInitializer extends  ChannelInitializer<SocketChannel> {
@Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();


        //pipeline.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
        pipeline.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
        pipeline.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
        // client logic
        pipeline.addLast("handler", new ClientHandler());
    }
}