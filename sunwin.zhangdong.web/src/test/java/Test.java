import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Created by Administrator on 2017/3/2.
 */
public class Test {

    public static void main(String[] args){

        String newPwd = BCrypt.hashpw("123456", BCrypt.gensalt());
        System.out.println(newPwd);
        System.out.println(newPwd);
    }
}
