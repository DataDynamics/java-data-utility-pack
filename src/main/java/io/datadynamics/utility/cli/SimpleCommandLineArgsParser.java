package io.datadynamics.utility.cli;

public class SimpleCommandLineArgsParser {

    public CommandLineArgs parse(String... args) {
        CommandLineArgs commandLineArgs = new CommandLineArgs();
        for (String arg : args) {
            if (arg.startsWith("--")) {
                String optionText = arg.substring(2);
                String optionName;
                String optionValue = null;
                int indexOfEqualsSign = optionText.indexOf('=');
                if (indexOfEqualsSign > -1) {
                    optionName = optionText.substring(0, indexOfEqualsSign);
                    optionValue = optionText.substring(indexOfEqualsSign + 1);
                } else {
                    optionName = optionText;
                }
                if (optionName.isEmpty()) {
                    throw new IllegalArgumentException("Invalid argument syntax: " + arg);
                }
                commandLineArgs.addOptionArg(optionName, optionValue);
            } else {
                commandLineArgs.addNonOptionArg(arg);
            }
        }
        return commandLineArgs;
    }
}
