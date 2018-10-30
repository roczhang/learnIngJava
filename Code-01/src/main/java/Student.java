/**
 * Created by I076057 on 10/24/2018.
 */
public class Student implements Itest {

    int i = 1;

    static {
        System.out.println(" static function");
    }

    @Override
    public  String getName(){

        return  "yaguang - only one";
    }

    @Override
   public String toString(){
        return "yaguang - only one";
    }
}
