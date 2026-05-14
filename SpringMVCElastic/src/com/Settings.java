package com;



/**
 * <i>This class has responsibility to hold settings related value and persist it to elastic. </i><br>
 * 
 * They are as:<br>
 * <br>
 * a). attribute---->storage location<br>
 * b).Value--------->"C:\" or and drive.
 * 
 * 
 * @author Vikash
 *
 */
public class Settings {

	String attribute;
	String value;

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String key) {
		this.attribute = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Settings [attribute=" + attribute + ", value=" + value + "]";
	}
	
	
}
