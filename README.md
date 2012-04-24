Sample Project Configuration for a Spring Application
-----------------------------------------------------

- Spring 3.1.1
- PAX 1.6 Snapshot (to support Maven 3)
- Felix Maven Bundle Plugin 2.3.7

Setup script is in scripts directory, however it was used as a starting point for the application.
HOWEVER, many changes were necessary for Maven 3 support and Spring 3.1.1.

== To Clean:
$ mvn clean pax:clean

== To Run:
$ mvn clean install pax:provision "-Dframework=equinox"