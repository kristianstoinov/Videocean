package com.example.interfaces;

import com.example.exceptions.UserProblemException;

public interface ICountryDAO {

	int addCountry(String country) throws UserProblemException;

	String getCountryById(int countryId) throws UserProblemException;

	int getCountryByName(String countryName) throws UserProblemException;

}