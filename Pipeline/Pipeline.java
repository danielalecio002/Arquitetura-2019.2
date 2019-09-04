
package Principal;


import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pipeline {
     static boolean estado = false;
    public static void main(String[] args) throws InterruptedException {
    
    //leitura a partir do teclado
    Scanner teclado = new Scanner(System.in);
    System.out.println("Digite o Número de Tarefas:");
    int  NumTarefas = teclado.nextInt();
    
      
  
    // criação das threads    
    Thread t1 = new Thread(){
        @Override
        public void run() {
             int i = 1;          
             
            while (i <= NumTarefas){
                System.out.println("Buscando I"  + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                Logger.getLogger(Pipeline.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("---------------------"); 
            i ++;               
            }
        }
    };
    Thread t2 = new Thread(){
        @Override
        public void run() {
            int i = 1;
         
            while(i <= NumTarefas){
                try {
                 Thread.sleep(1001);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Pipeline.class.getName()).log(Level.SEVERE, null, ex);
                }
                     System.out.println("Decodificando I" + (i));
                    
                     
            i++;
            }
            
        }
    };
    Thread t3 = new Thread(){
        @Override
        public void run() {
            int i = 1;
            while(i <= NumTarefas){
                //para a execução em ordem da terceira thread é necessario atrasar ela em 2003 milesimo 
                //porem a partir do segundo caso será necessário um atraso diferente que é 1003 milesimos  
             if(i == 1){
                try {
                 Thread.sleep(2003);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Pipeline.class.getName()).log(Level.SEVERE, null, ex);
                }
             }else{
             try {
                 Thread.sleep(1002);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Pipeline.class.getName()).log(Level.SEVERE, null, ex);
                }
             
             }
                System.out.println("Buscando Operandos I" + i);  
                if((NumTarefas == 2 && i == 1) ){
                           System.out.println("---------------------");
                }
       
            i++;
            }
        }
    };
    Thread t4 = new Thread(){
        @Override
        public void run() {
            int i = 1;
            while(i <= NumTarefas){
                //Pra a thread 4 o atraso vai ser um pouco maior 3006 segundo no primeiro caso já a partir do segundo 1003
          if(i == 1){ //tratando o primeiro caso
            try {
                 Thread.sleep(3006);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Pipeline.class.getName()).log(Level.SEVERE, null, ex);
                }
          }else{//Tratando os demais caso que precisam de um atraso menor para manter a sequencia de execução
          try {
                 Thread.sleep(1003);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Pipeline.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                System.out.println("Executando I" + (i));
                if((NumTarefas == 3 && i == 1) ||(NumTarefas == 2 && i == 1) ){
                           System.out.println("---------------------");
                     }
                
            i++;
            
            }
        }
    
    };
    Thread t5 = new Thread(){
        @Override
        public void run() {
            int i = 1;
            while(i <= NumTarefas){
          if(i == 1){// a quinta thread como será a ultima a ser executada ela tem um atraso ainda mais relevante
            try {
                 Thread.sleep(4010);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Pipeline.class.getName()).log(Level.SEVERE, null, ex);
                }
          }else{
          try {
                 Thread.sleep(1004);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Pipeline.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 System.out.println("Gravando I" + (i));
             if(i == (NumTarefas - 1)  || i == (NumTarefas - 3) || i == (NumTarefas -2)){
                   System.out.println("--------------------");
               }
             i++;
            
            }        
        }
    };   
     //Execucanto as Threads
           t1.start();
           t2.start();
           t3.start();
           t4.start();
           t5.start();
           
          
    }
}
