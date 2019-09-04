/***********************************************
Autores: Daniel Alécio do Nascimento
         Francisca Neuvânia de Paula Barros 
************************************************/
/*********************************************** 
                Instruções
//100 load da M para os Rgs
//101 load Rgs  para o AC
//102 store dos AC para a M
//103 add AC = M +AC 
//104 sub AC = AC - M
************************************************/


public class SimuladorInstrucao{
static int PC = 0;  //contador do programa que aponta para a proxima instrucao
static int AC = 0; //acumulador
static int Instr = 0; //registrador que contem a instrucao
static int InstrType = 0;// tipo da instrucao
static int data_loc = 0; //local dos dados ou -1
static int data = 0; // operando corrente
static boolean run_bit = true;  
static int[] register = {5,6,0,7,89,0,0,0,0,0}; //conjunto de registradores
static int[] memory = {101,101,101, 101, 103, 15, 78, 12, 3, 1}; //metade da memoria é destinada a instruçoes e a outra a armazenamento de dados


public static void showMemory(int memory[]){ //mostrar memoria
    System.out.println("*******Memory********");
    for (int i = 0; i < memory.length; i++) {
        System.out.println("adress " + i + " [" + memory[i]+ "]");
    }
    
}
public static void showRegister(int register[]){ //mostrar registardores
    System.out.println("*******Register********");
    for (int i = 0; i < register.length; i++) {
        System.out.println("R " + i + " [" + register[i]+ "]");
    }
    
}
public static void interpret(int memory[],int starting_adresss){
    PC = starting_adresss; //inicia com a primeira posicao da memoria
    while(run_bit){
        Instr = memory[PC]; 
        PC = PC + 1; //atualiza pc
        InstrType = get_instr_type(Instr);//descobre o tipo de instrucao
        data_loc = find_data(InstrType,Instr); //local da instrucao
        if(data_loc >= (memory.length/2))//verifica se o dado ta na memoria ou nos registradores
            data =(memory[data_loc]); //busca dos dados na memoria
        else
            data =(register[data_loc]); //busca dos dados nos registardores
     
        execute(Instr, data); //executa a instrucao
           System.out.println("Valor de AC:" + AC); //mostrando valor de AC a cada execucao
        //if(memory[PC] == -1) //verifica se existe uma proxima instrucao, caso nao, encerra a aplicacao 
          //  run_bit =false;
          System.out.println("memoria" +memory[PC]);
         if(memory[PC] != 100 && memory[PC]!= 101 && memory[PC] != 102 && memory[PC]!= 103 && memory[PC] != 104)
            run_bit = false;
    }
//tem que verificar o criteiro de parada quando tiver cheia
}
//pega o tipo de instrucao
private static int get_instr_type(int instr){  
    if(instr == 100 || instr == 102 || instr == 103 || instr == 104 ) 
        return 1;   // R/M
    else if(instr == 101 ) 
        return 2;   // R/R
    return -1;

}
//encontra o local onde esta os dados(memoria ou registradores) e retorna a posicao
private static int find_data(int InstrType ,int Instr){
//caso em queos dados está na memoria
if(InstrType == 1){
    int data_lock = memory.length/2;
    data_lock = data_lock + (PC - 1) ;  //a posicao dos dados de uma instrucao estao a partir da metade da memoria
                                        //sendo sempre o tamanho da memoria / 2 + a posicao da instrucao 
    return data_lock;
}
//caso em que os dados está em algum registrador
else if(InstrType == 2){
    int data_lock = 0;
    data_lock = PC -1;  //a posicao do dado no vetor de registradores correponde a mesma posica da instrucao na memoria
    return data_lock;
}

return -1; //nenhum dado
}
private static void execute(int Instr, int data){
    switch(Instr){
    case 100: //carregar da memoria para os registradores
       register[data_loc] = data;
       break;
    case 101://carregar dos registardores para AC
        AC = data; //7
        break;
    case 102:
        memory[data_loc] = AC;
        break;
    case 103:
        AC = AC + data;
        break;
    case 104:
        AC = AC - data;
        break;
    }  
}  
    public static void main(String[] args) {
        interpret(memory, 0);//funcao principal
        showMemory(memory);// mostrar memoria
        showRegister(register);//mostrar registradores
        System.out.println("Register AC: " + AC); 
        
    }

    
}
