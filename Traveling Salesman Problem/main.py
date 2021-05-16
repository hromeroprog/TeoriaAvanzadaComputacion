# -*- coding: utf-8 -*-
"""
Created on Tue May  4 12:02:52 2021

@author: 2rome
"""

from TSP import TSP
import numpy as np
import glob
import matplotlib.pyplot as plt


    
if __name__ == '__main__':
    d = {}
    tsp = TSP()
    x = []
    y = []
    y_ropt = [] #r+2opt
    y_gopt = [] #greedy+opt2
    
    x_dim = []
    opt_sum = []
    ropt_sum = []
    gopt_sum = []
    
    for i in range(4, 21):
        files = glob.glob(f'./TSP_generados/Aleatorio*_{i}.tsp')
        x = []
        y = []
        y_ropt = [] #r+2opt
        y_gopt = [] #greedy+opt2
        for index,file in enumerate(files):
            tsp.obtener_desde_archivo_tsp(file)
            tsp.aplicar_mejor_solucion_desde_archivo()
            x.append(index+1)
            y.append(tsp.compute_dist())
            
            tsp.greedy_solve()
            tsp.opt2()
            y_gopt.append(tsp.compute_dist())
            
            tsp.r_solve()
            tsp.opt2()
            y_ropt.append(tsp.compute_dist())
        
        
        x_dim.append(i)
        opt_sum.append(np.mean(y))
        ropt_sum.append(np.mean(y_ropt))
        gopt_sum.append(np.mean(y_gopt))       

    width = 9.6
    height = 7.2
    plt.figure(0, figsize=(width, height))
    plt.errorbar(x_dim, opt_sum,lolims=True, label = 'Solucion optima', color = 'red')
        
    plt.scatter(x_dim, ropt_sum, s=15, marker='x', c='black', label = 'r + 2opt')
    plt.scatter(x_dim, gopt_sum, s=15, marker='x', c='green', label = 'greedy + 2opt')
    plt.legend()
    plt.xlabel('Dimension (Número de ciudades)')
    plt.ylabel('Media de las soluciones de la dimension (metros)')
    plt.title('Comparación métodos de búsqueda local con solución óptima')

    
    
    
    
