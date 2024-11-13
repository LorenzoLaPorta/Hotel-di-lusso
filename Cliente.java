import java.util.Scanner;


public class Cliente
{


    //creo gli attributi necessari per ogni Cliente
    String nome;
    int giornoIngresso;
   
    /**
     * creo il costruttore
     * 
     * @param gli attributi nome e giornoIngresso
     */
    Cliente(String nome, int giornoIngresso){
        this.nome = nome;
        this.giornoIngresso = giornoIngresso;
    }
   
    /**
     * faccio l'override del metodo toString per fargli stampare nome del Cliente e giorno di ingresso
     * 
     * @return il nome e giorno d'ingresso del cliente
     */
    @Override
    public String toString(){
        return "nome del Cliente: " + this.nome + ", giorno di ingresso: " + this.giornoIngresso;
    }

    /**
     * creo il metodo trovaIndiceVuoto, che restituisce il primo indice senza oggetti dell'array camereSingole
     * 
     * @param l'array camereSingole
     * @return l'indice della camera vuota (-1 se non esistono camere vuote)
     */
    public static int trovaIndiceVuoto(Cliente[] camereSingole){
        for (int i = 0; i < camereSingole.length; i++){
            if (camereSingole[i] == null){
                return i;
            }
        }
        return -1;
    }

    /**
     * creo il metodo checkIn, che permette l'inserimento di nuovi clienti nelle stanze e restituisce false se l'array e' pieno o true se non e' pieno
     * 
     * @param l'array camereSingole e l'oggetto c1
     * @return l'indice della camera che viene riempita (-1 se non viene riempita nessuna camera)
     */
    public static boolean checkIn(Cliente[] camereSingole, Cliente c1){
        int indiceVuoto = trovaIndiceVuoto(camereSingole);
        if (indiceVuoto == -1){
            return false;
        }
        else{
            camereSingole[indiceVuoto] = c1;
            return true;
        }
    }

    /**
     * creo il metodo ricerca Cliente che cerca e restituisce l'indice di un Cliente inserito
     * 
     * @param l'array camereSingole e la variabile ClienteCheckout
     * @return l'indice del cliente cercato (-1 se non esiste)
     */
    public static int riecrcaCliente(Cliente[] camereSingole, String ClienteCheckout){
        for (int i = 0; i < camereSingole.length; i++){
            if (camereSingole[i] != null){
                if (camereSingole[i].nome.equals(ClienteCheckout)){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * creo il metodo pagamento che calcola il prezzo che deve pagare il Cliente inserito
     * 
     * @param l'array camereSingole, la variabile ClienteCheckout, la variabile giornoAttuale e la variabile prezzoANotte
     * @return il prezzo che deve pagare il cliente (-1 se non viene trovato dalla funzione ricercaCliente)
     */
    public static int pagamento(Cliente[] camereSingole, String ClienteCheckout, int giornoAttuale, int prezzoANotte){
        int clienteRicercato = riecrcaCliente(camereSingole, ClienteCheckout);
        if (clienteRicercato != -1){
            return ((giornoAttuale - camereSingole[riecrcaCliente(camereSingole, ClienteCheckout)].giornoIngresso) * prezzoANotte);
        }
        return -1;
    }

    /**
     * creo il metodo checkOut che elimina il Cliente inserito dall'array camereSingole
     * 
     * @param l'array camereSingole e la variabile ClienteCheckout
     */
    public static void checkOut(Cliente[] camereSingole, String ClienteCheckout){
        int clienteRicercato = riecrcaCliente(camereSingole, ClienteCheckout);
        if (clienteRicercato != -1){
            camereSingole[riecrcaCliente(camereSingole, ClienteCheckout)] = null;
        }
    }

    /**
     * creo il metodo stampaCamereLibere, che stampa tutte le camere libere
     * 
     * @param l'array camereSingole
     */
    public static void stampaCamereLibere(Cliente[] camereSingole){
        System.out.println("CAMERE LIBERE:");
        for (int i = 0; i < camereSingole.length; i++){
            if (camereSingole[i] == null){
                System.out.println("- Camera " + (i + 1));
            }
        }
    }

    /**
     * creo il metodo stampaCamereOccupate, che stampa tutte le camere occupate
     * 
     * @param l'array camereSingole
     */
    public static void stampaCamereOccupate(Cliente[] camereSingole){
        System.out.println("CAMERE OCCUPATE:");
        for (int i = 0; i < camereSingole.length; i++){
            if (camereSingole[i] != null){
                System.out.println("- Camera " + (i + 1) + ", " + camereSingole[i].toString());
            }
        }
    }


    public static void main(String[] args) {  


        Scanner scanner = new Scanner(System.in);


        final int N = 5; //creo la costante


        //creo i due array rappresentanti le camere
        Cliente[] camereSingole = new Cliente [N];
        //Cliente[] cameredoppie = new Cliente [N]


        String selezioneMenu;//creo la variabile "selezioneMenu" per selezionare una funzione
        String ClienteCheckout;//creo la variabile "ClienteCheckout" per inserire il Cliente che deve uscire e pagare
        int giornoAttuale;//creo la variabile "giornoAttuale" per definire il numero di giorno attuale
        int prezzoANotte = 300;//creo la variabile "prezzoAnotte" per definire il prezzo a notte di una camera dell'hotel
        int prezzo;//creo la variabile "prezzo", a cui verra' assegnato il prezzo finale di checkout


        do{
            System.out.println("Cosa vuoi fare? Seleziona un numero per continuare\n0) esci dal programma\n1) Check-in\n2) Check-out\n3) stampa camere libere\n4) stampa camere occupate\n");
            selezioneMenu = scanner.nextLine();
            switch (selezioneMenu){
                case "1":
                    // dichiaro l'oggetto Cliente, che conterra' tutti i nuovi clienti inseriti
                    Cliente c1 = new Cliente(null,0);
                    System.out.println("Inserisci il nome del cliente");
                    c1.nome = scanner.nextLine();
                    System.out.println("Inserisci il giorno di ingresso del cliente");
                    c1.giornoIngresso = scanner.nextInt();
                    scanner.nextLine();
                    if (checkIn(camereSingole, c1) == false){
                        System.out.println("Le camere sono tutte occupate! Il Cliente non puo' entrare");
                    }
                    break;
                case "2":
                    System.out.println("Inserisci il nome del cliente che deve fare checkout");
                    ClienteCheckout = scanner.nextLine();
                    System.out.println("Inserisci il giorno del mese attuale in formato numerico");
                    giornoAttuale = scanner.nextInt();
                    scanner.nextLine();
                    prezzo = pagamento(camereSingole, ClienteCheckout, giornoAttuale, prezzoANotte);
                    if (prezzo == -1){
                        System.out.println("Il Cliente ricercato non e' stato trovato!");
                    }
                    else{
                        System.out.println("Il Cliente " + ClienteCheckout + " deve pagare " + prezzo + " euro");
                    }
                    checkOut(camereSingole, ClienteCheckout);
                    break;
                case "3":
                    stampaCamereLibere(camereSingole);
                    break;
                case "4":
                    stampaCamereOccupate(camereSingole);
                    break;
                default:
                    System.out.println("hai sbagliato la selezione del menu'! Riprova");
                case "0":
            }
        }while (!"0".equals(selezioneMenu));
    }
}