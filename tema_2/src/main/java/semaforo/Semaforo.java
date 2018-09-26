package semaforo;

public class Semaforo {
    private int color;

    public Semaforo() {
        this.color = 0;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getColor() {
        String clr = "";
        switch (color) {
        case 0:
            clr = "Rojo";
            break;
        case 1:
            clr = "Ambar";
            break;
        case 2:
            clr = "Verde";
            break;
        }
        return clr;
    }
}