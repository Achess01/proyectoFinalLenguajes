
import com.achess.backend.TokenType;

/**
 *
 * @author achess
 */
public class Pruebas {
    public static void main(String[] args) {
        String a[] ="234".split(",");
        for(String l: a){
            System.out.println(l);
        }
        System.out.println(TokenType.ASIGNACION);
    }
}
