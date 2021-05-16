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
    
    x_dim = []
    opt_sum = []
    ropt_sum = []
    gopt_sum = []
    ranopt_sum = []
    
    porcentaje_acierto_ropt = []
    porcentaje_acierto_gopt = []
    porcentaje_acierto_ranopt = []
    
    for i in range(4, 21):
        files = glob.glob(f'./TSP_generados/Aleatorio*_{i}.tsp')
        x = []
        y = []
        y_ropt = [] #r+2opt
        y_gopt = [] #greedy+opt2
        y_ranopt = [] #random+opt2
        
        
        aciertos_ropt = 0
        aciertos_gopt = 0
        aciertos_ranopt = 0
        for index,file in enumerate(files):
            
            tsp.obtener_desde_archivo_tsp(file)
            tsp.aplicar_mejor_solucion_desde_archivo()
            x.append(index+1)
            y.append(tsp.compute_dist())
            best_sol = tsp.compute_dist()
            
            
            tsp.shuffle()
            tsp.opt2()
            y_ranopt.append(tsp.compute_dist())
            if tsp.compute_dist() == best_sol:
                aciertos_ranopt+=1
            
            tsp.greedy_solve()
            tsp.opt2()
            y_gopt.append(tsp.compute_dist())
            if tsp.compute_dist() == best_sol:
                aciertos_gopt+=1
                
            
            tsp.r_solve()
            tsp.opt2()
            y_ropt.append(tsp.compute_dist())
            if tsp.compute_dist() == best_sol:
                aciertos_ropt+=1
                
        
        porcentaje_acierto_ropt.append(round(aciertos_ropt/ len(files), 3)* 100)
        porcentaje_acierto_gopt.append(round(aciertos_gopt/len(files), 3)* 100)
        porcentaje_acierto_ranopt.append(round(aciertos_ranopt/len(files), 3)* 100)
        
        
        x_dim.append(i)
        opt_sum.append(np.mean(y))
        ropt_sum.append(np.mean(y_ropt))
        gopt_sum.append(np.mean(y_gopt))
        ranopt_sum.append(np.mean(y_ranopt))

    width = 9.6
    height = 7.2
    plt.figure(0, figsize=(width, height))
    plt.errorbar(x_dim, opt_sum,lolims=True, label = 'Solucion optima', color = 'red')
        
    plt.scatter(x_dim, ropt_sum, s=15, marker='x', c='black', label = 'r + 2-opt')
    plt.scatter(x_dim, gopt_sum, s=15, marker='x', c='green', label = 'greedy + 2-opt')
    plt.scatter(x_dim, ranopt_sum, s=15, marker='x', c='blue', label = 'random + 2-opt')
    plt.legend()
    plt.xlabel('Dimension (Número de ciudades)')
    plt.ylabel('Media de las soluciones de la dimension (metros)')
    plt.title('Comparación métodos de búsqueda local con solución óptima')
    
    ratios_ropt = [i/j for i,j in zip(ropt_sum, opt_sum)]
    ratios_gopt = [i/j for i,j in zip(gopt_sum, opt_sum)]
    ratios_ranopt = [i/j for i,j in zip(ranopt_sum, opt_sum)]

    
    
    
    
