package persistence;

import model.Event;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventRepository {
	private Map<Long, Event> dataBase = new HashMap<>();

	public void save(Event event) {
		dataBase.put(event.getId(), event);
	}

	public Collection<Event> findAll() {
		return dataBase.values();
	}

	public Event find(long id) {
		return dataBase.get(id);
	}

	public void saveToDisk(String filename) {
		try (FileOutputStream fos = new FileOutputStream(filename); ObjectOutputStream out = new ObjectOutputStream(fos)) {
			out.writeObject(dataBase);
		} catch (Exception ignored) {
		}
	}

	public void loadFromDisk(String filename) {
		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis)) {
			dataBase = (Map<Long, Event>) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception ignored) {
		}
	}
}
