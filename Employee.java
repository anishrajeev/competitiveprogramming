public class Employee{
    public int E_no, E_sal;
    public String E_name, E_address;
    public static int E_nostatic, E_salstatic;
    public static String E_namestatic, E_addressstatic;
    public Employee(int no, String name, int sal, String address){
        E_no = no;
        E_sal = sal;
        E_name = name;
        E_address = address;
        E_nostatic = no;
        E_salstatic = sal;
        E_namestatic = name;
        E_addressstatic = address;
    }
    public static void get(){
        System.out.println(E_nostatic);
        System.out.println(E_salstatic);
        System.out.println(E_namestatic);
        System.out.println(E_addressstatic);
    }
}