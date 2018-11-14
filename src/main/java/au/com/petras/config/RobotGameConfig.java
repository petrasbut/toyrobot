package au.com.petras.config;

import au.com.petras.robot.Robot;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class RobotGameConfig {
    static Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(Robot.class);
    }

    public int rows;
    public int columns;

    public RobotGameConfig(String configFilePath) {

        if (StringUtils.isBlank(configFilePath)) {
            loadDefaultConfig();
        } else {
            Configurations configs = new Configurations();
            try {
                Configuration config = configs.properties(new File(configFilePath));

                rows = config.getInt("table.size.x");
                columns = config.getInt("table.size.y");

            } catch (ConfigurationException e) {
                LOGGER.error("Error while loading RobotGame configuration", e);
                loadDefaultConfig();
            }
        }
    }

    private void loadDefaultConfig() {
        rows = 5;
        columns = 5;
    }
}
