package ps3;

/**
 * rectangulo
 */
public class rectangulo {
  public static String generate(int x,int y) {
    String rectangulo = "";
    for (int i = 0; i < y; ++i){
      for (int j = 0; j < x; ++j) {
        rectangulo+="*";
      }
      rectangulo += "\n";
    }
    return rectangulo;
  }
  public static void main(String[] args) {
    System.out.println(generate(12, 6));
  }
}