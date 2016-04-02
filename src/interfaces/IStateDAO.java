package interfaces;

import classes.TYPE;
import exceptions.ClipException;

public interface IStateDAO {

	TYPE getStateByID(int stateID) throws ClipException;


}