package proyectofinal;

/**
 *
 * @author luisa
 */
public class ProyectoFinal {
    
    public static void main(String[] args) {
        
        String[] pruebas = {
            "7+10+2",
            "1+5*(7-1)+10",
            "5+7+(1+2*(8-9)+2)-10",
            "9-2*(75-9+(6+3+(1+2)*1)+7)+9",
            "5++1+10",
            "-5+10",
            "74-7/7",
            "10+9-A"
        };

        for (String p : pruebas) {
            try {
                System.out.println(p + " = " + evaluar(p));
            } catch (IllegalArgumentException e) {
                System.out.println(p + " -> Excepcion: " + e.getMessage());
            }
        }
    }

    public static int evaluar(String expr) {
        if (expr.isEmpty()) {
            throw new IllegalArgumentException("Expresion vacia o mal formada");
        }

        int profundidad = 0;
        int ultimoSumaResta = -1;
        int ultimoMultiplicacion = -1;

        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == ')') {
                profundidad++;
            } else if (c == '(') {
                profundidad--;
                if (profundidad < 0) {
                    throw new IllegalArgumentException("Parentesis mal balanceados");
                }
            } else if (profundidad == 0) {
                if ((c == '+' || c == '-') && ultimoSumaResta == -1) {
                    ultimoSumaResta = i;
                } else if (c == '*' && ultimoMultiplicacion == -1) {
                    ultimoMultiplicacion = i;
                }
            }
        }
        if (profundidad != 0) {
            throw new IllegalArgumentException("Parentesis mal balanceados");
        }

        if (ultimoSumaResta != -1) {
            int izq = evaluar(expr.substring(0, ultimoSumaResta));
            int der = evaluar(expr.substring(ultimoSumaResta + 1));
            return expr.charAt(ultimoSumaResta) == '+' ? izq + der : izq - der;
        }

        if (ultimoMultiplicacion != -1) {
            int izq = evaluar(expr.substring(0, ultimoMultiplicacion));
            int der = evaluar(expr.substring(ultimoMultiplicacion + 1));
            return izq * der;
        }
        
        if (expr.charAt(0) == '(' && expr.charAt(expr.length() - 1) == ')') {
            return evaluar(expr.substring(1, expr.length() - 1));
        }

        if (expr.matches("\\d+")) {
            return Integer.parseInt(expr);
        }

        throw new IllegalArgumentException("Formato invalido: " + expr);
    }
    
}
