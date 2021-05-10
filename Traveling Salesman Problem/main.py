# -*- coding: utf-8 -*-
"""
Created on Tue May  4 12:02:52 2021

@author: 2rome
"""

from TSP import TSP
import numpy as np
from profundidad_y_poda import branchAndBound
import time

def t_s(tsp):
    tsp.shuffle()
    start = time.time()
    tsp.r_solve()
    end = time.time()
    print(end-start)
    return end-start
    
if __name__ == '__main__':
    tsp = TSP()
    tsp.obtener_random(3000)
    t_s(tsp)
    
    
    
