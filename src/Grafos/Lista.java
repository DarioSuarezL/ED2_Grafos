package Grafos;

public class Lista {
    
    private Nodo Head;
    private int n; //longitud de la lista

    //constructor
    public Lista() {
        this.Head = null;
        this.n = 0;
    }

    //retorna 'true' si la lista está vacía
    public boolean isVacia() {
        return (Head == null);
    }

    //añadir nodo
    public void add(int newdata) {
        Nodo ant = null;
        Nodo p = Head;

        //recorre la lista hasta la posición del newdata
        while (p != null && newdata >= p.getData()) { //el mayor o igual evita la repetición
            ant = p;
            p = p.getLink();
        }

        Nodo newnodo;
        if (ant == null) { //entra acá si es el nodo es el menor de todos o está vacia la lista
            newnodo = new Nodo(newdata);
            newnodo.setLink(Head);
            Head = newnodo;
            n++;
        } else if (ant.getData() != newdata) { //entra acá si el nodo se encuentra entre dos nodos de la lista, para lista ordenada
            newnodo = new Nodo(newdata);
            ant.setLink(newnodo);
            newnodo.setLink(p);
            n++;
        }
    }

    //devolver tamaño
    public int length() {
        return n;
    }

    @Override //Para sobreescribir la funcion toString (que ya existe en la libreria standar de Java)
    //funcion que convierte a cadena la lista
    public String toString() {
        String st = "";
        Nodo p = Head;
        while (p != null) {
            st = st + p.getData() + "--> ";
            p = p.getLink();
        }
        return st;
    }

    //procedimiento para borrar un nodo segun la data que tenga
    public void delete(int data) {
        Nodo ant = null;
        Nodo p = Head;

        //recorre la lista hasta la posición del data
        while (p != null && p.getData() < data) {
            ant = p;
            p = p.getLink();
        }

        //data existe en la tabla
        if (p != null && p.getData() == data) {

            //data es el primera de la lista
            if (ant == null) {
                Head = Head.getLink();
            }else{
                ant.setLink(p.getLink());
            }
            p.setLink(null);
            n--;
        }
    }

    //devuelve true si el valor data existe en la lista
    public boolean exists(int data) {
        Nodo p = Head;     //nodo auxiliar para viajar entre nodos

        while (p != null && data > p.getData()) {     //iteración entre todos los nodos
            p = p.getLink();
        }

        return (p != null && p.getData() == data);   //retorna true si existe
    }

    //Devuelve el nodo de la posición
    public Nodo nodoIndex(int posicion) {
        int contador = 0;
        Nodo actual = Head;
        while (contador < posicion) {
            actual = actual.getLink();
            contador++;
        }
        return actual;
    }
    
    public int get(int k){  //Devuelve el k-ésimo elemento de la lista k=0, 1, ..., length()-1 
        Nodo p = Head;
        int i = 0;
        while (p != null){
            if (i == k)
                return p.getData();
            
            p = p.getLink();
            i++;
        }
        
        System.err.println("Lista.get: Fuera de rango");
        return -1;
    }
    
    
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%% PRACIICO %%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    //Elimina el nodo de la posición
    public void delNth(int i){
        delete(nodoIndex(i).getData());
        //Usamos funcion hechas previamente 
    }
    
    //Retorna el ultimo data de la lista
    public int getLast(){
        return nodoIndex(n-1).getData();
    }
    
    
    
    //Máscara funcion exist pero recursivo
    public boolean recursive_exist(int data){
        return r_exist(data, Head);
    }
    
    //Exist recursivo
    private boolean r_exist(int data, Nodo R){
        if (isVacia())
            return false;   
        if (R.getLink() == null || R.getData() >= data)
            return (R.getData() == data);
        return r_exist(data,R.getLink()); 
    }
    
    //add recursivo
    public void recursive_add(int newdata){
        r_add(newdata,Head,null);
    }
    
    private void r_add(int newdata, Nodo p,Nodo ant){
        if(isVacia()){
            Head = new Nodo(newdata);
            n++;
        }else{
            if (p != null && newdata >= p.getData()) { 
                r_add(newdata,p.getLink(),p);               
            }else{
                Nodo newnodo;
                if (ant == null) { 
                    newnodo = new Nodo(newdata);
                    newnodo.setLink(Head);
                    Head = newnodo;
                    n++;
                }else if(ant.getData() != newdata){ 
                    newnodo = new Nodo(newdata);
                    ant.setLink(newnodo);
                    newnodo.setLink(p);
                    n++;
                }
            }        
        }
    }
    
}
