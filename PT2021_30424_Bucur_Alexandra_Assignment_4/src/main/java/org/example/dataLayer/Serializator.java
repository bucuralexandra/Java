package org.example.dataLayer;

import org.example.businessLayer.DeliveryService;

import java.io.*;

/**
 * @author Alexandra
 * @since 12/05/2021
 */
public class Serializator {

    /**
     * method that will serialize an object in a specific file
     *
     * @param deliveryService object to be serialized
     */
    public static void serialize(DeliveryService deliveryService) {

        try {
            FileOutputStream file = new FileOutputStream("ser.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(deliveryService);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method that will serialize an object from a specific file
     * deliveryService object to be serialized
     */
    public static DeliveryService deserialize() {
        try {
            FileInputStream file = new FileInputStream("ser.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            DeliveryService deliveryService = (DeliveryService) in.readObject(); // Method for deserialization of object // Method for deserialization of object
            in.close();
            file.close();
            return deliveryService;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
