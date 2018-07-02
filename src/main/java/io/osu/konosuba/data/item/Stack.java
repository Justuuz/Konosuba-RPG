package io.osu.konosuba.data.item;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public final class Stack {
	// = Stack parsing ====================================================================================
	public String toJSON(Stack[] stacks) {
		JSONArray array = new JSONArray();
		for (Stack stack : stacks) {
			if (stack.getQuantity() < 1) continue; // Dump empty/negative stacks
			JSONObject json = new JSONObject();
			json.put("id", stack.getItem().getId());
			if (stack.getQuantity() > 1)json.put("q", stack.getQuantity()); // Omit quantity if equal to 1
			array.put(json);
		}
		return array.toString();
	}

	public Stack[] toStacks(String json) {
		Stack[] stacks = new Stack[json.length()];

		List<Object> list = new JSONArray(json).toList();
		for (int i=0, len=list.size(); i<len; i++) {
			Object obj = list.get(i);
			if (obj != null && obj instanceof JSONObject) {
				JSONObject stack = (JSONObject) obj;
				stacks[i] = new Stack(Item.getItem(stack.getInt("id")), stack.has("q") ? stack.getInt("q") : 1);
			}
		}

		// TODO a null scan should be here but meh

		return stacks;
	}

	// = Stack data =======================================================================================
	private final Item item;
	private int quantity;

	public Stack(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
