package org.example.Controller;

import javafx.scene.control.TextArea;
import org.example.Model.Client;
import org.example.Model.Simulation.Scheduler;
import org.example.Model.Simulation.Server;
import org.example.Model.StrategyManager.ConcreteStrategyQueue;
import org.example.Model.StrategyManager.ConcreteStrategyTime;
import org.example.Model.StrategyManager.SelectionPolicy;
import org.example.Model.TimeBounds;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulationManager implements Runnable  {
    private final int simulationTime;
    private final TimeBounds serviceTime;
    private final TimeBounds arrivalTime;
    private final int nrQueues;
    private final int nrClients;
    private final Scheduler scheduler;
    private final List<Client> waitingClients;
    private final static Random rnd = new Random();
    private final AtomicBoolean run = new AtomicBoolean(true);
    private double avgServiceTime = 0;
    private double avgWaitingTime = 0;
    private final TextArea outputUI;
    private FileWriter outputFile;
    private int peakTime = 0;
    private int peakTimeClients = 0;

    public SimulationManager(int simulationTime, TimeBounds serviceTime , TimeBounds arrivalTime, int nrQueues, int nrClients, SelectionPolicy policy, TextArea resultTextArea) {
        this.simulationTime = simulationTime;
        this.serviceTime = serviceTime;
        this.arrivalTime = arrivalTime;
        this.nrQueues = nrQueues;
        this.nrClients = nrClients;
        this.scheduler = new Scheduler(nrQueues,nrClients, policy);
        try {
            outputFile = new FileWriter("simulation.txt");
        }catch (IOException e){
            System.out.println("Something wrong with the file");
        }
        this.outputUI = resultTextArea;
        this.outputUI.setText("");
        waitingClients = new ArrayList<>();
        createListOfWaitingClients();
    }

    private void createListOfWaitingClients(){
        for(int i = 0; i < this.nrClients; i++){
            int serviceT = rnd.nextInt(this.serviceTime.getMaxTime() - this.serviceTime.getMinTime()) + this.serviceTime.getMinTime() + 1;
            int arrivalT = rnd.nextInt(this.arrivalTime.getMaxTime() - this.arrivalTime.getMinTime()) + this.arrivalTime.getMinTime() + 1;
            this.waitingClients.add(new Client((i+1),arrivalT,serviceT));
        }
        Collections.sort(this.waitingClients);
        for(int i = 0; i < this.nrClients; i++) {
            waitingClients.get(i).setID(i+1);
        }
        avgServiceTime = computeAverageServiceTime();
    }

    @Override
    public void run() {
        int currentTime = 0;
        ArrayList<Client> toBeRemoved = new ArrayList<>();

        while (run.get()){

            for( Client client: this.waitingClients){
                if(client.getArrivalTime() == currentTime) {
                    this.avgWaitingTime += computeTotalWaitingTime(client);
                    scheduler.dispatchTask(client);
                    toBeRemoved.add(client);
                }
            }
            computePeakTime(currentTime);
            this.waitingClients.removeAll(toBeRemoved);
            //update UI
            updateUI(currentTime);
            writeInFile(currentTime);

            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){

            }
            currentTime++;
            if( currentTime >= this.simulationTime || ( waitingClients.size()==0 && scheduler.emptyServiceQueues() == true)) {
                run.set(false);
            }
            decrementServiceTime();
        }
        finalAvgWaitingTime();
        finalWriteInFile();
        finalWriteInUI();
    }

    private void finalAvgWaitingTime() {
        if(waitingClients.size()== 0)
            this.avgWaitingTime = this.avgWaitingTime / nrClients;
        else {
            this.avgWaitingTime = this.avgWaitingTime / (nrClients-waitingClients.size());
        }
    }

    private void finalWriteInUI() {
        StringBuilder s = new StringBuilder("");
        s.append("Average service time: ").append(new DecimalFormat("##.##").format(avgServiceTime)).append('\n');
        s.append("Peak time: " + this.peakTime +"\n");
        s.append("Average waiting time: ").append(new DecimalFormat("##.##").format(avgWaitingTime)).append('\n');
        outputUI.appendText(s.toString());
    }

    private void finalWriteInFile() {
        try {
            outputFile.write("Average service time: " + new DecimalFormat("##.##").format(avgServiceTime) + "\n");
            outputFile.write("Peak time: " + this.peakTime +"\n");
            outputFile.write("Average waiting time: " + new DecimalFormat("##.##").format(avgWaitingTime) + "\n");
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateUI(int currentTime){

        StringBuilder s = new StringBuilder();
        s.append("Time= ").append(currentTime).append('\n');
        s.append("Waiting clients: ");
        if(waitingClients.size() != 0) {
            s.append(this.waitingClients.toString()).append('\n');
        }else s.append('\n');
        if(scheduler.toString() != null) {
            s.append(scheduler.toString()).append('\n');
        }
        outputUI.appendText(s.toString());
    }

    private void writeInFile(int currentTime){
        try {
            outputFile.write("Time= " + currentTime+ "\n");
            outputFile.write("Waiting clients: ");
            outputFile.write(this.waitingClients.toString() + "\n");
            outputFile.write(scheduler.toString() + "\n");

        } catch (IOException e){
            System.out.println("Problems with the file");
        }
    }

    private double computeAverageServiceTime(){
        double avg = 0;
        for(Client client: waitingClients){
            avg += client.getServiceTime();
        }
        avg /= waitingClients.size();
        return avg;
    }

    private void computePeakTime(int currentTime){
        int numberClients = scheduler.allClients();
        if(numberClients > this.peakTimeClients){
            this.peakTimeClients = numberClients;
            this.peakTime = currentTime;
        }
    }

    private void decrementServiceTime(){

        for(Server server: this.scheduler.getQueues()){
            if( server != null) {
                if (server.getClients().peek() != null) {
                    server.getClients().peek().setServiceTime();
                }
                //server.getWaitingPeriod().decrementAndGet();
            }
        }
    }

    private double computeTotalWaitingTime(Client client){
        double avg = 0;
       if(scheduler.getStrategy().equals(SelectionPolicy.SHORTEST_QUEUE)){
           int whichQueue = ConcreteStrategyQueue.strategyQueue(scheduler.getQueues(),client);
           if(whichQueue != -1){
               for (Client x: scheduler.getQueues().get(whichQueue).getClients()){
                   avg += x.getServiceTime();
               }
           }
        }else {
           int whichQueue = ConcreteStrategyTime.strategyTime(scheduler.getQueues(),client);
           if(whichQueue != -1){
               for (Client x: scheduler.getQueues().get(whichQueue).getClients()){
                   avg += x.getServiceTime();
               }
           }
       }
        return avg;
    }
}
