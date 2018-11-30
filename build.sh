#!/usr/bin/env bash

native-image -cp target/s2m.jar -H:Name=s2m -H:+ReportUnsupportedElementsAtRuntime --no-server com.gxk.s2m.Main