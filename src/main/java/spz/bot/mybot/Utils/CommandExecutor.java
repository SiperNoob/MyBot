package spz.bot.mybot.Utils;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandExecutor {

    //  ALWAYS RETURNS TRUE UNLESS YOU WANT THE COMMAND TO BE DISABLED
    boolean execute(String[] args, MessageReceivedEvent event); //não sei o que isso faz

    String name();

    String description();

    String[] alieases();
}
