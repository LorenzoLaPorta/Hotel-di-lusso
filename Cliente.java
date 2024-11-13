public class Cliente
{
    String nome;
    int giornoIngresso;
   
    Cliente(String nome, int giornoIngresso){
        this.nome = nome;
        this.giornoIngresso = giornoIngresso;
    }
   
    @Override
    public String toString(){
        return "nome: " + this.nome + ", giorno di ingresso: " + this.giornoIngresso;
    }


    public static int trovaIndiceVuoto(Cliente[] camereSingole){
        for (int i = 0; i < camereSingole.length; i++){
            if (camereSingole[i] == null){
                return i;
            }
        }
        return -1;
    }


    public static boolean checkIn(Cliente[] camereSingole, Cliente[] clienti){
        int indiceVuoto = trovaIndiceVuoto(camereSingole);
        if (indiceVuoto == -1){
            return false;
        }
        else{
            camereSingole[indiceVuoto] = clienti[indiceVuoto];
            return true;
        }
    }


    public static int riecrcaCliente(Cliente[] camereSingole, String clienteCheckout){
        for (int i = 0; i < camereSingole.length; i++){
            if (camereSingole[i].nome.equals(clienteCheckout)){
                return i;
            }
        }
        return -1;
    }


    public static int pagamento(Cliente[] camereSingole, String clienteCheckout, int giornoAttuale){
        if (riecrcaCliente(camereSingole, clienteCheckout) != -1){
            return (giornoAttuale - camereSingole[riecrcaCliente(camereSingole, clienteCheckout)].giornoIngresso);
        }
        return -1;
    }


    public static void checkOut(Cliente[] camereSingole, String clienteCheckout){
        camereSingole[riecrcaCliente(camereSingole, clienteCheckout)] = null;
    }


    public static void main(String[] args) {  
        final int N = 5; //creo la costante


        //creo i due array rappresentanti le camere
        Cliente[] camereSingole = new Cliente [N];
        //Cliente[] cameredoppie = new Cliente [N];


        //creo l'array contenente i clienti
        Cliente c1 = new Cliente("Marco",1);
        Cliente c2 = new Cliente("claudia",3);
        Cliente c3 = new Cliente("Luca",7);
        Cliente c4 = new Cliente("Antonella",5);
        Cliente c5 = new Cliente("Daniele",2);
        Cliente clienti[] = {c1, c2, c3, c4, c5};


        //creo la variabile "clienteCheckout" per inserire il cliente che deve uscire e pagare
        String clienteCheckout = "Luca";


        //creo la variabile "giornoAttuale" per definire il numero di giorno attuale
        int giornoAttuale = 23;




    }
}

