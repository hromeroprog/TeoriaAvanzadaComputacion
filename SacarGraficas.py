# -*- coding: utf-8 -*-
"""
Created on Fri Feb 12 13:51:10 2021

@author: 2rome
"""

import pandas as pd
import matplotlib.pyplot as plt
df  = pd.read_csv("data.csv")

#df.plot(kind='scatter',x='Number',y='Time') # scatter plot

import seaborn as sns
#df = sns.load_dataset("data.csv")
 
# Use the 'hue' argument to provide a factor variable
graph = sns.lmplot( x="Number", y="Time", data=df, fit_reg=False, order= 6, ci = None, hue='isPrime', legend=True, scatter_kws={"s": 10})

 
# Move the legend to an empty part of the plot
#plt.legend(loc='lower right')
graph.set(xscale="linear", yscale="linear")

plt.show()

graph = sns.lmplot( x="Number", y="Time", data=df, fit_reg=True, order= 1, ci = None, hue='isPrime', legend=True, scatter_kws={"s": 10})

 
# Move the legend to an empty part of the plot
#plt.legend(loc='lower right')
graph.set(xscale="linear", yscale="linear")

plt.show()
