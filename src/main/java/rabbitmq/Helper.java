package rabbitmq;

import java.io.*;

public class Helper {
    public static byte[] objectToByteArray(Object object) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput output = null;

        byte[] bytes = null;

        try {
            output = new ObjectOutputStream(bos);
            output.writeObject(object);
            output.flush();
            bytes = bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

    public static Object byteArrayToObject(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;

        Object object = null;

        try {
            in = new ObjectInputStream(bis);
            object = in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null ) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return object;
    }
}
