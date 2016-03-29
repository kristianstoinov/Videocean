package interfaces;

import java.util.List;

import classes.TYPE;
import exceptions.ClipException;

public interface IStateDAO {

	TYPE getStateByID(int stateID) throws ClipException;


}