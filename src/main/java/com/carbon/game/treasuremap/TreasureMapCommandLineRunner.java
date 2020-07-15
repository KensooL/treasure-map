package com.carbon.game.treasuremap;


import com.carbon.game.treasuremap.model.GameMap;
import com.carbon.game.treasuremap.services.FileLoader;
import com.carbon.game.treasuremap.services.FileWriter;
import com.carbon.game.treasuremap.services.MovementSimulator;
import com.carbon.game.treasuremap.utils.OutputFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class TreasureMapCommandLineRunner implements CommandLineRunner {

    private final File inputFile = new File("input.txt");
    private final File outputFile = new File("output.txt");

    private final FileLoader fileLoader;
    private final FileWriter fileWriter;
    private final MovementSimulator movementSimulator;

    @Autowired
    public TreasureMapCommandLineRunner(FileLoader fileLoader, FileWriter fileWriter, MovementSimulator movementSimulator) {
        this.fileLoader = fileLoader;
        this.fileWriter = fileWriter;
        this.movementSimulator = movementSimulator;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            if (inputFile.isFile()) {
                GameMap gameMap = this.fileLoader.loadFile(inputFile);

                log.info("Etat avant simulation :\n{}",OutputFormatter.formatState(gameMap));

                this.movementSimulator.simulate(gameMap);

                log.info("Etat après simulation :\n{}", OutputFormatter.formatState(gameMap));

                this.fileWriter.writeFile(outputFile, gameMap);

            } else {
                log.error("Le fichier {} n'existe pas.", inputFile.getName());
            }
        }catch (Exception e){
            log.error("Erreur lors de l'exécution du programme : {}", e.getMessage());
            System.exit(1);
        }
    }
}
