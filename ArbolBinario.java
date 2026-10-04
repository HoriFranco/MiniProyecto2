/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniproyecto2;

/**
 *
 * @author HoriAndréFrancoRuiz
 */
public class ArbolBinario {
    private NodoArbol raiz;
 
    public NodoArbol getRaiz() {
        return raiz;
    }
 
    public boolean estaVacio() {
        return raiz == null;
    }
 
    public void vaciar() {
        raiz = null;
    }
 
    // Metodo insertar 
    public boolean insertar(int valor) {
        if (buscarNivel(valor) != -1) {
            return false;
        }
        raiz = insertarRec(raiz, valor);
        return true;
    }
 
    private NodoArbol insertarRec(NodoArbol actual, int valor) {
        if (actual == null) {
            return new NodoArbol(valor);          // encontramos el lugar vacío
        }
        if (valor < actual.valor) {
            actual.izquierdo = insertarRec(actual.izquierdo, valor);
        } else {
            actual.derecho = insertarRec(actual.derecho, valor);
        }
        return actual;
    }
 
    //Metodo eliminar
 
    
    public boolean eliminar(int valor) {
        if (buscarNivel(valor) == -1) {
            return false;
        }
        raiz = eliminarRec(raiz, valor);
        return true;
    }
 
    private NodoArbol eliminarRec(NodoArbol actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor < actual.valor) {
            actual.izquierdo = eliminarRec(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = eliminarRec(actual.derecho, valor);
        } else {
           
            if (actual.izquierdo == null) {
                return actual.derecho;       // si era hoja, devuelve null
            }
            if (actual.derecho == null) {
                return actual.izquierdo;
            }

            NodoArbol menor = actual.derecho;
            while (menor.izquierdo != null) {
                menor = menor.izquierdo;
            }
            actual.valor = menor.valor;
            actual.derecho = eliminarRec(actual.derecho, menor.valor);
        }
        return actual;
    }
 
    //Metodo de buscar
 
    public int buscarNivel(int valor) {
        NodoArbol actual = raiz;
        int nivel = 0;
        while (actual != null) {
            if (valor == actual.valor) {
                return nivel;
            }
            if (valor < actual.valor) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
            nivel++;                         // bajamos un nivel
        }
        return -1;
    }
 
    //Metodo de los 3 recorridos
 
    //PreOrden
    public String preOrden() {
        StringBuilder sb = new StringBuilder();
        preOrdenRec(raiz, sb);
        return sb.toString().trim();
    }
 
    private void preOrdenRec(NodoArbol n, StringBuilder sb) {
        if (n == null) {
            return;
        }
        sb.append(n.valor).append(" ");
        preOrdenRec(n.izquierdo, sb);
        preOrdenRec(n.derecho, sb);
    }
 
    // InOrden
    public String inOrden() {
        StringBuilder sb = new StringBuilder();
        inOrdenRec(raiz, sb);
        return sb.toString().trim();
    }
 
    private void inOrdenRec(NodoArbol n, StringBuilder sb) {
        if (n == null) {
            return;
        }
        inOrdenRec(n.izquierdo, sb);
        sb.append(n.valor).append(" ");
        inOrdenRec(n.derecho, sb);
    }
    
    // PostOrden
    public String postOrden() {
        StringBuilder sb = new StringBuilder();
        postOrdenRec(raiz, sb);
        return sb.toString().trim();
    }
 
    private void postOrdenRec(NodoArbol n, StringBuilder sb) {
        if (n == null) {
            return;
        }
        postOrdenRec(n.izquierdo, sb);
        postOrdenRec(n.derecho, sb);
        sb.append(n.valor).append(" ");
    }
 
    //Metodo grafico
 
    public String dibujar() {
        StringBuilder stringbuiler = new StringBuilder();
        dibujarRec(raiz, 0, stringbuiler);
        return stringbuiler.toString();
    }
 
    private void dibujarRec(NodoArbol n, int nivel, StringBuilder sb) {
        if (n == null) {
            return;
        }
        dibujarRec(n.derecho, nivel + 1, sb);      // primero el lado derecho (arriba)
        for (int i = 0; i < nivel; i++) {
            sb.append("      ");                   // espacios según el nivel
        }
        sb.append(n.valor).append("\n");
        dibujarRec(n.izquierdo, nivel + 1, sb);    // luego el lado izquierdo (abajo)
    }
 
    // Calculo de altura
 
    public int altura() {
        return alturaRec(raiz);
    }
 
    private int alturaRec(NodoArbol n) {
        if (n == null) {
            return 0;
        }
        return 1 + Math.max(alturaRec(n.izquierdo), alturaRec(n.derecho));
    }
 
    public int contarNodos() {
        return contarRec(raiz);
    }
 
    private int contarRec(NodoArbol n) {
        if (n == null) {
            return 0;
        }
        return 1 + contarRec(n.izquierdo) + contarRec(n.derecho);
    }

}