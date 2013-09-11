
  Important note before read the followings:
  This project has been abandoned.
  This library had been developed for an earlyer version of Sikuli
  and since the new version came out this library is no longer
  supported. Reports about its usability are apreciated though.
  Thank you for your understanding.
  
  ---- 8< ---- 8= ---- 8< ---- 8= ---- 8< ---- 8= ---- 8< ---- 8= ----  

  This work is based on project by David Luu which can 
  be found at http://code.google.com/p/simplesikuli/
  
  The original license was left unchanged.
  License: Apache License, Version 2.0
           http://www.apache.org/licenses/LICENSE-2.0
  
  This is a basic API library for accessing some Sikuli 
  methods to detect objects, wait for them to appear/disappear,
  click on them, etc. using captured "expected" PNG images 
  of the objects in question.
  
  Implemented with reference to http://sikuli.org/doc/java-x/
  
  For use through:
  
    - command line (CLI) interface for stand-alone use, or
      external integration with other tools/frameworks via
      the system shell 
  
    - Robot Framework as a Java library (remote or not)
      http://www.robotframework.org
  
    - Java (XML-RPC) remote server/library interface,
      usable with Robot Framework or as stand-alone 
      automation server
      http://code.google.com/p/jrobotremoteserver/
      
    - integration with other Java code based tools/frameworks via
      integration or calling of this library code from Java
      
  NOTE (and TODO :): 
        Exceptions are caught and passed to standard output and
        method returns false. While this doesn't fit general
        programming design & Robot Framework specification for
        keyword failures, if you don't like it, feel free to remove
        the exception handling and let the exception propagate and
        be handled by the caller, and in the case of the CLI, let
        exception be handled at the main method. It is designed
        this way for simplicity, so one (and novice users)
        don't have to deal with exceptions. False = fail. And in
        the case of this wrapper library, generally if the
        exception happened, you pretty much know why (straightforward).
        
        And one can also modify to add a flag to enable/disable or
        just disable (no flag) dumping of the exception info, 
        as it can be a lot of extraneous text. 
  
  @author David Luu and Laszlo Jozsef Szalai
  Contact: lj.szalai@yahoo.com
  
  Changes from original source:
   - SCM has been changed to Git and project was pushed to GitHub
   - used sikuli library had been updated to 0.10.2 and code has been refactored accordingly 
   - Settle this project as Maven build
  	- TODO unit tests planned and implemented partially yet
  	- TODO project has been re-shaped based on project rfdblibrary
  available at https://github.com/ThomasJaspers/robotframework-dblibrary
   - TODO (and the original goal was) implement an escape mechanism
   for sending keystrokes with type_at_object method
   @see type_at_object
  
  To satisfy prescribed maven dependency 'sikuli-script' follow these steps:
  	- download sikuli from sikuli.org
  	- go to folder where sikuli-script.jar is installed
  	- issue the following command there:
  		mvn install:install-file 
  			-Dfile=sikuli-script.jar 
  			-DgroupId=org.sikuli 
  			-DartifactId=sikuli-script 
  			-Dversion=0.10.2 
  			-Dpackaging=jar
 
  To satisfy prescribed maven dependency 'jvmconnector' follow these steps:
  	- download jvmconnector from http://code.google.com/p/robotframework-javatools/downloads/list
  	- go to folder where jvmconnector-1.1-jar-with-dependencies.jar resides 
  	- issue the following command there:
  	    mvn install:install-file 
  	    	-Dfile=jvmconnector-1.1-jar-with-dependencies.jar 
  	    	-DgroupId=org.robotframework 
  	    	-DartifactId=jvmconnector 
  	    	-Dversion=1.1 
  	    	-Dpackaging=jar
  	
  	Note: file names and version numbers may vary.
 