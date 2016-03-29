package interfaces;

import java.util.List;

import classes.State;
import exceptions.ClipException;

public interface IStateDAO {

	State getStateByID(int stateID) throws ClipException;

	List<State> getAllStates() throws ClipException;

}