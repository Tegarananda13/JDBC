import java.util.Scanner;

public class Kasir {

        String username = "Tegar";
        String password = "Ananda";
        String cap  = "Tegar_A";
        Boolean loginBenar = false;
        Boolean inputcapBenar = false;
        String usernameBenar, passwordBenar,capBenar;

        public String getUsernameBenar() {
            return usernameBenar;
        }

    public void Login() {
        System.out.println("A STORE");
        System.out.println("===========================");
        System.out.println("Log In");
 
        try (Scanner scanner = new Scanner(System.in)) {
            {

                while (!loginBenar) {
                    System.out.print("username  : ");
                    usernameBenar = scanner.nextLine();
                    System.out.print("password  : ");
                    passwordBenar = scanner.nextLine();

                    if (usernameBenar.equals(username) && passwordBenar.equals(password)) {
                        break;
                    } else {
                        System.out.println("username atau password Salah!");
                    }
                }    

                while (!inputcapBenar){
                    System.out.println("Kode cap : " + cap);
                    System.out.print("Entry cap : ");
                    capBenar = scanner.nextLine();

                    if (capBenar.equals(cap)) {
                        System.out.println("Login berhasil!");
                        break;
                    } else {
                        System.out.println("cap Salah!");
                    }
                }  
            }
        }   
        System.out.println("---------------------------");  
    }  
}  