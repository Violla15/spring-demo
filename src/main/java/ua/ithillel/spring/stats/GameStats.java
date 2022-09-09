package ua.ithillel.spring.stats;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GameStats {

    @Value("${stats.initial.count}")
    private int buttonsPressed;

    public void pressButton() {
        buttonsPressed++;
    }

    public void printStats() {
        System.out.println("Total buttons pressed count = " + buttonsPressed);
    }


}
