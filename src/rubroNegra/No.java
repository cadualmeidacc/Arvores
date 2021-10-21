package rubroNegra;

public class No {
    int value;
    No father;
    No left;
    No right;
    int color;
    
    public String color(){
        if(color == 0){
            return "PRETO";
        } else if(color == 1){
        return "VERMELHO";
        }
        return "INVÁLIDO";
    }

    @Override
    public String toString() {
        if (value == 0 && color == 0){
            return "false";
        }
        return "true: No " + value + " (" + color() + ')';
    }
    
    
}