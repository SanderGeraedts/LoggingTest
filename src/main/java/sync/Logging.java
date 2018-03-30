package sync;

import domain.CarPackage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.List;

public class Logging {

    public static void main(String[] argv) throws FileNotFoundException, UnsupportedEncodingException {
        Long total = Long.parseLong("0");

        for (int x = 0; x < 100; x++) {

            //Slaat de tijd voor de test op
            Long start = System.currentTimeMillis();

            //Start de logging file
//            PrintWriter writer = new PrintWriter("logging.txt", "UTF-8");

            //Maakt 1.000.000x een nieuw package aan en schrijft het naar de logging.
            for (int i = 0; i < 1000000; i++) {
                Long current = System.currentTimeMillis();
                CarPackage car = new CarPackage(current);

//                writer.println(car);
            }

            //Slaat de tijd na de test op
            Long end = System.currentTimeMillis();

            //Berekent het tijdsverschil
            Long delta = end - start;

            //Voegt het tijdsverschil toe aan het totaal
            total += delta;

            //Print het tijdsverschil naar de console
            System.out.println("Time difference: " + delta);

            //Sluit de logging
//            writer.close();
        }

        //Berekent de gemiddelde tijdsduur
        double avg = total / 100;

        //Print de gemiddelde tijdsduur naar de console.
        System.out.println("======================================================");
        System.out.println("Time difference avg: " + avg);
        System.out.println("======================================================");
    }
}
