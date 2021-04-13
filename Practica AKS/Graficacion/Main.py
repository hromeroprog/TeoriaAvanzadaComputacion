# -*- coding: utf-8 -*-
"""
Created on Sun Mar 21 21:11:22 2021

@author: 2rome
"""
import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt

def format_plot():
    plt.xlabel("Number")
    plt.ticklabel_format(axis='x',style = "sci", useMathText=True)
    plt.ticklabel_format(axis='y',style = "sci", useMathText=True)
    

if __name__ == '__main__':
    MAIN_FOLDER = "../AKS_Simple/outputs/"
    SUB_FOLDER = "isPower/"
    FILE_NAME = "primes2"
    X_AXIS = "Bit_size"
    Y_AXIS = "Time"
    
    
    df = pd.read_csv(MAIN_FOLDER + SUB_FOLDER + FILE_NAME + ".csv")
    df.Time = list(map(lambda x: x/1000, df.Time))
    graph = sns.lmplot( x=X_AXIS, y=Y_AXIS, data=df, fit_reg=False, ci = None, legend=True, scatter_kws={"s": 6}, height = 6, aspect = 1.5)

    format_plot()
    