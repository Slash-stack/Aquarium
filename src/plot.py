#!/usr/bin/env python3

"""
Script qui produit un graphique à partir
d'un fichier text généré par la simulation
"""

import matplotlib.pyplot as plt


def plot(x, y):
    plt.plot(x, y)
    plt.plot(x,y,'.')
    plt.gca().xaxis.set_ticks(range(x[-1]))
    plt.xlabel("tour")
    plt.ylabel("nombre de poissons dans l'aquarium")
    plt.title("Evolution du banc de poisson")
    plt.show()


def parse_txt(filepath):
    x = []
    y = []
    i = 0
    with open(filepath, "r") as f:
        line = f.readline()
        while line:
            x_data = int(line)
            line = f.readline()
            x.append(x_data)
            y.append(i)
            i += 1
    return y, x


if __name__ == '__main__':
    plot(*parse_txt("../output/population.txt"))
