import java.io.*;
public class login_details {
    private final String login_details="login_details.txt";
    public void log_id(int id,String pass,int x) throws Exception
    {
        BufferedReader bf=new BufferedReader(new FileReader("login_details.txt"));
        String line=bf.readLine();
        int f=0;
        String type=null;
        if (x==1)
            type="Admin";
        else
            type="faculty";
        while(line!=null)
        {
            String[] str=line.split(",");
            if (str[0].equals(type)&&(str[1].equals(Integer.toString(id)))&&(str[2].equals(pass)))
            {

                if (type.equals("Admin"))
                {
                   admin add=new admin();
                   add.show_admin();
                }
                else
                {
                   faculty add=new faculty(id);
                }
                f=1;
                break;
            }
            line=bf.readLine();
        }
        if (f==0)
        {
           System.out.println("\nSorry,Incorrect id or password\n");
           String str[]={"kamal","saini"};
           entry.main(str);
        }
    }
}