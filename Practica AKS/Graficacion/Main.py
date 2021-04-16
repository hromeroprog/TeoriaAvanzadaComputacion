# -*- coding: utf-8 -*-
"""
Created on Sun Mar 21 21:11:22 2021

@author: 2rome
"""
import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import math
from scipy import stats

def abline(df, xlabel):
    """Plot a line from slope and intercept"""
    slope, intercept, r_value, p_value, std_err = stats.linregress(df[df.Time > 0]['Number'],df[df.Time > 0]['Time'])
    axes = plt.gca()
    
    x_vals = np.array(axes.get_xlim())
    # x_vals[0] = -intercept/slope
    y_vals = intercept + slope * x_vals
    
    plt.plot(x_vals, y_vals, '--', color= 'gray')
    axes.text(0, 200, f"Tiempo = {round(intercept, 3)} + {round(slope, 15)} * {xlabel}\nR^2 = {round(r_value, 6)}", horizontalalignment='left', size='medium', color='dimgrey', weight='normal')


def format_plot(x_label):
    plt.title("Evaluación de la función Euclides")
    plt.xlabel(x_label)
    plt.ylabel("Tiempo (segundos)")
    plt.ticklabel_format(axis='x',style = "sci", useMathText=True)
    plt.ticklabel_format(axis='y',style = "sci", useMathText=True)
    

if __name__ == '__main__':
    MAIN_FOLDER = "../AKS_Simple/outputs/"
    SUB_FOLDER = "checkMCD/"
    FILE_NAME = "primesStop"
    FILE_NAME2 = "compositesStop"
    X_AXIS = "Number"
    Y_AXIS = "Time"
    
    
    df = pd.read_csv(MAIN_FOLDER + SUB_FOLDER + FILE_NAME + ".csv")    
    df['es_primo'] = ['si' for _ in range(len(df))]
    
    # df2 = pd.read_csv(MAIN_FOLDER + SUB_FOLDER + FILE_NAME2 + ".csv")
    # df2['es_primo'] = ['no' for _ in range(len(df2))]
    
    # df = df.append(df2)

    df.Time = list(map(lambda x: x/1000, df.Time))
    df = df[0.2 < df.Time]
    # df = df[df.es_primo == 'si']
    df.Number = list(map(float, df.Number))
    df.Number = df.Number * df.NumberR
    # df.Number = list(map(lambda x: math.pow(math.log10(x), 26), df.Number))
    
    graph = sns.lmplot( x=X_AXIS, y=Y_AXIS, data=df, fit_reg=False, ci = None, legend=True, scatter_kws={"s": 6}, height = 6, aspect = 1.5, hue = "es_primo")
    
    x_label = 'Número * Número 2'
    format_plot(x_label)
    abline(df, x_label)
    