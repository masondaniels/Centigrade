package com.github.masondaniels.centigrade;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class Main {

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("Set first argument as token");
			return;
		}
		
		final DiscordClient client = DiscordClient.create(args[0]); // put discord token as args[0]
		final GatewayDiscordClient gateway = client.login().block();

		gateway.on(MessageCreateEvent.class).subscribe(event -> {
			String nickname = "???";
			try {
				nickname = event.getMember().get().getNickname().get();
			} catch (Exception e) {
				
			}
			
			nickname = event.getMember().get().getDisplayName();
			
			final Message message = event.getMessage();
			// Literally all of centigrade relies on this method
			if (!event.getMember().get().isBot()) {
				Centigrade.evaluate(message, nickname);
			}
			
		});

		gateway.onDisconnect().block();

	}

}
