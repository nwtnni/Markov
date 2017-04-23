# Markov Chain Text Generator

## Background

A Markov chain of order n represents a transition
function that only depends on theh previous n states.
This program uses a Markov chain to generate random
text in the style of the given source text.

## Description

This package contains three primary classes:

- PrefixNode
- Markov
- MarkovTextParser

The first two contain the backbone logic for constructing
Markov chains of generic type, storing sequence information
in a tree structure.

A path from the root node to a leaf node represents a single
unique sequence appearing in the source file. Each node also
stores the probability of each of its children appearing.

## Usage

The jar file can be built using gradle build.

java -jar Markov.jar <ORDER> <FILE>

- ORDER is the order of the generated Markov Chain
- FILE is the file to read
