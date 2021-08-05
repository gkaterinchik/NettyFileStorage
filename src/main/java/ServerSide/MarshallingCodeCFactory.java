package ServerSide;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

public final class MarshallingCodeCFactory {

    public static MarshallingDecoder buildMarshallingDecoder() {
        System.out.println("Creat Marshaling Decoder");
// Сначала получите объект экземпляра Marshalling с помощью профессионального метода класса инструмента Marshalling. Параметр серийной идентификации создает объект фабрики сериализации Java.
        final MarshallerFactory marshallerFactory = Marshalling
                .getProvidedMarshallerFactory("serial");
// Создал объект MarshallingConfiguration и настроил номер версии как 5
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
// Создаем провайдера на основе marshallerFactory и конфигурации
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(
                marshallerFactory, configuration);
// Создаем объект Netty MarshallingDecoder, два параметра - это поставщик и максимальная длина одного сообщения после сериализации
        MarshallingDecoder decoder = new MarshallingDecoder(provider,
                1024 * 1024 * 1024);
        return decoder;
    }

    /**
     * Создайте кодировщик Jboss Marshalling MarshallingEncoder
     *
     * @return MarshallingEncoder
     */
    public static MarshallingEncoder buildMarshallingEncoder() {
        System.out.println("Creat Marshaling Encoder");
        final MarshallerFactory marshallerFactory = Marshalling
                .getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(
                marshallerFactory, configuration);
// Создаем объект Netty MarshallingEncoder. MarshallingEncoder используется для сериализации объекта POJO интерфейса сериализации в двоичный массив
        MarshallingEncoder encoder = new MarshallingEncoder(provider);

        return encoder;
    }
}