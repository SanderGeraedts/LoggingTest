package rabbitmq;

import com.rabbitmq.client.*;
import domain.CarPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeoutException;

public class Monitoring {
    private final static String EXCHANGE_NAME = "CAR_LOCATION_SENT";

    public static void main(String[] argv)
            throws IOException,
            InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //creates a new consumer to handle the delivery of the message
        Consumer consumer = new DefaultConsumer(channel) {

            //Triggers when a message there's a message in the queue
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {

                //converts the serialized byte array to a string
                CarPackage message = (CarPackage) Helper.byteArrayToObject(body);
                Long current = System.currentTimeMillis();

                Long delta = current - message.getTime();

                PrintWriter writer = new PrintWriter("logging.txt", "UTF-8");
            }
        };

        //Assigns the consumer to the correct queue
        channel.basicConsume(queueName, true, consumer);
    }
}
