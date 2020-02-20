#!/bin/bash
for x in a b c d e; do java PizzaApp < $x.in > $x.out; done;