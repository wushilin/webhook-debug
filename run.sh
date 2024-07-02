#!/bin/sh


java \
      -Dserver.address=192.168.44.30 \
      -Dserver.port=4747 \
      -Ddump.dest.folder=/nfs/services/webhook-data \
      -jar webhook-debug-1.0.3-release.jar \
