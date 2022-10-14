package Digrafos;

import Grafos.*;

public class Nodo {
        
    private int data;
    private Nodo link;

    //Constructor de un solo parámetro {DATA}
    public Nodo(int data){
            this.data = data;
            this.link = null;
    }
    
    //Constructor vacio
    public Nodo(){
            this.data = 0;
            this.link = null;
    }

    public int getData() {
            return data;
    }

    public void setData(int data) {
            this.data = data;
    }

    public Nodo getLink() {
            return link;
    }

    public void setLink(Nodo link) {
            this.link = link;
    }
   
}
