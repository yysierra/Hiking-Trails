package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Utilities implements Serializable {
	public static void importInfo() {
		try (Scanner in = new Scanner(new File("data/hikingtrails.txt"))) {
			while (in.hasNext()) {
				String[] trailInfo = in.nextLine().split(",");
				if (trailInfo.length == 6) {
					TrailBag.getInstance().add(new Trail(trailInfo[0], trailInfo[1], Double.parseDouble(trailInfo[2]),
							Double.parseDouble(trailInfo[3]), trailInfo[4], trailInfo[5]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveInfo() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/data.dat"))) {
			oos.writeObject(UserBag.getInstance());
			oos.writeObject(TrailBag.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadInfo() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/data.dat"))) {
			UserBag users = (UserBag) ois.readObject();
			TrailBag trails = (TrailBag) ois.readObject();
			UserBag.getInstance().setUserBag(users);
			TrailBag.getInstance().setTrailBag(trails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
