package persistence;

public class Parametre {
    
    public static String IPHOST="127.0.0.1" ;
    public String Data;
    public static String username;
    public static String Host;
    
    public static String password;
    public static int Port=8081 ;
    public static String Util ;
    
    public Parametre(String username,String password,String Data){
    this.Data=Data;
    this.username=username;
    this.password=password;
    Host="jdbc:mysql://"+ IPHOST+":3306/"+Data+"?useUnicode=true&characterEncoding=UTF-8";
}

 

    
}
