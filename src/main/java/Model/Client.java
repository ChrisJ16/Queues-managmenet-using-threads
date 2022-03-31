package Model;

public class Client implements  Comparable<Client>{
    private int idCLient;
    private int arrivalTime; //simulation time when they are ready to enter the queue
    private int serviceTime; //time interval or duration needed to serve the client, when the client is in front of the queue

    public Client(int id, int arrvTime, int servTime)
    {
        this.idCLient = id;
        this.arrivalTime = arrvTime;
        this.serviceTime = servTime;
    }

    public String afisare()
    {
        return "Id = " + idCLient +"; Arrival Time: " + arrivalTime + "; Service time: " + serviceTime;
    }

    public void setServiceTime(int t)
    {
        this.serviceTime=t;
    }

    public int getServiceTime()
    {
        return this.serviceTime;
    }

    public int getArrivalTime()
    {
        return this.arrivalTime;
    }

    @Override
    public String toString(){
        return "["+idCLient+","+arrivalTime+","+serviceTime+"]";
    }

    @Override
    public int compareTo(Client o) {
        return this.arrivalTime - o.arrivalTime;
    }
}