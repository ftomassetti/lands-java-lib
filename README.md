lands-java-lib
==============

[![Build Status](https://travis-ci.org/ftomassetti/lands-java-lib.svg?branch=master)](https://travis-ci.org/ftomassetti/lands-java-lib)

Library to load objects produced by the python [lands](https://github.com/ftomassetti/lands) project (a world generator).

It also wrap [langgen](https://github.com/ftomassetti/langgen) to make accessible to Java. Through that project we can generate languages.

This is needed to integrate [lands](https://github.com/ftomassetti/lands) with Java/Clojure libraries.

The plan is to reuse Java libraries for erosion, like [procedurality-lands](https://github.com/ftomassetti/procedurality-lands) and develop AI for Civs simulation in Clojure (possibly using core.logic).

How to generate languages and names
-----------------------------------

You can ask the library to mix the samples it can with and just give you a language:

```java
Language myLanguage = SamplesBasedLanguageFactory.getRandomLanguage();
```

Or you can provide you own list of samples to get similar names:
```java
List<String> samples = new ArrayList<>;
samples.add("Camille");
samples.add("Marianne");
samples.add("Benoit");
Language myLanguage = new Language(samples);
```

Once you have a language you can very easily generate names:

```java
String myGenerayedName = language.name();
```

