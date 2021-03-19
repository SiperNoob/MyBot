package spz.bot.mybot.Commands;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import spz.bot.mybot.Utils.CommandExecutor;

public class HelloCommand implements CommandExecutor {
    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {

        MessageChannel channel = event.getChannel();

        channel.sendMessage("Hello my friend!").queue();

        return true;
    }

    @Override
    public String name() {
        return "Hello";
    }

    @Override
    public String description() {
        return "Hello there!";
    }

    @Override
    public String[] alieases() {
        String[] aliases = new String[1];
        aliases[0] = "Hey";
        return aliases;
    }
}
