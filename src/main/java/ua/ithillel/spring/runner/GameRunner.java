package ua.ithillel.spring.runner;

import lombok.Setter;
import org.springframework.stereotype.Component;
import ua.ithillel.spring.game.GameConsole;

@Component
public class GameRunner {

    @Setter
    private GameConsole game;

    public void run() {
        game.up();
        game.down();
        game.left();
        game.right();
    }

}
