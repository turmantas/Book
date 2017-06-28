
public class Program
{

    public static void main (String[] args) throws Exception
    {
    	System.out.println(DBase.getInstance().getConnection());
        Menu menu = new Menu ();
        menu.start ();
    }

}
