package Logic;

import Model.*;
import GUI.*;

import java.io.*;
import java.util.*;

public class ManagerSim implements  Runnable{
    private int nrOfClients;
    private int nrOfServers;
    private int simTime;
    private int ArrivalTimeMin;
    private int ArrivalTimeMax;
    private int ServiceTimeMin;
    private int ServiceTimeMax;

    private double avgWaitTime = 0.0;
    private double avgServTime = 0.0;

    private View view;

    private String currentStr; //string-ul ce se formeaza la fiecare pas
    private String bigStr; //string-ul final, cel mare

    private ArrayList<Client> clientList = new ArrayList<>();
    private Planificator planific;

    public ManagerSim(View view, int simTime, int nrOfServers, int nrOfClients, int arrivalTimeMin, int arrivalTimeMax, int serviceTimeMin, int serviceTimeMax){
        this.view = view;
        this.nrOfClients = nrOfClients;
        this.nrOfServers = nrOfServers;
        this.simTime = simTime;
        this.ArrivalTimeMin = arrivalTimeMin;
        this.ArrivalTimeMax = arrivalTimeMax;
        this.ServiceTimeMin = serviceTimeMin;
        this.ServiceTimeMax = serviceTimeMax;

        this.planific = new Planificator(nrOfServers, nrOfClients);
        GenerateNClients();
    }

    public void GenerateNClients(){
        int stime = 0;
        int wtime = 0;
        for(int i=0; i<nrOfClients; i++){//?
            Random r = new Random();
            int arrivalTime = r.nextInt(ArrivalTimeMax - ArrivalTimeMin) + ArrivalTimeMin;
            int serviceTime = r.nextInt(ServiceTimeMax - ServiceTimeMin) + ServiceTimeMin;
            Client c = new Client(i, arrivalTime, serviceTime);
            stime += serviceTime;
            wtime += serviceTime;
            clientList.add(c);
        }
        avgWaitTime = wtime/(nrOfClients*1.0);
        avgServTime = stime/(nrOfServers*1.0);
        clientList.sort(Client::compareTo);
    }


    public String getStatus(){ //pt a vizualiza starea cozilor, precum si cei care sunt inca in coada principala
        String str = new String();
        str = str + "Clienti in coada:\n";
        for(Client t : clientList)
            str = str + " " + t.toString();
        str = str + "\n";
        int kcont=1;
        for(Server s : planific.getServere()) {
            if(s.toString().isEmpty())
                str = str + "Coada " + kcont + ":  goala!\n";
            else
                str = str + "Coada " + kcont + ": " + s.toString() + "\n";
            kcont++;
        }
        str = str + "************************************\n";
        return str;
    }

    public boolean checkQueues(){ //verificam starea cozilor
        boolean ok = false;
        for(Server q : planific.getServere()) //verificam daca toate cozile sunt goale
            if(q.getSizeOfQueue() != 0)
                ok = true;
        if(!clientList.isEmpty()) //verificam daca coada principala este goala
            ok = true;
        return ok;
    }

    public void printToFile(String string){
        try {//cream fisierul
            File myObj = new File("simulare.txt");
            if (myObj.createNewFile()) {
                System.out.println("Fisier creat: " + myObj.getName());
            } else {
                System.out.println("Fisierul exista deja!");
            }
        } catch (IOException e) {
            System.out.println("A aparut o eroare!");
            e.printStackTrace();
        }

        try { //scriem in fisierul creat deja
            FileWriter myWriter = new FileWriter("simulare.txt");
            string = string + "Timp de asteptare mediu " + avgWaitTime +
                    "\nTimp de servire mediu " + avgServTime + "\n";
            myWriter.write(string);
            myWriter.close();
            System.out.println("Success!");
        } catch (IOException e) {
            System.out.println("A aparut o eroare!");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        bigStr = new String();
        int nowTimeSim = 0; //timpul curent din simulare
        planific.startThread(); //pornim thread-urile
        while (nowTimeSim < simTime && checkQueues()){
            nowTimeSim++;
            String str = new String("*********************\nTime: " + nowTimeSim + "\n");
            ArrayList<Client> aux = new ArrayList<>();
            for(Client t : clientList){
                if(t.getArrivalTime() == nowTimeSim)  //daca timpul cand a ajuns clientul este egal cu timpul simularii => il distribuim la o coada
                    planific.dispatchClient(t);
                else aux.add(t);
            }
            clientList = aux;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int numCurrentClients = 0; ///verificam cati clienti sunt in fiecare coada
            for(Server s : planific.getServere()){
                numCurrentClients = numCurrentClients + s.getSizeOfQueue();
            }

            currentStr = numCurrentClients + "\n" + str + getStatus();

            bigStr = bigStr + currentStr;

            view.setAreaText(bigStr);
            System.out.println(currentStr);
        }
        printToFile(bigStr);

        bigStr = bigStr + "\nTimp servire mediu: " + avgServTime + "\nTimp de aseptare mediu: " + avgWaitTime + "\n";
        view.setAreaText(bigStr);
    }
}
