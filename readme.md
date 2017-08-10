# Markov Chain Text Generator

## Background

A Markov chain of order n represents a transition
function that only depends on the previous n states.
This program uses a Markov chain to generate random
text in the style of the given source text.

## Description

[A more detailed description can be found here.](https://nwtnni.github.io/category/markov/)

This package contains three primary classes:

- PrefixNode
- Markov
- TextGenerator

The first two contain the backbone logic for constructing
Markov chains of generic type, storing sequence information
in a tree structure.

A path from the root node to a leaf node represents a single
unique sequence appearing in the source file. Each node also
stores the probability of each of its children appearing.

The third class contains all of the GUI code. Here's a screenshot:

![Screenshot of GUI](/markov.png)

## Usage

Either double click the jar file, or enter the following command:

`java -jar Markov.jar`
