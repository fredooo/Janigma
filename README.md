Janigma (Java Enigma cipher machine)
====================================

[![Build Status](https://travis-ci.org/fredooo/Janigma.svg?branch=master)](https://travis-ci.org/fredooo/Janigma) [![Coverage Status](https://coveralls.io/repos/github/fredooo/Janigma/badge.svg?branch=master)](https://coveralls.io/github/fredooo/Janigma?branch=master)

A simulator of the Enigma M3 and M4 electro-mechanical rotor cipher machines from World War II. The Enigma M3 and M4 simulation and a simple GUI are implemented, but unverified and untested.

#### Current task:

Implementation of a text-based console interface and direct file encyption and decryption.

#### Goals:

* Clean and simple implementation of
    - Enigma M3 with rotors I to VIII and reflectors A to C
    - Enigma M4 with thin reflectors B and C
    - Interface for custom character rotors and reflectors
* Graphical user interface built with Swing
* Simple text-based console interface
* CLI tool for direct file encyption and decryption

#### Build and run:

To build this project Apache Maven is required. Simply run `mvn package` in the root directory of the project. The file *janigma-X.X.X-SNAPSHOT-jar-with-dependencies.jar* in the target directory is runnable (double-click or launch with `java -jar [file]`).
