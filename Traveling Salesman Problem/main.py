# -*- coding: utf-8 -*-
"""
Created on Tue May  4 12:02:52 2021

@author: 2rome
"""

from TSP import TSP
import numpy as np
from profundidad_y_poda import branchAndBound
import time


    
if __name__ == '__main__':
    tsp = TSP()
    tsp.obtener_random(13)
    
    tsp.greedy_solve()
    tsp.opt2()
    tsp.draw_with_solution()
    
    tsp.r_solve()
    tsp.opt2()
    tsp.draw_with_solution()
    
    
    
    branchAndBound(tsp)
    tsp.draw_with_solution()
    
    
    
