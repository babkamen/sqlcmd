package view;

import java.util.Scanner;

/**
 * Created by Rostyslav on 09/08/16.
 */
public class Console implements View {
    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public String read() {
      Scanner in=new Scanner(System.in);
      return in.nextLine();
    }
}
