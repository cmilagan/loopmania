package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * controller for the main menu.
 * TODO = you could extend this, for example with a settings menu, or a menu to load particular maps.
 */
public class MainMenuController {
    /**
     * facilitates switching to new game menu
     */
    private MenuSwitcher newMenuSwitcher;
    /**
     * facilitates load game
     */
    private MenuSwitcher gameSwitcher;
    /**
     * facilitates credit screen switch
     */
    private MenuSwitcher creditsSwitcher;
    /**
     * facilitates exit game switch
     */
    private MenuSwitcher exitSwitcher;

    public void setNewGameSwitcher(MenuSwitcher gameSwitcher){
        this.newMenuSwitcher = gameSwitcher;
    }

    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    public void setCreditsSwitcher(MenuSwitcher gameSwitcher){
        this.creditsSwitcher = gameSwitcher;
    }

    public void setExitSwitcher(MenuSwitcher gameSwitcher){
        this.exitSwitcher = gameSwitcher;
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToGame() throws IOException {
        gameSwitcher.switchMenu();
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToNewGameMenu() throws IOException {
        newMenuSwitcher.switchMenu();
    }

    /**
     * facilitates switching to credits
     * @throws IOException
     */
    @FXML
    private void switchToCredits() throws IOException {
        creditsSwitcher.switchMenu();
    }

    /**
     * Exits the program
     */
    @FXML
    private void exitGame() {
        exitSwitcher.switchMenu();
    }
}
