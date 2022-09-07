import java.util.Scanner;
import java.io.*;
public class admin extends member_details {
    public void show_admin() throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Home page\n2. Add member\n3. Delete member\n4. See pending leave request\n5. See member info");
        switch (scan.nextInt())
        {
            case 1:
                String str[]={"kamal","saini"};
                entry.main(str);  
            case 2:
               try{add_member();
                   System.out.println("\n~~Adding member is sucessfull~~\n");
                    show_admin();
                   } 
               catch(Exception e){
                     System.out.println("problem in opening files,please try again later");
                }
                break;
            case 3:
                try {
                    scan.nextLine();
                    System.out.println("Enter type of member(Admin/faculty)");
                    String type=scan.nextLine();
                    System.out.println("Enter id of member");
                    String id=scan.nextLine();
                    //todo delete id in bplustree
                    System.out.println("Deleting node in B Plus Tree");
                    // btree BPT=new btree();
                    // BPT.search(Integer.parseInt(id));
                    btree.search(Integer.parseInt(id));
                    Leave_server obj1=new Leave_server();
                    obj1.dequeue(type,id,"leave_request.txt");
                    if (!dequeue(type,id,"member.txt")||!dequeue(type,id,"login_details.txt")||!dequeue(type,id,"leave_data.txt"))
                        System.out.println("\n~~Member with given id not exits~~\n");
                    else   
                        System.out.println("\n~~Deleting member is sucessfull~~\n");
                    show_admin();
                } catch (Exception e) {
                    System.out.println("error occured");
                }
                break;
            case 4:
                try{pending_leave_request();
                      System.out.println("");
                     show_admin();}
                catch(Exception e)
                {   e.printStackTrace();
                    System.out.println("Problem in opening leave_data server");
                }
                break;
            case 5:
                try {
                    /*scan.nextLine();
                    System.out.println("Enter member type(Admin/faculty)");
                    String type=scan.nextLine();
                    System.out.println("Enter id");             // For know info about particuler id
                    String id=scan.nextLine();                        holder
                    member_info(type,Integer.parseInt(id)); */
                    member_info1();
                    System.out.println("");
                    show_admin();
                } catch (Exception e) {
                    System.out.println("error in processing command");
                }
                break;
            default:
                System.out.println("Please enter number from 1 to 6");
                show_admin();
        }
    }
    public void approve_reject() throws Exception
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("\n1. Approve any leave request\n2. Reject any leave request\n3. Go back");   //Here we only concern about faculty
            switch(scan.nextInt())
            {
            case 1:
                scan.nextLine();
                System.out.println("Enter id of member");
                String id=scan.nextLine();
                BufferedReader bf1=new BufferedReader(new FileReader("leave_request.txt"));
                String line=null;
                while((line=bf1.readLine())!=null)
                {
                    String str[]=line.split(",");
                    if (str[0].equals("faculty")&&str[1].equals(id))
                        break;
                }
                if (line==null)
                    System.out.println("There is not faculty found with this id");
                else
                {
                    Update obj=new Update();
                    obj.update_leave(line.split(","));
                }
                break;
            case 2:
                scan.nextLine();
                System.out.println("Enter id of member");
                id=scan.nextLine();
                dequeue("faculty",id,"leave_request.txt");
                System.out.println("\n~~Leave request deleted~~\n");
                break;
            case 3:
                show_admin();
            default :
                System.out.println("Please enter write number");
                approve_reject();
            }
    }
}