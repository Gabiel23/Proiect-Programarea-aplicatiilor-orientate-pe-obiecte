package main;

import javax.swing.*;

// Aceasta este clasa principala a aplicatiei care lanseaza jocul Highschool Escape.
public class Main {
    public static void main(String[] args) throws ImageSetupException {
        // Crearea unei ferestre pentru joc.
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Inchiderea aplicatiei la inchiderea ferestrei.
        window.setResizable(true
        ); // Permite redimensionarea ferestrei.
        window.setTitle("Highschool Escape"); // Setarea titlului ferestrei.

        // Crearea panoului de joc.
        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel); // Adaugarea panoului de joc la fereastra.

        window.pack(); // Redimensionarea ferestrei pentru a se potrivi continutului.

        window.setLocationRelativeTo(null); // Centrarea ferestrei pe ecran.
        window.setVisible(true); // Afisarea ferestrei.

        // Initializarea jocului si pornirea firului de executie pentru actualizarea si desenarea jocului.
        gamepanel.setUp_game();
        gamepanel.StartGameThread();
    }
}
