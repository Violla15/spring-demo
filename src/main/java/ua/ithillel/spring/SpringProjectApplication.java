package ua.ithillel.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ua.ithillel.spring.game.CarsGame;
import ua.ithillel.spring.game.TetrisGame;
import ua.ithillel.spring.runner.GameRunner;

@SpringBootApplication
@ComponentScan("ua.ithillel")
public class SpringProjectApplication {

    private static final String APP_CONTEXT_PATH = "src/main/resources/application_context.xml";

    public static void main(String[] args) {
        // Read from xml context
        try (FileSystemXmlApplicationContext xmlApplicationContext = new FileSystemXmlApplicationContext(APP_CONTEXT_PATH)) {
            GameRunner carsGameRunner = xmlApplicationContext.getBean("cars_game_runner", GameRunner.class);
            carsGameRunner.run();

            GameRunner tetrisGameRunner = xmlApplicationContext.getBean("tetris_game_runner", GameRunner.class);
            tetrisGameRunner.run();
        }

        // Read from annotations context
        try (ConfigurableApplicationContext context = SpringApplication.run(SpringProjectApplication.class, args)) {
            GameRunner tetrisGameRunner = context.getBean("tetris_game_runner", GameRunner.class);
            tetrisGameRunner.run();

            GameRunner carsGameRunner = context.getBean("cars_game_runner", GameRunner.class);
            carsGameRunner.run();

            TetrisGame tetrisGame = context.getBean("TetrisGame", TetrisGame.class);
            System.out.println("Tetris game version = " + tetrisGame.getVersion());

            // Singleton bean example
            TetrisGame tetris_v2 = context.getBean("tetris_v2", TetrisGame.class);
            tetris_v2.setVersion("aa");
            System.out.println(tetris_v2.getVersion());

            TetrisGame tetris_v22 = context.getBean("tetris_v2", TetrisGame.class);
            System.out.println(tetris_v22.getVersion());
        }
    }

    @Bean(name = "tetris_v2")
    @Scope(value = "singleton")
    public TetrisGame tetrisGamev2() {
        TetrisGame tetrisGame = new TetrisGame();
        tetrisGame.setVersion("2.0");
        return tetrisGame;
    }

    @Bean(name = "tetris_game_runner")
    @Scope(value = "singleton")
    public GameRunner tetrisGame() {
        GameRunner runner = new GameRunner();
        runner.setGame(tetrisGamev2());
        return runner;
    }

    @Bean(name = "cars_game_runner")
    @Scope(value = "singleton")
    public GameRunner carsGame() {
        GameRunner runner = new GameRunner();
        runner.setGame(new CarsGame());
        return runner;
    }

}
