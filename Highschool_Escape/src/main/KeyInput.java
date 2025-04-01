package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Această clasă gestionează intrările de la tastatură în jocul Highschool Escape.
public class KeyInput implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, last_pressed, enterPressed, shotKeyPressed;

    // Constructorul clasei KeyInput
    public KeyInput(GamePanel gp) {
        this.gp = gp;
    }

    // Metoda pentru gestionarea tastelor apăsate
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();


        if (gp.gameState == gp.titleState) {
            titleState(code);
        }
        else if(gp.gameState == gp.playState)
        {
            try {
                playState(code);
            } catch (ImageSetupException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(gp.gameState == gp.pauseState)
        {
            pauseState(code);
        }
        else if(gp.gameState == gp.dialogueState)
        {
            dialogueState(code);
        }
        else if(gp.gameState == gp.characterStare)
        {
            try {
                characterState(code);
            } catch (ImageSetupException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(gp.gameState == gp.gameOverState)
        {
            try {
                gameOverState(code);
            } catch (ImageSetupException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void gameOverState(int code) throws ImageSetupException {
        if(code == KeyEvent.VK_UP)
        {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0)
            {
                gp.ui.commandNum = 1;
            }
        }
        if(code == KeyEvent.VK_DOWN)
        {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1)
            {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER)
        {
            if(gp.ui.commandNum == 0)
            {
                gp.gameState = gp.playState;
                gp.retry();
            }
            else if(gp.ui.commandNum == 1)
            {
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }

    public void titleState(int code)
    {
        if (code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 3;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 3) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            // Gestionarea comenzilor din meniul de titlu
            switch (gp.ui.commandNum) {
                case 0:
                    gp.gameState = gp.playState;
                    break;
                case 1:
                    gp.loadData();
                    gp.gameState = gp.playState;
                    break;
                case 2:
                    //TODO: Implementarea unei funcționalități pentru opțiunea 2
                    break;
                case 3:
                    System.exit(0); // Ieșirea din joc
                    break;
            }
        }
    }

    public void playState(int code) throws ImageSetupException {
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
            last_pressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
            last_pressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            // Schimbarea între starea de joc și starea de pauză
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_C) {
            // Schimbarea între starea de joc și starea de pauză
            gp.gameState = gp.characterStare;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if(code == KeyEvent.VK_S)
        {
            gp.saveData();
            gp.gameState = gp.titleState;
        }
    }

    public void pauseState(int code)
    {
        if(code == KeyEvent.VK_P)
        {
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code)
    {
        if(code == KeyEvent.VK_ENTER)
        {
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code) throws ImageSetupException {
        if(code == KeyEvent.VK_C)
        {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_UP) {
            if(gp.ui.platerSlotRow > 0)
            {
                gp.ui.platerSlotRow--;
            }
        }
        if (code == KeyEvent.VK_LEFT) {
            if(gp.ui.playerSlotCol > 0)
            {
                gp.ui.playerSlotCol--;

            }
        }
        if (code == KeyEvent.VK_DOWN) {
            if(gp.ui.platerSlotRow < gp.ui.maxSlotRow -1)
            {
                gp.ui.platerSlotRow++;
            }
        }
        if (code == KeyEvent.VK_RIGHT) {
            if(gp.ui.playerSlotCol < gp.ui.maxSlotCol -1)
            {
                gp.ui.playerSlotCol++;
            }
        }
        if(code == KeyEvent.VK_ENTER)
        {
            gp.player.selectItem();
        }
    }
    // Metoda pentru gestionarea tastelor eliberate
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // Resetarea stării tastelor direcționale când acestea sunt eliberate
        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
    }

    // Metoda necesară pentru implementare, dar nu este folosită în acest context
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
