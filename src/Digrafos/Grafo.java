package Digrafos;

import java.util.LinkedList;

public class Grafo {
    private static final int MAXVERTEX = 49;
 
    private Lista V[];
    private int n;
    private boolean marca[];   
    
    public Grafo(){
        V = new Lista[MAXVERTEX+1];
        n = -1;
        marca = new boolean[MAXVERTEX+1];
    }
    
    public boolean isVerticeValido(int v){
        return (0 <= v && v <= n);
    }
    
    public void addVertice(){
        if(n == MAXVERTEX){
            System.err.println("Grafo.addVertice: Demasiado vértice (solo se permiten"+(MAXVERTEX +1)+")");
            return;
        }
        n++;
        V[n] = new Lista();  //Crear un nuevo vértice sin adyacentes (o sea con su Lista de adyacencia)
    }
    
    public int cantVertices(){
        return n+1;
    }
    
// PARA EL MARCADO DE VERTICES

    private void desmarcarTodos(){
        for (int i = 0; i <= n; i++) {
            marca[i] = false;  
        }
    }
    
    private void marcar(int u){
        if (isVerticeValido(u))
           marca[u] = true; 
    }
    
    private void desmarcar(int u){
        if (isVerticeValido(u))
            marca[u] = false; 
    }
    
    private boolean isMarcado(int u){   //Devuelve true sii el vertice u está marcado.
        return marca[u]; 
    }
    
// ----------------------------
    
    public void printListas(){  //Muestra las listas del Grafo.  Util para el programador de esta class
        if (cantVertices()==0)
            System.out.println("(Grafo Vacío)");
        else{
            for (int i = 0; i <= n; i++) {
                System.out.println("V["+i+"]-->"+V[i]);  
            }  
        }
    }
    
    private boolean isVerticeValido(int v, String metodo){
        boolean b = isVerticeValido(v);
        if (!b)
            System.err.println("Grafo."+metodo+": " + v + " no es un vértice del Grafo " + getIndicacion());

        return b;
    }
    
    private String getIndicacion(){  //Corrutina de boolean isVerticeValido(int, String)
        switch (cantVertices()){
            case 0  :   return "(el grafo no tiene vértices). ";
            case 1  :   return "(el 0 es el único vértice). ";
            case 2  :   return "(0 y 1 son los únicos vértices). ";
            default :   return "(los vértices van de 0 a " + (cantVertices()-1) + "). "; 
        }
    }    
    
    public void addArista(int u, int v){
        String metodo = "addArista";
        if(!isVerticeValido(u,metodo) || !isVerticeValido(v,metodo))
            return;
        
        V[u].add(v);
    }
    
    public void delArista(int u, int v){
        String metodo="delArista";
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo))
            return;     //No existe el vertice u o el vertice v.
        
        V[u].delete(v);    //Quitar v a la lista V[u]
    }
    
    public void dfs(int v){  //Recorrido Depth-First Search (en profundidad).
        if (!isVerticeValido(v, "dfs"))
            return;  //Validación. v no existe en el Grafo.
        
        desmarcarTodos();
        System.out.print("DFS:");
        dfs_r(v);
        System.out.println();
    }
    
    private void dfs_r(int v){  //mask-function de void dfs(int)
        System.out.print(" "+v);
        marcar(v);
        
        for (int i = 0; i < V[v].length(); i++) {   //for (cada w adyacente a v)
            int w = V[v].get(i);
            
            if (!isMarcado(w))
                dfs_r(w);
        }
    }
    
    public void bfs(int u){  //Recorrido Breadth-First Search (en anchura).
        if (!isVerticeValido(u, "bfs"))
            return;  //Validación. u no existe en el Grafo. 
       
        desmarcarTodos();
        LinkedList <Integer> cola = new LinkedList<>();  //"cola" = (vacía) = (empty)
        cola.add(u);     //Insertar u a la "cola" (u se inserta al final de la lista).
        marcar(u);
        
        System.out.print("BFS:");
        do{
            int v = cola.pop();         //Obtener el 1er elemento de la "cola".
            System.out.print(" "+v);
            
            for (int i = 0; i < V[v].length(); i++) {   //for (cada w adyacente a v)
                int w = V[v].get(i);
            
                if (!isMarcado(w)){
                    cola.add(w);
                    marcar(w);
                }    
            }
        }while (!cola.isEmpty());  
        
        System.out.println();
    }
    
}
