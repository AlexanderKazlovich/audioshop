package com.epam.command;

import com.epam.controller.SessionRequestContext;
import com.epam.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionFactory {
    private static Logger logger = LoggerFactory.getLogger(ActionFactory.class);


    private static final String COMMAND = "command";

    public static ActionCommand defineCommand(SessionRequestContext sessionRequestContext){
        try {
            return CommandType.valueOf(
                            sessionRequestContext.getRequestParameterValue(COMMAND))
                            .getCommand();
        }catch (IllegalArgumentException e){
            logger.error("Illegal command");
        }
        return null;
    }
}
