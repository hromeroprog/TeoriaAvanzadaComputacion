# -*- coding: utf-8 -*-
"""
Created on Tue May  4 12:02:52 2021

@author: 2rome
"""

from TSP import TSP
from profundidad_y_poda import branchAndBound
from time import sleep
import pandas as pd

if __name__ == '__main__':
    tsp = TSP() #Crear el objeto
    
    min_dim = 4
    max_dim = 21
    scenarios_per_dim = 20
    tiempos = {}
    
    
    for dim in range(min_dim, max_dim):
        tiempos[dim] = [None]*scenarios_per_dim
        for i in range(scenarios_per_dim):
            sleep(1)
            tsp.obtener_random(dim)
            time = branchAndBound(tsp)
            tiempos[dim][i] = time
            tsp.save_scenario()
            tsp.save_solucion()
            df = pd.DataFrame(tiempos)
            df.to_csv('tiempos_branch_ejecucion.csv', index = False)
            print(f'Generado {i+1}/{scenarios_per_dim} de {dim} ciudades')
            
           
