package com.example.interfaces;

import com.example.classes.TYPE;
import com.example.exceptions.ClipException;

public interface IStateDAO {

	TYPE getStateByID(int stateID) throws ClipException;


}