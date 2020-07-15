package com.carbon.game.treasuremap.services;

import com.carbon.game.treasuremap.model.GameMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Slf4j
public class FileLoader {

    private static final String COMMENT_PREFIX = "#";

    private final EntityLoaderFactory entityLoaderFactory;
    private final GameMapLoaderFactory gameMapLoaderFactory;


    @Autowired
    public FileLoader(EntityLoaderFactory entityLoaderFactory, GameMapLoaderFactory gameMapLoaderFactory) {
        this.entityLoaderFactory = entityLoaderFactory;
        this.gameMapLoaderFactory = gameMapLoaderFactory;
    }

    public GameMap loadFile(File file) throws IOException {
        return loadFile(FileUtils.readLines(file, StandardCharsets.UTF_8));
    }

    public GameMap loadFile(List<String> lines) {

        GameMap gameMap = null;

        boolean mapLoaded = false;

        for (String line : lines) {
            if (!this.isCommentLine(line)) {
                if (!mapLoaded && this.gameMapLoaderFactory.isGameMap(line)) {
                    log.debug("La ligne est la carte : {}", line);
                    mapLoaded = true;
                    gameMap = this.gameMapLoaderFactory.getGameMap(line);
                } else if (mapLoaded) {
                    gameMap.addEntityCase(this.entityLoaderFactory.getEntityCase(line));
                } else {
                    throw new IllegalArgumentException("La ligne est mal formatée : " + line);
                }
            } else {
                log.debug("La ligne est un commentaire : {}", line);
            }
        }
        return gameMap;
    }

    private boolean isCommentLine(String line) {
        return line.startsWith(COMMENT_PREFIX);
    }

}
