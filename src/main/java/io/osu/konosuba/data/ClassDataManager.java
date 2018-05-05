package io.osu.konosuba.data;

import com.google.gson.Gson;
import io.magiccraftmaster.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ClassDataManager {
	private final Gson gson = new Gson();
	private final File file;
	private final List<ClassData> ClassDataList = new ArrayList<>();

	public ClassDataManager(File file) {
		this.file = file;
	}

	public void load() {
		if (!file.exists()) return;
		try {
			List<String> lines = Files.readAllLines(file.toPath());
			JSONArray array = new JSONArray(StringUtils.toString(lines.toArray(new String[lines.size()]), ""));
			for (Object object : array) {
				if (object instanceof JSONObject) {
					ClassDataList.add(gson.fromJson(object.toString(), ClassData.class));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			PrintWriter printWriter = new PrintWriter(file);
			JSONArray jsonArray = new JSONArray();
			for (ClassData ClassData : ClassDataList) {
				jsonArray.put(new JSONObject(gson.toJson(ClassData)));
			}
			printWriter.print(jsonArray.toString(15).replaceAll(" {15}", "\t"));
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private OffsetDateTime lastSave = OffsetDateTime.now();

	public void trySave() {
		if (!OffsetDateTime.now().isAfter(lastSave.plusMinutes(2))) return;
		lastSave = OffsetDateTime.now();
		save();
	}

	public boolean hasData(String Class) {
		for (ClassData ClassData : ClassDataList) {
			if (ClassData.getClasss() == Class) return true;
		}
		return false;
	}

	public ClassData getData(String Class) {
		for (ClassData ClassData : ClassDataList) {
			if (ClassData.getClasss() == Class) return ClassData;
		}
		ClassData ClassData = new ClassData(Class);
		ClassDataList.add(ClassData);
		return ClassData;
	}
}
