set "MAVEN_OPTS=-XX:TieredStopAtLevel=1"
compile.bat -o -Dmaven.javadoc.skip=true -DskipTests -T 2C -Dgpg.skip=true

