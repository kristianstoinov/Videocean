package com.example.model;

public class TextDAO {

	public Object getTextById(int index) {
		if (index == 2)
			return "Ave toa NIki mnogo golemi raboti pokazva.";
		return "Hello to Spring MVC. I like it!";
	}

}
