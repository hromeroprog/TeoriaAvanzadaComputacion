# -*- coding: utf-8 -*-
"""
Created on Tue May  4 12:02:52 2021

@author: 2rome
"""
from random import shuffle
from TSP import TSP

if __name__ == '__main__':
    tsp = TSP()
    fichero = "ulysses16.tsp"
    tsp.obtener_desde_archivo_tsp(fichero)
    tsp.solve()
    tsp.draw_with_solution()
    shuffle(tsp.solution)
    tsp.draw_with_solution()

