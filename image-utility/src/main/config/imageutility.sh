#!/bin/sh
#
# Image Utility driver script.

ARGS="$1 $2"
LIBDIR=`dirname $0`/lib
CLASSPATH=$LIBDIR/activation.jar
CLASSPATH=$CLASSPATH:$LIBDIR/appserv-admin.jar
CLASSPATH=$CLASSPATH:$LIBDIR/appserv-deployment-client.jar
CLASSPATH=$CLASSPATH:$LIBDIR/appserv-ext.jar
CLASSPATH=$CLASSPATH:$LIBDIR/appserv-rt.jar
CLASSPATH=$CLASSPATH:$LIBDIR/comic-api.jar
CLASSPATH=$CLASSPATH:$LIBDIR/data-access.jar
CLASSPATH=$CLASSPATH:$LIBDIR/data-access-api.jar
CLASSPATH=$CLASSPATH:$LIBDIR/eclipselink.jar
CLASSPATH=$CLASSPATH:$LIBDIR/hibernate-validator.jar
CLASSPATH=$CLASSPATH:$LIBDIR/image-utility.jar
CLASSPATH=$CLASSPATH:$LIBDIR/javaee.jar
CLASSPATH=$CLASSPATH:$LIBDIR/jaxb-api.jar
CLASSPATH=$CLASSPATH:$LIBDIR/jaxb-impl.jar
CLASSPATH=$CLASSPATH:$LIBDIR/log4j.jar
CLASSPATH=$CLASSPATH:$LIBDIR/slf4j-api.jar
CLASSPATH=$CLASSPATH:$LIBDIR/slf4j-log4j12.jar
CLASSPATH=$CLASSPATH:$LIBDIR/stax-api.jar
CLASSPATH=$CLASSPATH:$LIBDIR/validation-api.jar
MAINCLASS=org.lazydog.comic.image.utility.ImageUtility

$JAVA_HOME/bin/java -classpath $CLASSPATH $MAINCLASS $ARGS
