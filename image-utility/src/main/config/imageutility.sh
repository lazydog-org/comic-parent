#!/bin/sh
#
# Image Utility driver script.

ARGS="$1 $2"
LIBDIR=`dirname $0`/lib
CLASSPATH=$LIBDIR/appserv-admin.jar
CLASSPATH=$CLASSPATH:$LIBDIR/appserv-deployment-client.jar
CLASSPATH=$CLASSPATH:$LIBDIR/appserv-ext.jar
CLASSPATH=$CLASSPATH:$LIBDIR/appserv-rt.jar
CLASSPATH=$CLASSPATH:$LIBDIR/ComicAPI.jar
CLASSPATH=$CLASSPATH:$LIBDIR/ComicRepository.jar
CLASSPATH=$CLASSPATH:$LIBDIR/ImageUtility.jar
CLASSPATH=$CLASSPATH:$LIBDIR/eclipselink.jar
CLASSPATH=$CLASSPATH:$LIBDIR/hibernate-validator.jar
CLASSPATH=$CLASSPATH:$LIBDIR/javaee.jar
CLASSPATH=$CLASSPATH:$LIBDIR/slf4j-api.jar
CLASSPATH=$CLASSPATH:$LIBDIR/validation-api.jar
MAINCLASS=org.lazydog.comic.image.utility.ImageUtility

$JAVA_HOME/bin/java -classpath $CLASSPATH $MAINCLASS $ARGS
