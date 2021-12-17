package org.example.Model;

public class Client implements Comparable<Client>{

    private int ID;
    private int arrivalTime;
    private int serviceTime;
    private int waitingTime;

    public Client(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.waitingTime = 0;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime() {
        if (this.serviceTime > 0) {
            this.serviceTime--;
        }
    }

    @Override
    public String toString() {
        return "(" + ID +"," + arrivalTime + "," + serviceTime + ')';
    }

    @Override
    public int compareTo(Client o) {
       return this.arrivalTime-o.arrivalTime;
    }
}
