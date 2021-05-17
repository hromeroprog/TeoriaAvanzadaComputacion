# -*- coding: utf-8 -*-
"""
Created on Tue May  4 12:02:52 2021

@author: 2rome
"""

import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from scipy import stats

def abline(x, y):
    """Plot a line from slope and intercept"""
    slope, intercept, r_value, p_value, std_err = stats.linregress(x,y)
    axes = plt.gca()
    
    x_vals = np.array(axes.get_xlim())
    # x_vals[0] = -intercept/slope
    y_vals = intercept + slope * x_vals
    
    plt.plot(x_vals, y_vals, '--', color= 'gray')
    axes.text(0, 100, f"Tiempo = {round(intercept, 3)} + {round(slope, 15)} * x\nR^2 = {round(r_value, 6)}", horizontalalignment='left', size='medium', color='dimgrey', weight='normal')
    
if __name__ == '__main__':
    df = pd.read_csv('tiempos_branch_ejecucion.csv')

    width = 9.6
    height = 7.2
    plt.figure(0, figsize=(width, height))
    df = df.iloc[:, 9:18]
    
    plt.scatter(list(map(int, df.columns)), df.mean().values,s=15, marker='x', c='black', label = 'Tiempo de ejecución')

    plt.legend()
    plt.xlabel('Dimension ciudades')
    plt.ylabel('media de tiempos de ejecución (segundos)')
    plt.title('Medias tiempos de ejecución método Branch and Bound')
    
    plt.figure(1, figsize=(width, height))
    plt.scatter(list(map(lambda x: x**2*2**x, list(map(int, df.columns)))), df.mean().values,s=15, marker='x', c='black', label = 'Tiempo de ejecución')
    
    
    plt.legend()
    plt.xlabel('Dimension transformada (n^2 * 2^n)')
    plt.ylabel('media de tiempos de ejecución (segundos)')
    plt.title('Medias tiempos de ejecución método Branch and Bound')
    
    a = df.describe()
    print(df.describe())
    abline(list(map(lambda x: x**2*2**x, list(map(int, df.columns)))), df.mean().values)
    plt.ticklabel_format(axis='x',style = "sci", useMathText=True)

    
    
    
    
