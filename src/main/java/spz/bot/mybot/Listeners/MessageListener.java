package spz.bot.mybot.Listeners;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import spz.bot.mybot.Main;

import java.io.IOException;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        //verificando se o autor é um bot pra não entrar em loop
        if(event.getAuthor().isBot() || event.isWebhookMessage()) {
            return;
        }

        //verifica qual é o tipo do canal e manda uma mensagem
        if(event.getChannelType().equals(ChannelType.PRIVATE)) {    //verifica se o bot está em um canal privado
            //if (event.getMessage().getContentRaw().startsWith(Main.bot_PREFIX)) {   //verifica se o texto recebido começa com o prefixo
                try {
                    Main.executeCommand(Main.bot_PREFIX, event);
                } catch (IOException e) {
                    event.getChannel().sendMessage("An error occured when trying to execute this command.").queue();
                    e.printStackTrace();
                }
            }
        }
    }
}
