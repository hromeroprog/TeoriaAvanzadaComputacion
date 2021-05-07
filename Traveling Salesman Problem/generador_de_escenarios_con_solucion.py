# -*- coding: utf-8 -*-
"""
Created on Tue May  4 12:02:52 2021

@author: 2rome
"""

from TSP import TSP
from profundidad_y_poda import branchAndBound
from time import sleep

#Función main implementada por Hugo Romero
#Sirve como base para que haya un primer vistazo a como se trabajaría
if __name__ == '__main__':
    tsp = TSP() #Crear el objeto
    
    min_dim = 4
    max_dim = 19
    scenarios_per_dim = 20
    tiempos = {}
    for dim in range(min_dim, max_dim):
        tiempos[dim] = []
        for i in range(scenarios_per_dim):
            sleep(1)
            tsp.obtener_random(dim)
            time = branchAndBound(tsp)
            tiempos[dim].append(time)
            tsp.save_scenario()
            tsp.save_solucion()

    
    # No se recomienda probar el backtracking con el archivo .tsp,
    # si este tiene demasiadas dimensiones empezar con generaciones
    # aleatorias y tratar de reducir la computacion con podas y/o heuristicas.
    # La complejidad crece muy rápidamente y se puede bloquear el ordenador.
    
    #tsp.backtracking_solve()
    #tsp.draw_with_solution()
