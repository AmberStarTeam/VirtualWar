package org.amberstar.virtualwar.sound;


public class Lancement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Son player = new Son("src/Son/640.wav", 400);
		InputStream stream = new ByteArrayInputStream(player.getSamples());
		System.out.println("Start non thread");
		player.play(stream);
		System.out.println("End non thread");

		player = new Son("src/Son/640.wav");
		stream = new ByteArrayInputStream(player.getSamples());
		

		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}*/

		ThreadSoundRun tmp;
		
		for(String str : "640.wav\nboom.wav\ndeathOfRobots.wav\nmoveTank.wav\nrunning.wav\nshotShooter.wav\nshotTank.wav".split("\n")){
			System.out.println(str);
			tmp = new ThreadSoundRun("sounds/" + str);
			tmp.start();
			while (tmp.isAlive()){
				
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		tmp = new ThreadSoundRun("src/Son/running.wav", 1000);
		tmp.start();

	}

}
