package com.carbon.game.treasuremap.services;

import com.carbon.game.treasuremap.model.GameMap;
import com.carbon.game.treasuremap.utils.OutputFormatter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
@Slf4j
public class FileWriter {

    public void writeFile(File file , GameMap gameMap) throws IOException {
        Objects.requireNonNull(file);
        Objects.requireNonNull(gameMap);

        if(file.isFile()) FileUtils.forceDelete(file);

        FileUtils.writeStringToFile(file, OutputFormatter.formatOutput(gameMap), StandardCharsets.UTF_8);

    }
}
