package com.inventoryManagement.constants;

/**
 * <p>
 * All action will be declared here so that developer can use easily and confidently
 * 
 * @author Assaduzzaman Sohan
 */
public enum ActionType {
	NEW("NEW"), SELECT("SELECT"), SELECT_ALL("SELECT_ALL"), UPDATE("UPDATE"), DELETE("DELETE"), SAVE("SAVE"),
	SEARCH("SEARCH"), SELECT_BY_BRAND_ID("SELECT_BY_BRAND_ID");

	private final String actionType;

	private ActionType(String actionType) {
		this.actionType = actionType;
	}

	@Override
	public String toString() {
		return actionType;
	}
}
