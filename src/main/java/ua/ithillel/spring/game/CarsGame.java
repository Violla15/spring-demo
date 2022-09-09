package ua.ithillel.spring.game;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class CarsGame implements GameConsole {

    @Setter
    @Getter
    private String version;

    public void up() {
        System.out.println("speed up");
    }

    public void down() {
        System.out.println("slow down");
    }

    public void left() {
        System.out.println("turn left");
    }

    public void right() {
        System.out.println("turn right");
    }
}
