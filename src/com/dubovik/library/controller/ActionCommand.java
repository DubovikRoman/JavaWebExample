package com.dubovik.library.controller;

import java.util.Map;

public interface ActionCommand {
    Map<String, String> execute(Map<String, String> command_parameters);
}
