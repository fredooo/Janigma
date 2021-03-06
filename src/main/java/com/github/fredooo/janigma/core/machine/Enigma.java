package com.github.fredooo.janigma.core.machine;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.github.fredooo.janigma.core.symbols.NoSuchSymbolException;
import com.github.fredooo.janigma.core.symbols.Original;

/**
 * Implements the common functionalities of an Enigma M3 and M4.
 * @author Frederik Dennig
 * @since 2011-11-18
 * @version 0.0.4 (last revised 2016-03-02)
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "machine")
@JsonSubTypes({ @Type(value = EnigmaM3.class, name = "m3"), @Type(value = EnigmaM4.class, name = "m4") })
public abstract class Enigma {
	
	@JsonProperty("rightRotor")
	protected Rotor rightRotor;
	
	@JsonProperty("middleRotor")
	protected Rotor middleRotor;
	
	@JsonProperty("leftRotor")
	protected Rotor leftRotor;
	
	@JsonProperty("plugboard")
	protected Plugboard plugboard;
		
	/**
	 * Constructs a basic Enigma object, that only consists of a
	 * plugboard and rotors.
	 * @see EnigmaM3
	 * @see EnigmaM4
	 */
	protected Enigma() {
		this.rightRotor = Rotor.createRotor(Rotor.M3_III);
		this.middleRotor = Rotor.createRotor(Rotor.M3_II);
		this.leftRotor = Rotor.createRotor(Rotor.M3_I);
		this.plugboard = new Plugboard();
	}
	
	/**
	 * Returns the right rotor of this Enigma machine.
	 * @return the current right rotor 
	 */
	public Rotor getRightRotor() {
		return rightRotor;
	}
	
	/**
	 * Sets the right rotor of the this Enigma machine to another one.
	 * @param rotor a given rotor
	 */
	public void setRightRotor(Rotor rotor) {
		rightRotor = rotor;
	}
	
	/**
	 * Returns the middle rotor of this Enigma machine.
	 * @return the current middle rotor 
	 */
	public Rotor getMiddleRotor() {
		return middleRotor;
	}

	/**
	 * Sets the middle rotor of the this Enigma machine to a given one.
	 * @param rotor a given rotor
	 */
	public void setMiddleRotor(Rotor rotor) {
		middleRotor = rotor;
	}

	/**
	 * Returns the left rotor of this Enigma machine.
	 * @return the current left rotor 
	 */
	public Rotor getLeftRotor() {
		return leftRotor;
	}
	
	/**
	 * Sets the left rotor of the this Enigma machine to a given one.
	 * @param rotor a given rotor
	 */
	public void setLeftRotor(Rotor rotor) {
		leftRotor = rotor;
	}

	/**
	 * Returns the plugboard of this Enigma M3 machine.
	 * @return the plugboard
	 */
	public Plugboard getPlugboard() {
		return plugboard;
	}
	
	/**
	 * Applies the mechanical changes of one keypress. 
	 */
	protected final void actuateSteppingMechanism() {
    	for (int i = 0; i < middleRotor.getTransferNotches().length; i++) {
    		if (middleRotor.getPosition() == middleRotor.getTransferNotches()[i]) {		
    			// The middle rotor increments its position on its own transfer notch,
    			// this is known as "double stepping"
    			middleRotor.incrementPosition();	
    	    	// The middle rotor moves the right rotor, if it is on its transfer
    	    	// notch postion
    			leftRotor.incrementPosition();
    			break;
    		}
    	}
    	// The leftmost rotor moves the middle rotor, if it is on its transfer
    	// notch postion
		for (int i = 0; i < rightRotor.getTransferNotches().length; i++) {
			if (rightRotor.getPosition() == rightRotor.getTransferNotches()[i]) {
				middleRotor.incrementPosition();
				break;
			}
		}	
		// The right-most rotor moves for every character
		rightRotor.incrementPosition();	
		// The greek rotor (if available) is static and does not rotate
	}
	
	/**
	 * This method converts a string to an encrypted output or vice versa. 
	 * @param input the input to convert
	 * @return the encrypted or decrypted input
	 * @throws NoSuchSymbolException if a character of the input is not a valid symbol
	 */
	public final String use(String input) throws NoSuchSymbolException {
		String s = "";
		for(int i = 0; i < input.length(); i++) {
			s += use(input.charAt(i));
		}
		return s;
	}
	
	/**
	 * This method converts one single char input to an encrypted output or
	 * vice versa.
	 * @param input the input to convert
	 * @return the encrypted or decrypted input
	 * @throws NoSuchSymbolException if the character is not a valid symbol
	 */
	public final char use(char input) throws NoSuchSymbolException {
		return Original.toChar(use(Original.toInt(input)));
	}

	/**
	 * This method converts one single integer input to an encrypted output or
	 * vice versa.
	 * @param input the input to convert
	 * @return the encrypted or decrypted input
	 */
	public abstract int use(int input);

}
