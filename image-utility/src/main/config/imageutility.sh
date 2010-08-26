#!/bin/sh
#
# Image Utility driver script.

ARGS="$1 $2"
LIBDIR=`dirname $0`/lib
CLASSPATH=$LIBDIR/*
MAINCLASS=org.lazydog.comic.image.utility.ImageUtility

$JAVA_HOME/bin/java -classpath "$CLASSPATH" $MAINCLASS $ARGS
