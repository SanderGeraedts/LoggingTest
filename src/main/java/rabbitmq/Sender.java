package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import domain.CarPackage;

import java.io.*;
import java.util.concurrent.TimeoutException;

public class Sender {
    private final static String EXCHANGE_NAME = "CAR_LOCATION_SENT";

    public static void main(String[] argv) throws IOException, TimeoutException {
        //Creates the factory
        ConnectionFactory factory = new ConnectionFactory();

        //Sets the host of the broker to local host. This could also be an IP or HelloWorld.server name.
        factory.setHost("localhost");

        //Sets up a new connection
        Connection connection = factory.newConnection();

        //Creates the channel
        Channel channel = connection.createChannel();

        //Declares a new queue in the channel with the name "hello"
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");


        for (int i = 0; i < 100000; i++) {
            Long current = System.currentTimeMillis();
            CarPackage car = new CarPackage(current);

            //publishes the message string to the queue "hello"
            channel.basicPublish(EXCHANGE_NAME, "", null, Helper.objectToByteArray(car));

            System.out.println(" [x] Sent '" + car + "'");
        }

        //Closes the channel and connection
        channel.close();
        connection.close();
    }
}
