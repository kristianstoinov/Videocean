package com.example.interfaces;

import com.example.exceptions.UserProblemException;

public interface ILanguageDAO {

	int addLanguage(String language) throws UserProblemException;

	String getLanguageById(int languageId) throws UserProblemException;

	int getLanguageByName(String language) throws UserProblemException;

}