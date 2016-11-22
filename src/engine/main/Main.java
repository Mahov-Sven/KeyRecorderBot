package engine.main;

import engine.bot.Bot;
import engine.bot.BotRecorder;

public class Main{

	public static void main(String... args){
		
		Bot bot = new Bot(null, null);
		BotRecorder botRecorder = new BotRecorder(null, null);
		
		botRecorder.start();
		System.out.println("Recording Started");
		
		while(botRecorder.isRunning()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Recording Done");
		
		bot.addEvents(botRecorder.getRecordedEvents());
		
		bot.start();
		System.out.println("Bot Started");
		
		while(bot.isRunning()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Bot Done");
		
	}
}
