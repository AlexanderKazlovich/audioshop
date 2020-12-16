package com.epam.command;

import com.epam.controller.SessionRequestContext;

public interface ActionCommand {
    public String execute(SessionRequestContext sessionRequestContext);
}
