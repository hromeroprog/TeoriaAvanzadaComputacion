# -*- coding: utf-8 -*-
"""
Created on Thu May  6 12:54:23 2021

@author: 2rome
"""
from TSP import TSP
import time
from numba import jit,njit
import numpy as np

#Adaptación realizada por Hugo Romero sobre el código de ng24_7 en geeksforgeeks
@njit
def copyToFinal(curr_path: np.array, final_path: np.array, N: int):
    for i in range(curr_path.size):
        final_path[i] = curr_path[i]
    final_path[N] = curr_path[0]

#Función g(TSP, list, int, int) implementada por Hugo Romero
#funcion heuristica, modificar cuando se desee para podar las ramas
#esta heuristica reduce unas 6 veces el tiempo de ejecucion
@njit
def g(graph: np.array, visited: np.array, level: int, adding_node: int): 
    bound = 0
    for i in range(graph.shape[0]):
        minim = np.inf
        second_minim = np.inf
        if not visited[i] and i != adding_node:
            for j in range(graph.shape[0]):
                if graph[i][j] > 0 and graph[i][j] < minim:
                    second_minim = minim
                    minim = graph[i][j]
                    
                elif graph[i][j] > 0 and graph[i][j] < second_minim:
                    second_minim = graph[i][j]
            if minim != np.inf:
                bound = bound + (minim+second_minim)/2
            
            
    return bound

#Adaptación realizada por Hugo Romero sobre el código de ng24_7 en geeksforgeeks

@njit
def TSPRec(graph: np.array, curr_weight: int,level: int, curr_path: np.array, visited: np.array, final_path: np.array, final_res: np.array):
    if level == graph.shape[0]:
        curr_res = curr_weight + graph[curr_path[level - 1]][curr_path[0]]
        if curr_res < final_res[0]:
            copyToFinal(curr_path, final_path, graph.shape[0])
            final_res[0] = curr_res
        return
  
    for i in range(graph.shape[0]):
        if (graph[curr_path[level-1]][i] != 0 and visited[i] == False):
            curr_weight = curr_weight + graph[curr_path[level - 1]][i]
            curr_bound = g(graph, visited, level, i) #Aqui se hace la llamada a la heuristica
            if curr_bound + curr_weight < final_res[0]:
                curr_path[level] = i
                visited[i] = True
                TSPRec(graph, curr_weight, level + 1, curr_path, visited, final_path, final_res)
                
            curr_weight = curr_weight - graph[curr_path[level - 1]][i]
            visited = np.array([False] * len(visited))
            for j in range(level):
                if curr_path[j] != -1:
                    visited[curr_path[j]] = True
  
#Adaptación realizada por Hugo Romero sobre el código de ng24_7 en geeksforgeeks
#Ejecuta el algoritmo de ramificacion y poda (branch and bound) con heuristica
#por determinar (trabajo de cada grupo).
def branchAndBound(tsp):
    start = time.time()
    final_path = np.array([np.nan] * (tsp.dimension + 1))
    curr_path = np.array([-1] * (tsp.dimension + 1))
    visited = np.array([False] * tsp.dimension)
    visited[0] = True
    curr_path[0] = 0
    final_res = np.array([np.inf])
    TSPRec(tsp.graph, 0, 1, curr_path, visited, final_path, final_res)
    tsp.solution = list(map(lambda x: int(x+1), final_path.tolist()[:-1]))
    print(tsp.solution)
    end = time.time()
    print(f'Solución profundidad y poda generada: {tsp.compute_dist()}m. {end-start} segundos.')
    return(end-start)