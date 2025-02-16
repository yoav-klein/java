

group=$1

if [ "$group" = "fast" ]; then
    java -cp lib/jcommander-1.78.jar:lib/testng-7.5.1.jar:lib/jquery-3.5.1.jar:./classes org.testng.TestNG testng.xml -groups fast
fi

if [ "$group" = "slow" ]; then
    java -cp lib/jcommander-1.78.jar:lib/testng-7.5.1.jar:lib/jquery-3.5.1.jar:./classes org.testng.TestNG testng.xml -groups slow
fi

if [ -z "$group" ]; then
    java -cp lib/jcommander-1.78.jar:lib/testng-7.5.1.jar:lib/jquery-3.5.1.jar:./classes org.testng.TestNG testng.xml
fi
# Run only "fast" tests


# Run only "slow" tests
