package com.epam.command;

import com.epam.command.impl.LoginCommand;
import com.epam.command.impl.LogoutCommand;

public enum CommandType {
    LOG_IN(new LoginCommand()),
    LOG_OUT(new LogoutCommand());


    private ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
