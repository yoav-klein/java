
java -cp lib/jcommander-1.78.jar;lib/testng-7.5.1.jar;lib/jquery-3.5.1.jar;./classes org.testng.TestNG testng.xml
rem Run only "fast" tests
java -cp lib/jcommander-1.78.jar;lib/testng-7.5.1.jar;lib/jquery-3.5.1.jar;./classes org.testng.TestNG testng.xml -groups fast


rem Run only "slow" tests
java -cp lib/jcommander-1.78.jar;lib/testng-7.5.1.jar;lib/jquery-3.5.1.jar;./classes org.testng.TestNG testng.xml -groups slow
